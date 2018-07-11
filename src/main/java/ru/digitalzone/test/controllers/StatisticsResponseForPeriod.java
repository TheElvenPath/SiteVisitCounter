package ru.digitalzone.test.controllers;

import lombok.Data;


@Data
public class StatisticsResponseForPeriod extends StatisticsResponseFor24Hours {
    private Long regularUsersForPeriod;

}
