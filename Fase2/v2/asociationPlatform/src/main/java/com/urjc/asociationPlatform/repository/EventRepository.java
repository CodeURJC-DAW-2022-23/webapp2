package com.urjc.asociationPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.urjc.asociationPlatform.model.Event;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>{
    Optional<Event> findByName(String name);

    /* 
    @Query(value = "SELECT * FROM event WHERE CONTAINS(name, :name) AND month = :month AND campus = :campus AND asociation = :asociation",nativeQuery = true)
    List<Event> getEvents(@Param("name") String name, @Param("month") String month, @Param("campus") String campus, @Param("asociation") String asociation);

    @Query(value = ":query",nativeQuery = true)
    List<Event> launchQuery(@Param("query") String query);

}
