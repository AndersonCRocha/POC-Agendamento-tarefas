package io.github.andersoncrocha.appointments.services;

import io.github.andersoncrocha.appointments.models.Establishment;
import io.github.andersoncrocha.appointments.repositories.EstablishmentRepository;

public record EstablishmentService(EstablishmentRepository repository) {

    public Establishment findOrFailById(Long establishmentId) {
        return repository.findById(establishmentId)
                .orElseThrow(() -> {
                    String message = "Establishment not found for id: %d".formatted(establishmentId);
                    return new IllegalArgumentException(message);
                });
    }

}
