package ru.digitalzone.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.digitalzone.test.dao.VisitEventRepository;

import java.time.LocalDate;

/**
 * Данный класс позволяет предзаполнять базу при бутстрапе, можно использовать 3 метода для массовой генерации сущностей для проверки
 * Util.createVisitEventsWithDifferentSites, Util.createVisitEventsWithDifferentDates, Util.createVisitEventsWithDifferentNames
 * <p>
 * <p>
 * По умолчанию создается 1  постоянный пользователль (10 сайтов) за сегодня - 2 дня,  3 уникальных пользователя, 14 событий всего за 3 дня (сегодня - 2 дня)
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Value("${data.preloading.enabled}")
    private boolean dataPreLoadingEnabled = true;
    private final VisitEventRepository visitEventRepository;

    @Autowired
    public DataLoader(VisitEventRepository visitEventRepository) {
        this.visitEventRepository = visitEventRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (dataPreLoadingEnabled) {
            visitEventRepository.saveAll(Util.createVisitEventsWithDifferentSites(9, "testUser", LocalDate.now()));
            visitEventRepository.saveAll(Util.createVisitEventsWithDifferentDates(2, "testUser", "/testSite111"));
            visitEventRepository.saveAll(Util.createVisitEventsWithDifferentSites(2, "testUser100", LocalDate.now()));
            visitEventRepository.saveAll(Util.createVisitEventsWithDifferentNames(1, "/index", LocalDate.now()));
        }
    }
}
