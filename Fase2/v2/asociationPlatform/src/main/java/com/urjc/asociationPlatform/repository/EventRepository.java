package com.urjc.asociationPlatform.repository;

import java.sql.Blob;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.urjc.asociationPlatform.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>, CustomEventRepository{

    @Query(value = "SELECT * FROM event WHERE name LIKE :name AND month = :month AND campus = :campus AND asociation = :asociation",nativeQuery = true)
    List<Event> getEvents(@Param("name") String name, @Param("month") String month, @Param("campus") String campus, @Param("asociation") String asociation);

    @Query(value = ":query",nativeQuery = true)
    List<Event> launchQuery(@Param("query") String query);

    @Query(value = "SELECT * FROM event WHERE asociation = :name", nativeQuery=true)
    List<Event> findAllByAsociation(@Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE event SET name = :name, date = :date, start_time = :startTime, end_time = :endTime, campus = :campus, location = :location, credits = :credits, booking = :booking, description = :description, image = :image WHERE id = :id", nativeQuery = true)
    void updateEvent(@Param("id") long id, @Param("name") String name, @Param("date") String date, @Param("startTime") String startTime, 
                    @Param("endTime") String endTime, @Param("campus") String campus, @Param("location") String location, 
                    @Param("credits") Boolean credits, @Param("booking") Boolean booking, @Param("description") String description, 
                    @Param("image") Blob image);

}
