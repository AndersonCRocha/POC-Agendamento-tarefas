package io.github.andersoncrocha.appointments.repositories;

import io.github.andersoncrocha.appointments.models.Establishment;

import java.util.Optional;

public interface EstablishmentRepository {

    Optional<Establishment> findById(Long id);

}
