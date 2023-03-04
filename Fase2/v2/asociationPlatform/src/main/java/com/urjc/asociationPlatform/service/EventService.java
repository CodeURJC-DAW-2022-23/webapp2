package com.urjc.asociationPlatform.service;

import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
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
    public List<Event> findAllbyAsociation(String asociation) {
		return eventRepository.findAllByAsociation(asociation);
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
		eventRepository.deleteById(id);
	}

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

	//query ruben

	public List<Event> getEventsByFilters(String name, String month, String campus, String asociation){


        List<Object[]> eventsObj = eventRepository.customQuery(name, month, campus, asociation);
        
        List<Event> eventsList=new ArrayList<>();

        for(Object[] obj : eventsObj){
            Event event=new Event();
            if(obj[0] instanceof BigInteger){
                event.setId(((BigInteger)obj[0]).longValue());
            }
            if(obj[1] instanceof String){
                event.setAsociation((String)obj[1]);
            }
            if(obj[2] instanceof Boolean){
                event.setBooking((boolean)obj[2]);
            }
            if(obj[3] instanceof String){
                event.setCampus((String)obj[3]);
            }
            if(obj[4] instanceof Boolean){
                event.setCredits((boolean)obj[4]);
            }
            if(obj[5] instanceof Date){
                event.setDate((Date)obj[5]);
            }
            if(obj[6] instanceof String){
                event.setDescription((String)obj[6]);
            }
            if(obj[7] instanceof String){
                event.setDuration((String)obj[7]);
            }
            if(obj[8] instanceof String){
                event.setEndTime((String)obj[8]);
            }
            if(obj[9] instanceof Blob){
                event.setImgUrl((Blob)obj[9]);
            }
            if(obj[10] instanceof String){
                event.setLocation((String)obj[10]);
            }
            if(obj[11] instanceof String){
                event.setMonth((String)obj[11]);
            }
            if(obj[12] instanceof String){
                event.setName((String)obj[12]);
            }
            if(obj[13] instanceof String){
                event.setStartTime((String)obj[13]);
            }
            eventsList.add(event);
        
        
        }
        return eventsList;
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
