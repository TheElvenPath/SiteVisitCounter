package ru.digitalzone.test.controllers;

import lombok.Data;


@Data
public class StatisticsResponseFor24Hours {
    Long totalVisitEventsForPeriod;
    Long uniqueUsersForPeriod;

}
