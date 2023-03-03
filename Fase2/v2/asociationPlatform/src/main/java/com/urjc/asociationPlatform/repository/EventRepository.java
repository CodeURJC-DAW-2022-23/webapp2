package com.urjc.asociationPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.urjc.asociationPlatform.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository{

    @Query(value = "SELECT * FROM event WHERE name LIKE :name AND month = :month AND campus = :campus AND asociation = :asociation",nativeQuery = true)
    List<Event> getEvents(@Param("name") String name, @Param("month") String month, @Param("campus") String campus, @Param("asociation") String asociation);

    @Query(value = ":query",nativeQuery = true)
    List<Event> launchQuery(@Param("query") String query);

}
