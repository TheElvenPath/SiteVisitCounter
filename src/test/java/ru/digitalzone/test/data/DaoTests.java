package ru.digitalzone.test.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digitalzone.test.Util;
import ru.digitalzone.test.dao.VisitEventRepository;
import ru.digitalzone.test.entities.VisitEvent;

import java.time.LocalDate;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class DaoTests {

    @Autowired
    private VisitEventRepository visitEventRepository;

    @Test
    public void countTodayVisitEventsTest() {
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentSites(10, "User", LocalDate.now()));
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setSite("/site");
        visitEvent.setUserName("Kolya");
        visitEvent.setVisitDate(LocalDate.now().minusDays(1));
        visitEventRepository.save(visitEvent);
        Assert.assertEquals(Long.valueOf(10), visitEventRepository.countVisitEventByVisitDate(LocalDate.now()));
    }

    @Test
    public void countUniqueTodayVisitEventsTest() {
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentSites(5, "User", LocalDate.now()));
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentDates(3, "User2", "/site"));
        Assert.assertEquals(Long.valueOf(2), visitEventRepository.countUniqueUsersByVisitDate(LocalDate.now()));
    }

    @Test
    public void statisticsByPeriodTest() {
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentSites(10, "User", LocalDate.now()));
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentDates(10, "User", "/site"));
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentDates(10, "User2", "/site"));
        visitEventRepository.saveAll(Util.createVisitEventsWithDifferentNames(100, "/site", LocalDate.now()));
        Assert.assertEquals(Long.valueOf(18), visitEventRepository.countVisitEventByVisitDateBetween(LocalDate.now().minusDays(10), LocalDate.now().minusDays(1)));
        Assert.assertEquals(Long.valueOf(2), visitEventRepository.countUniqueUsersByPeriod(LocalDate.now().minusDays(10), LocalDate.now().minusDays(1)));
        Assert.assertEquals(Long.valueOf(1), visitEventRepository.countRegularUsersByPeriod(LocalDate.now().minusDays(10), LocalDate.now()));
    }


}
