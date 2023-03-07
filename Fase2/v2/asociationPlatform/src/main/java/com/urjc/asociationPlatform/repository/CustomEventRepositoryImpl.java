package com.urjc.asociationPlatform.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.cj.protocol.ValueEncoder;
import com.urjc.asociationPlatform.service.AsociationService;

public class CustomEventRepositoryImpl implements CustomEventRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AsociationService asoService;

    private final int pageSize=1;
    
    @Override
    public List<Object[]> customQuery(String name, String month, String campus, String asociation, int page) {
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
            sb.append(" asociation_id = '"+asoService.findByName(asociation).get().getId().toString()+"'");
        }
        int value=page*pageSize;
        sb.append(" LIMIT "+value+" OFFSET 0");
        System.out.println(sb.toString());

        Query query = entityManager.createNativeQuery(sb.toString());

        return query.getResultList();
    }
}

