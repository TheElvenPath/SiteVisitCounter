package ru.digitalzone.test.controllers;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class StatisticsResponseForPeriod extends StatisticsResponseFor24Hours {
    private Long regularUsersForPeriod;

}
