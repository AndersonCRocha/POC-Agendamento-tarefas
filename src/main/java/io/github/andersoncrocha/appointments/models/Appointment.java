package io.github.andersoncrocha.appointments.models;

import io.github.andersoncrocha.appointments.models.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {

    private AppointmentStatus status;
    private Long establishmentId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static Appointment of(Long establishmentId, LocalDateTime startAt, LocalDateTime endAt) {
        Appointment appointment = new Appointment();
        appointment.status = AppointmentStatus.PENDING;
        appointment.establishmentId =
                Objects.requireNonNull(establishmentId, "Appointment establishment id cannot be null");
        appointment.startAt = Objects.requireNonNull(startAt, "Appointment start time cannot be null");
        appointment.endAt = Objects.requireNonNull(endAt, "Appointment end time cannot be null");

        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("Appointment start time cannot be after end time");
        }

        return appointment;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

}
