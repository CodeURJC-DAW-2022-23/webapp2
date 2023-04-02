package com.urjc.asociationPlatform.model.restModel;

import com.urjc.asociationPlatform.model.Asociation;

public class AsociationDTO {
    public Long id;
    public String name;
    public String description;
    public String faculty;
    public String campus;
    public Long owner;

    public AsociationDTO(Asociation asociation){
        this.id = asociation.getId();
        this.name = asociation.getName();
        this.description = asociation.getDescription();
        this.faculty = asociation.getFaculty();
        this.campus = asociation.getCampus();
        if(asociation.getOwner() == null)
            this.owner = Long.valueOf(0);
        else
            this.owner = asociation.getOwnerId();
    }
}
