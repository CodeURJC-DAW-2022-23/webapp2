package com.urjc.asociationPlatform.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomEventRepositoryImpl implements CustomEventRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> customQuery(String name, String month, String campus, String asociation) {
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
            sb.append(" asociation = '"+asociation+"'");
        }
        System.out.println(sb.toString());

        Query query = entityManager.createNativeQuery(sb.toString());

        return query.getResultList();
    }
}

