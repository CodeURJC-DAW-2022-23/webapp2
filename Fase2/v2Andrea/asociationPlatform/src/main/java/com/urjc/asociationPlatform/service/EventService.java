package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urjc.asociationPlatform.repository.EventRepository;
import com.urjc.asociationPlatform.model.Event;

@Service
public class EventService {

    @Autowired
	private EventRepository events;

	public void save(Event event) {
		events.save(event);
	}

	public Optional<Event> findByName(String name) {
		return events.findByName(name);
	}

	public List<Event> findAll() {
		return events.findAll();
	}

	public Optional<Event> findById(long id) {
		Optional<Event> findById = events.findById(id);
		return findById;
	}

    public void deleteById(long id){
		events.deleteById(id);
	}

	//query ruben
	/* 
	public List<Event> getEventsByFilters(String name, String month, String campus, String asociation){
        /*
        if(name.equals("")){
            name="*";
        }else{
            name='"'+name+'"';
        }
        if(month.equals("")){
            month="*";
        }else{
            month='"'+month+'"';
        }
        if(campus.equals("")){
            campus="*";
        }else{
            campus='"'+campus+'"';
        }
        if(asociation.equals("")){
            asociation="*";
        }else{
            asociation='"'+asociation+'"';
        }
        return events.getEvents(name, month, campus, asociation);*/

        //SELECT * FROM event WHERE CONTAINS(name, :name) AND month = :month AND campus = :campus AND asociation = :asociation
		/* 
        String query="SELECT * FROM event";
        if(!name.equals("")){
            query+=" WHERE CONTAINS(name, '"+name+"')";
        }
        if(!month.equals("")){
            query+=" AND month = '"+month+"'";
        }
        if(!campus.equals("")){
            query+=" AND campus = '"+campus+"'";
        }
        if(!asociation.equals("")){
            query+=" AND asociation = '"+asociation+"'";
        }
        return events.launchQuery(query);
    }*/
}
