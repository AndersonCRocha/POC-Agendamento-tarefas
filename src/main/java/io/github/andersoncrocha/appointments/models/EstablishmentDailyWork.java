package io.github.andersoncrocha.appointments.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EstablishmentDailyWork {

    private DayOfWeek dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public static EstablishmentDailyWork of(DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime) {
        EstablishmentDailyWork establishmentDailyWork = new EstablishmentDailyWork();
        establishmentDailyWork.dayOfWeek = dayOfWeek;
        establishmentDailyWork.openingTime = openingTime;
        establishmentDailyWork.closingTime = closingTime;
        return establishmentDailyWork;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

}
