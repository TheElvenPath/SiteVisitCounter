package ru.digitalzone.test.services;

import ru.digitalzone.test.controllers.StatisticsResponseFor24Hours;
import ru.digitalzone.test.controllers.StatisticsResponseForPeriod;
import ru.digitalzone.test.entities.VisitEvent;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public interface EventService {
    CompletableFuture<VisitEvent> saveVisitEvent(String userName, String site);

    StatisticsResponseForPeriod getStatisticsForPeriod(LocalDate periodStart, LocalDate periodEnd);

    StatisticsResponseFor24Hours getStatisticsForCurrentDay();
}
