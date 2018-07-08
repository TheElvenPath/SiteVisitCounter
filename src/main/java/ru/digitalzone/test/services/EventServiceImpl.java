package ru.digitalzone.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.digitalzone.test.controllers.StatisticsResponseFor24Hours;
import ru.digitalzone.test.controllers.StatisticsResponseForPeriod;
import ru.digitalzone.test.dao.VisitEventRepository;
import ru.digitalzone.test.entities.VisitEvent;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class EventServiceImpl implements EventService {

    private final VisitEventRepository visitEventRepository;

    @Autowired
    public EventServiceImpl(VisitEventRepository visitEventRepository) {
        this.visitEventRepository = visitEventRepository;
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<VisitEvent> saveVisitEvent(String userName, String site) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("User is null or empty");
        }
        if (site == null || site.isEmpty()) {
            throw new IllegalArgumentException("Site is null or empty");
        }
        System.out.println("Creating event with thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(visitEventRepository.save(new VisitEvent(userName, site, LocalDate.now())));
    }

    @Override
    public StatisticsResponseForPeriod getStatisticsForPeriod(LocalDate periodStart, LocalDate periodEnd) {
        if (periodStart == null) {
            throw new IllegalArgumentException("PeriodStart is null or empty");
        }
        if (periodEnd == null ) {
            throw new IllegalArgumentException("PeriodEnd is null or empty");
        }
        StatisticsResponseForPeriod result = new StatisticsResponseForPeriod();
        result.setRegularUsersForPeriod(visitEventRepository.countRegularUsersByPeriod(periodStart, periodEnd));
        result.setTotalVisitEventsForPeriod(visitEventRepository.countVisitEventByVisitDateBetween(periodStart, periodEnd));
        result.setUniqueUsersForPeriod(visitEventRepository.countUniqueUsersByPeriod(periodStart, periodEnd));
        return result;
    }

    @Override
    public StatisticsResponseFor24Hours getStatisticsForCurrentDay() {
        StatisticsResponseFor24Hours result = new StatisticsResponseFor24Hours();
        LocalDate now = LocalDate.now();
        result.setTotalVisitEventsForPeriod(visitEventRepository.countVisitEventByVisitDate(now));
        result.setUniqueUsersForPeriod(visitEventRepository.countUniqueUsersByVisitDate(now));
        return result;
    }
}
