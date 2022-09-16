package io.github.andersoncrocha.appointments.models;

import java.time.Duration;
import java.util.List;

public class Establishment {

    private Long id;
    private String name;
    private Duration defaultAppointmentDuration;
    private List<EstablishmentDailyWork> weeklyWorkingHours;

    public static Establishment of(
            Long id, String name, Duration defaultAppointmentDuration, List<EstablishmentDailyWork> weeklyWorkingHours
    ) {
        Establishment establishment = new Establishment();
        establishment.id = id;
        establishment.name = name;
        establishment.defaultAppointmentDuration = defaultAppointmentDuration;
        establishment.weeklyWorkingHours = weeklyWorkingHours;
        return establishment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Duration getDefaultAppointmentDuration() {
        return defaultAppointmentDuration;
    }

    public List<EstablishmentDailyWork> getWeeklyWorkingHours() {
        return weeklyWorkingHours;
    }

}
