package com.example.demo.model.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name ="inkusagedto")
public class InkusageDTO {

    @Id
    private Long id;
    @Column(name = "name")
    private Date name;
    //map mediaType and sum(imageLength * imageWidth)
    @ElementCollection
    @CollectionTable(name = "name_inktype_mapping",
            joinColumns = {@JoinColumn(name = "inkusagedto_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "inktype_name")
    @Column(name = "inkTypes")
    Map<String, Float> inkTypes = new HashMap<String, Float>();

    public InkusageDTO() {
    }

    public InkusageDTO(Date name, Map<String, Float> inkTypes) {
        this.name = name;
        this.inkTypes = inkTypes;
    }

    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }

    public Map<String, Float> getInkTypes() {
        return inkTypes;
    }

    public void setInkTypes(Map<String, Float> inkTypes) {
        this.inkTypes = inkTypes;
    }
}
