package io.github.andersoncrocha.appointments;

import io.github.andersoncrocha.appointments.models.Period;
import io.github.andersoncrocha.appointments.repositories.AppointmentRepository;
import io.github.andersoncrocha.appointments.repositories.EstablishmentRepository;
import io.github.andersoncrocha.appointments.repositories.impl.InMemoryAppointmentRepository;
import io.github.andersoncrocha.appointments.repositories.impl.InMemoryEstablishmentRepository;
import io.github.andersoncrocha.appointments.services.AppointmentService;
import io.github.andersoncrocha.appointments.services.EstablishmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentExampleApplication {

    public static void main(String[] args) {
        EstablishmentRepository establishmentRepository = new InMemoryEstablishmentRepository();
        EstablishmentService establishmentService = new EstablishmentService(establishmentRepository);

        AppointmentRepository appointmentRepository = new InMemoryAppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, establishmentService);

        long establishmentId = 1L;
        LocalDate date = LocalDate.parse("2022-09-16", DateTimeFormatter.ISO_DATE);

        List<Period> periods = appointmentService.getEstablishmentAvailableTimesInDate(establishmentId, date);
        System.out.println(periods);
    }

}
