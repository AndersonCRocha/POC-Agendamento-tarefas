package io.github.andersoncrocha.appointments.repositories.impl;

import io.github.andersoncrocha.appointments.models.Establishment;
import io.github.andersoncrocha.appointments.models.EstablishmentDailyWork;
import io.github.andersoncrocha.appointments.repositories.EstablishmentRepository;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryEstablishmentRepository implements EstablishmentRepository {

    private final List<Establishment> establishments;

    public InMemoryEstablishmentRepository() {
        Duration duration = Duration.ofHours(1);
        List<EstablishmentDailyWork> weeklyWorkingHours = Arrays.asList(
                EstablishmentDailyWork.of(DayOfWeek.MONDAY, LocalTime.parse("08:00"), LocalTime.parse("18:00")),
                EstablishmentDailyWork.of(DayOfWeek.TUESDAY, LocalTime.parse("08:00"), LocalTime.parse("18:00")),
                EstablishmentDailyWork.of(DayOfWeek.WEDNESDAY, LocalTime.parse("08:00"), LocalTime.parse("18:00")),
                EstablishmentDailyWork.of(DayOfWeek.THURSDAY, LocalTime.parse("08:00"), LocalTime.parse("18:00")),
                EstablishmentDailyWork.of(DayOfWeek.FRIDAY, LocalTime.parse("08:00"), LocalTime.parse("18:00")),
                EstablishmentDailyWork.of(DayOfWeek.SATURDAY, LocalTime.parse("08:00"), LocalTime.parse("12:00"))
        );
        Establishment establishment = Establishment.of(1L, "My Establishment", duration, weeklyWorkingHours);
        this.establishments = Collections.singletonList(establishment);
    }

    @Override
    public Optional<Establishment> findById(Long id) {
        return establishments.stream()
                .filter(establishment -> id.equals(establishment.getId()))
                .findFirst();
    }

}
