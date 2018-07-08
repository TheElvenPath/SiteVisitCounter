package ru.digitalzone.test.controllers;

import java.util.Objects;

public class StatisticsResponseFor24Hours {
    Long totalVisitEventsForPeriod;
    Long uniqueUsersForPeriod;

    public StatisticsResponseFor24Hours() {
    }

    public StatisticsResponseFor24Hours(Long totalVisitEventsForPeriod, Long uniqueUsersForPeriod) {
        this.totalVisitEventsForPeriod = totalVisitEventsForPeriod;
        this.uniqueUsersForPeriod = uniqueUsersForPeriod;
    }

    public Long getTotalVisitEventsForPeriod() {
        return totalVisitEventsForPeriod;
    }

    public void setTotalVisitEventsForPeriod(Long totalVisitEventsForPeriod) {
        this.totalVisitEventsForPeriod = totalVisitEventsForPeriod;
    }

    public Long getUniqueUsersForPeriod() {
        return uniqueUsersForPeriod;
    }

    public void setUniqueUsersForPeriod(Long uniqueUsersForPeriod) {
        this.uniqueUsersForPeriod = uniqueUsersForPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticsResponseFor24Hours)) return false;
        StatisticsResponseFor24Hours that = (StatisticsResponseFor24Hours) o;
        return Objects.equals(totalVisitEventsForPeriod, that.totalVisitEventsForPeriod) &&
                Objects.equals(uniqueUsersForPeriod, that.uniqueUsersForPeriod);
    }

    @Override
    public int hashCode() {

        return Objects.hash(totalVisitEventsForPeriod, uniqueUsersForPeriod);
    }

    @Override
    public String toString() {
        return "StatisticsResponseFor24Hours{" +
                "totalVisitEventsForPeriod=" + totalVisitEventsForPeriod +
                ", uniqueUsersForPeriod=" + uniqueUsersForPeriod +
                '}';
    }
}
