package io.github.andersoncrocha.appointments.services;

import io.github.andersoncrocha.appointments.models.Appointment;
import io.github.andersoncrocha.appointments.models.Establishment;
import io.github.andersoncrocha.appointments.models.EstablishmentDailyWork;
import io.github.andersoncrocha.appointments.models.Period;
import io.github.andersoncrocha.appointments.repositories.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record AppointmentService(
        AppointmentRepository repository,
        EstablishmentService establishmentService
) {

    public List<Period> getEstablishmentAvailableTimesInDate(Long establishmentId, LocalDate date) {
        List<Appointment> scheduledAppointmentsInDate = repository.findByDateAndEstablishmentId(date, establishmentId);
        List<Period> allAvailablePeriods = getAllAvailableHoursFromEstablishmentBusinessHour(establishmentId, date);
        return allAvailablePeriods.stream()
                .filter(period -> checkIfPeriodHasNoAppointment(scheduledAppointmentsInDate, period))
                .toList();
    }

    private boolean checkIfPeriodHasNoAppointment(List<Appointment> scheduledAppointments, Period period) {
        LocalDateTime startPeriodTime = period.startAt();
        LocalDateTime endPeriodTime = period.endAt();

        return scheduledAppointments.stream()
                .allMatch(scheduledAppointment -> {
                    LocalDateTime scheduledStartTime = scheduledAppointment.getStartAt();
                    LocalDateTime scheduledEndTime = scheduledAppointment.getEndAt();

                    return !startPeriodTime.isBefore(scheduledEndTime) || !endPeriodTime.isAfter(scheduledStartTime);
                });
    }

    private List<Period> getAllAvailableHoursFromEstablishmentBusinessHour(Long establishmentId, LocalDate date) {
        Establishment establishment = establishmentService.findOrFailById(establishmentId);

        Optional<EstablishmentDailyWork> establishmentDailyWorkOptional = establishment.getWeeklyWorkingHours()
                .stream()
                .filter(dailyWork -> dailyWork.getDayOfWeek() == date.getDayOfWeek())
                .findFirst();

        if (establishmentDailyWorkOptional.isEmpty()) {
            return Collections.emptyList();
        }

        EstablishmentDailyWork establishmentDailyWork = establishmentDailyWorkOptional.get();

        LocalTime currentStartPeriod = establishmentDailyWork.getOpeningTime();
        LocalTime currentEndPeriod = currentStartPeriod.plus(establishment.getDefaultAppointmentDuration());

        if (currentEndPeriod.isAfter(establishmentDailyWork.getClosingTime())) {
            return Collections.emptyList();
        }

        List<Period> availableHours = new ArrayList<>();

        do {
            availableHours.add(new Period(date.atTime(currentStartPeriod), date.atTime(currentEndPeriod)));
            currentStartPeriod = currentEndPeriod;
            currentEndPeriod = currentEndPeriod.plus(establishment.getDefaultAppointmentDuration());
        } while (!currentEndPeriod.isAfter(establishmentDailyWork.getClosingTime()));

        return availableHours;
    }

}
