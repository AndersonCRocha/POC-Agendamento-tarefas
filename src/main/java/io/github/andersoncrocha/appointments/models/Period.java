package io.github.andersoncrocha.appointments.models;

import java.time.LocalDateTime;

public record Period(LocalDateTime startAt, LocalDateTime endAt) {

    @Override
    public String toString() {
        return """
               { "startAt": %s, "endAt": %s }
               """.formatted(startAt, endAt);
    }

}
