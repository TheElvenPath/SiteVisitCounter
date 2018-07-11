package ru.digitalzone.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalzone.test.entities.VisitEvent;
import ru.digitalzone.test.services.EventService;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
public class VisitEventsController {

    private final EventService eventService;

    @Autowired
    public VisitEventsController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/statistics")
    public ResponseEntity<?> addStatistics(@RequestParam(name = "user") String userName, @RequestParam(name = "url") String site) {
        CompletableFuture<VisitEvent> event = eventService.saveVisitEvent(userName, site);
        //сделал чтобы возвращалась правильная статистика, с учетом только что добавленного
        event.join();
        return new ResponseEntity<>(eventService.getStatisticsForCurrentDay(), HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public StatisticsResponseForPeriod getStatisticsForPeriod(@RequestParam("startDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate startDate,
                                                              @RequestParam("endDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate endDate) {
        return eventService.getStatisticsForPeriod(startDate, endDate);
    }
}
