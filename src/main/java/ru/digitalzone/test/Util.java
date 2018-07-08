package ru.digitalzone.test;

import ru.digitalzone.test.entities.VisitEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * @param n - number of created visitEvents
     * @return List of VisitEvents with same name and date but different page
     */
    public static List<VisitEvent> createVisitEventsWithDifferentSites(int n, String name, LocalDate date) {
        List<VisitEvent> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new VisitEvent(name, "/page" + i + ".jsp", date));
        }
        return events;
    }

    /**
     * @param n - number of created visitEvents
     * @return List of VisitEvents with same page and date but different name
     */
    public static List<VisitEvent> createVisitEventsWithDifferentNames(int n, String site, LocalDate date) {
        List<VisitEvent> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new VisitEvent("User" + i, site, date));
        }
        return events;
    }

    /**
     * @param n - number of created visitEvents
     * @return List of VisitEvents with same name and site but different dates
     */
    public static List<VisitEvent> createVisitEventsWithDifferentDates(int n, String name, String site) {
        List<VisitEvent> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new VisitEvent(name, site, LocalDate.now().minusDays(i)));
        }
        return events;
    }
}
