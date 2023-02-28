package com.urjc.asociationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.repository.EventRepository;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;
  
	public void save(Event event) {
		eventRepository.save(event);
	}

	//public Optional<Event> findByName(String name) {
		//return eventRepository.findByName(name);
	//}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Optional<Event> findById(long id) {
		Optional<Event> findById = eventRepository.findById(id);
		return findById;
	}

    public void deleteById(long id){
		eventRepository.deleteById(id);
	}

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

	//query ruben

	public List<Event> getEventsByFilters(String name, String month, String campus, String asociation){

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
