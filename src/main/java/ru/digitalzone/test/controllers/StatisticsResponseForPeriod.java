package ru.digitalzone.test.controllers;

import java.util.Objects;

public class StatisticsResponseForPeriod extends StatisticsResponseFor24Hours {
    private Long regularUsersForPeriod;

    public StatisticsResponseForPeriod() {
    }

    public StatisticsResponseForPeriod(Long totalVisitEventsForPeriod, Long uniqueUsersForPeriod, Long regularUsersForPeriod) {
        super(totalVisitEventsForPeriod, uniqueUsersForPeriod);
        this.regularUsersForPeriod = regularUsersForPeriod;
    }

    public Long getRegularUsersForPeriod() {
        return regularUsersForPeriod;
    }

    public void setRegularUsersForPeriod(Long regularUsersForPeriod) {
        this.regularUsersForPeriod = regularUsersForPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticsResponseForPeriod)) return false;
        if (!super.equals(o)) return false;
        StatisticsResponseForPeriod that = (StatisticsResponseForPeriod) o;
        return Objects.equals(getRegularUsersForPeriod(), that.getRegularUsersForPeriod());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getRegularUsersForPeriod());
    }

    @Override
    public String toString() {
        return "StatisticsResponseForPeriod{" +
                "regularUsersForPeriod=" + regularUsersForPeriod +
                ", totalVisitEventsForPeriod=" + totalVisitEventsForPeriod +
                ", uniqueUsersForPeriod=" + uniqueUsersForPeriod +
                '}';
    }
}
