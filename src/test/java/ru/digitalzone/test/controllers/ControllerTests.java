package ru.digitalzone.test.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalzone.test.dao.VisitEventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ControllerTests {
    @Autowired
    VisitEventRepository visitEventRepository;
    @Autowired
    private TestRestTemplate restTemplate;


    /**
     * Данные подгружаются в
     *
     * @see ru.digitalzone.test.DataLoader
     */
    @Test
    @Transactional
    public void addStatisticsTest() {
        Map<String, String> request = new HashMap<>();
        request.put("user", "valentin");
        request.put("url", "/testSite1");

        ResponseEntity<StatisticsResponseFor24Hours> response =
                restTemplate.postForEntity(restTemplate.getRootUri() + "/statistics?user={user}&url={url}", null, StatisticsResponseFor24Hours.class, request);

        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(Long.valueOf(14), response.getBody().getTotalVisitEventsForPeriod());
        Assert.assertEquals(Long.valueOf(4), response.getBody().getUniqueUsersForPeriod());
    }

    /**
     * Тест для измерения многопоточного добавления (имитируеут множество пользователей)
     */
    @Test
    @Transactional
    public void addStatisticsMultiThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<StatisticsResponseFor24Hours>> futureList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<StatisticsResponseFor24Hours> future = executorService.submit(() -> {
                Map<String, String> request = new HashMap<>();
                request.put("user", "valentin");
                request.put("url", "/testSite1");

                ResponseEntity<?> response =
                        restTemplate.postForEntity(restTemplate.getRootUri() + "/statistics?user={user}&url={url}", null, StatisticsResponseFor24Hours.class, request);

                //System.out.println(Thread.currentThread().getName() + ": " + response.getBody());
                return (StatisticsResponseFor24Hours) response.getBody();
            });
            futureList.add(future);
        }
        futureList.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    @Transactional
    public void getPeriodStatisticsTest() {
        Map<String, Object> request = new HashMap<>();
        request.put("startDate", LocalDate.now().minusDays(3));
        request.put("endDate", LocalDate.now());

        ResponseEntity<StatisticsResponseForPeriod> response =
                restTemplate.getForEntity(restTemplate.getRootUri() + "/statistics?startDate={startDate}&endDate={endDate}", StatisticsResponseForPeriod.class, request);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Long.valueOf(1), response.getBody().getRegularUsersForPeriod());
        Assert.assertEquals(Long.valueOf(14), response.getBody().getTotalVisitEventsForPeriod());
        Assert.assertEquals(Long.valueOf(3), response.getBody().getUniqueUsersForPeriod());
    }

}
