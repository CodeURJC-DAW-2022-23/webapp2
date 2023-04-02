package com.urjc.asociationPlatform.model.restModel;

import com.urjc.asociationPlatform.model.Asociation;

public class AsociationDTO {
    public Long id;
    public String name;
    public String description;
    public String faculty;
    public String campus;
    public Long ownerId;

    public AsociationDTO(Asociation asociation){
        this.id = asociation.getId();
        this.name = asociation.getName();
        this.description = asociation.getDescription();
        this.faculty = asociation.getFaculty();
        this.campus = asociation.getCampus();
        if(asociation.getOwner() == null)
            this.ownerId = Long.valueOf(0);
        else
            this.ownerId = asociation.getOwnerId();
    }
}
