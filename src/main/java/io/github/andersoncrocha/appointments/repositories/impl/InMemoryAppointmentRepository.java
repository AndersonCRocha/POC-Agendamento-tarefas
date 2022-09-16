package io.github.andersoncrocha.appointments.repositories.impl;

import io.github.andersoncrocha.appointments.models.Appointment;
import io.github.andersoncrocha.appointments.models.enums.AppointmentStatus;
import io.github.andersoncrocha.appointments.repositories.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class InMemoryAppointmentRepository implements AppointmentRepository {

    private final List<Appointment> appointments;

    public InMemoryAppointmentRepository() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Long establishmentId = 1L;
        this.appointments = Arrays.asList(
                Appointment.of(
                        establishmentId,
                        LocalDateTime.parse("2022-09-16 08:00", dateTimeFormatter),
                        LocalDateTime.parse("2022-09-16 09:00", dateTimeFormatter)
                ),
                Appointment.of(
                        establishmentId,
                        LocalDateTime.parse("2022-09-16 09:00", dateTimeFormatter),
                        LocalDateTime.parse("2022-09-16 10:00", dateTimeFormatter)
                ),
                Appointment.of(
                        establishmentId,
                        LocalDateTime.parse("2022-09-16 10:00", dateTimeFormatter),
                        LocalDateTime.parse("2022-09-16 11:00", dateTimeFormatter)
                ),
                Appointment.of(
                        establishmentId,
                        LocalDateTime.parse("2022-09-16 13:00", dateTimeFormatter),
                        LocalDateTime.parse("2022-09-16 14:00", dateTimeFormatter)
                )
        );
    }

    @Override
    public List<Appointment> findByDateAndEstablishmentId(LocalDate date, Long establishmentId) {
        return appointments.stream()
                .filter(appointment -> {
                    LocalDateTime startAt = appointment.getStartAt();
                    LocalDateTime endAt = appointment.getEndAt();

                    boolean isSameEstablishment = establishmentId.equals(appointment.getEstablishmentId());
                    boolean isPendingStatus = appointment.getStatus() == AppointmentStatus.PENDING;
                    boolean startDateIsFilterDate = date.equals(startAt.toLocalDate());
                    boolean endDateIsFilterDate = date.equals(endAt.toLocalDate());

                    return isSameEstablishment && isPendingStatus && (startDateIsFilterDate || endDateIsFilterDate);
                }).toList();
    }

}
