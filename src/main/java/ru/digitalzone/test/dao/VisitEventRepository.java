package ru.digitalzone.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.digitalzone.test.entities.VisitEvent;

import java.time.LocalDate;


public interface VisitEventRepository extends CrudRepository<VisitEvent, Long> {
    @Query(value = " SELECT count(cnt1.user_name) FROM (SELECT count(DISTINCT site) as cnt, ve.user_name FROM visit_events ve WHERE ve.visit_date between :startDate AND :endDate GROUP BY ve.user_name having cnt >=10) cnt1", nativeQuery = true)
    Long countRegularUsersByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Long countVisitEventByVisitDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT count(DISTINCT userName) FROM visitEvents WHERE visitDate between ?1 AND ?2")
    Long countUniqueUsersByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Long countVisitEventByVisitDate(LocalDate visitDate);

    @Query("SELECT count(DISTINCT userName) FROM visitEvents WHERE visitDate= ?1")
    Long countUniqueUsersByVisitDate(LocalDate visitDate);

}
