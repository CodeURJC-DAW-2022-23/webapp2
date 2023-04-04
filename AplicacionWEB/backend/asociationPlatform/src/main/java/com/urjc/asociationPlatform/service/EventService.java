package com.urjc.asociationPlatform.service;

import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.model.Asociation;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.repository.EventRepository;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  AsociationService asociationService;
  
	public void save(Event event) {
		eventRepository.save(event);
	}

	// public Optional<Event> findByName(String name) {
	// 	return eventRepository.findByName(name);
	// }

	public List<Event> findAll() {
		return eventRepository.findAll();
	}
    public List<Event> findAllbyAsociation(Asociation asociation) {
		return eventRepository.findAllByAsociation(asociation.getId());
	}
    
    public void updateById(Event event, long id){
        int booking=0;
        int credits=0;
        if(event.getBooking()){
            booking=1;
        }
        if(event.getCredits()){
            credits=1;
        }
        eventRepository.updateEvent(id,event.getName(),event.getDate().toString(),event.getStartTime(),event.getEndTime(),
                                    event.getCampus(),event.getLocation(),event.getCredits(),event.getBooking(),
                                    event.getDescription(),event.getImage());
    }

	public Optional<Event> findById(long id) {
		Optional<Event> findById = eventRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
        /*Event event= eventRepository.findById(id).orElseThrow();
        event.clearComments();*/
		eventRepository.deleteById(id);
	}

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

	//query ruben

	public Page<Event> getEventsByFilters(String name, String month, String campus, String asociation, Pageable pageable){


        return eventRepository.customQuery(name, month, campus, asociation, pageable);

        /*
        if(name.equals("") || name.equals("ALL")){
            name="*";
        }
        if(month.equals("") || month.equals("ALL")){
            month="*";
        }
        if(campus.equals("") || campus.equals("ALL")){
            campus="*";
        }
        if(asociation.equals("") || asociation.equals("ALL")){
            asociation="*";
        }
        return eventRepository.getEvents(name, month, campus, asociation);
        */
        /* 
        //SELECT * FROM event WHERE CONTAINS(name, :name) AND month = :month AND campus = :campus AND asociation = :asociation
        String query="SELECT * FROM event";
        if(!name.equals("")){
            query+=" WHERE name LIKE '"+name+"'";
        }
        if(!month.equals("")){
            query+=" AND month = '"+month+"'";
        }
        if(!campus.equals("")){
            query+=" AND location = '"+campus+"'";
        }
        if(!asociation.equals("")){
            query+=" AND asociation = '"+asociation+"'";
        }
        System.out.println(query);
        return events.launchQuery(query);*/
    }
}
