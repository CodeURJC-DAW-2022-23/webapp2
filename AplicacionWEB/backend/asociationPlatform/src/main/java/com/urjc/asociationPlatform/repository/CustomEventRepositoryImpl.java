package com.urjc.asociationPlatform.repository;

import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import com.mysql.cj.protocol.ValueEncoder;
import com.urjc.asociationPlatform.model.Event;
import com.urjc.asociationPlatform.service.AsociationService;

public class CustomEventRepositoryImpl implements CustomEventRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AsociationService asoService;

    private final int pageSize=1;
    
    @Override
    public Page<Event> customQuery(String name, String month, String campus, String asociation, Pageable pageable) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM event");
        int conditions=0;

        if(!name.equals("")){
            if(conditions==0){
                sb.append(" WHERE");
            }
            conditions++;
            sb.append(" name LIKE '%"+name+"%'");
        }
        if(!month.equals("") && !month.equals("All")){
            if(conditions==0){
                sb.append(" WHERE");
            }else{
                sb.append(" AND");
            }
            conditions++;
            sb.append(" month = '"+month+"'");
        }
        if(!campus.equals("") && !campus.equals("All")){
            if(conditions==0){
                sb.append(" WHERE");
            }else{
                sb.append(" AND");
            }
            conditions++;
            sb.append(" campus = '"+campus+"'");
        }
        if(!asociation.equals("") && !asociation.equals("All")){
            if(conditions==0){
                sb.append(" WHERE");
            }else{
                sb.append(" AND");
            }
            conditions++;
            sb.append(" asociation_id = "+asoService.findByName(asociation).get().getId().toString()+"");
        }
        //int value=page*pageSize;
        sb.append(" LIMIT "+pageable.getPageSize()+" OFFSET " + pageable.getOffset());
        Query queryTotal = entityManager.createNativeQuery("SELECT count(e.id) FROM event e");
        long countResult = ((BigInteger)queryTotal.getSingleResult()).longValue();
        Query query = entityManager.createNativeQuery(sb.toString());
        //----------------------------obtencion y conversion--------------------------------
        List<Object[]> eventsObj=query.getResultList();
        List<Event> eventsList=new ArrayList<>();

        for(Object[] obj : eventsObj){
            Event event=new Event();
            if(obj[0] instanceof BigInteger){
                event.setId(((BigInteger)obj[0]).longValue());
            }
            if(obj[1] instanceof Boolean){
                event.setBooking((boolean)obj[1]);
            }
            if(obj[2] instanceof String){
                event.setCampus((String)obj[2]);
            }
            if(obj[3] instanceof Boolean){
                event.setCredits((boolean)obj[3]);
            }
            if(obj[4] instanceof Date){
                event.setDate((Date)obj[4]);
            }
            if(obj[5] instanceof String){
                event.setDescription((String)obj[5]);
            }
            if(obj[6] instanceof String){
                event.setDuration((String)obj[6]);
            }
            if(obj[7] instanceof String){
                event.setEndTime((String)obj[7]);
            }
            if(obj[8] instanceof Blob){
                event.setImgUrl((Blob)obj[8]);
            }
            if(obj[9] instanceof String){
                event.setLocation((String)obj[9]);
            }
            if(obj[10] instanceof String){
                event.setMonth((String)obj[10]);
            }
            if(obj[11] instanceof String){
                event.setName((String)obj[11]);
            }
            if(obj[12] instanceof String){
                event.setStartTime((String)obj[12]);
            }
            if(obj[13] instanceof BigInteger){
                event.setAsociation(asoService.findById(((BigInteger)obj[13]).longValue()).get()); //Probably works
                //event.setAsociation(asociationService.findByName((String)obj[13]).get());
            }
            eventsList.add(event);
        
        
        }


        return new PageImpl<>(eventsList, pageable, countResult);
    }
}

