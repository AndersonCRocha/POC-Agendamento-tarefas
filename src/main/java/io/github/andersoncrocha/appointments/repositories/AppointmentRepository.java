package io.github.andersoncrocha.appointments.repositories;

import io.github.andersoncrocha.appointments.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository {

    List<Appointment> findByDateAndEstablishmentId(LocalDate date, Long establishmentId);

}
