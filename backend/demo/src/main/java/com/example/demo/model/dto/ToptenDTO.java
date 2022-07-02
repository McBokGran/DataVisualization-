package com.example.demo.model.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name ="toptendto")
public class ToptenDTO {
    @Id
    private Long id;
    //map mediaType and sum(imageLength * imageWidth)
    @ElementCollection
    @CollectionTable(name = "name_machineSquaredecimeter_mapping",
            joinColumns = {@JoinColumn(name = "toptendto_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "machineSquaredecimeter_name")
    @Column(name = "machineSquaredecimeters")
    Map<String, Float> machineSquaredecimeter = new HashMap<String, Float>();

    public ToptenDTO() {
    }

    public ToptenDTO(Map<String, Float> machineSquaredecimeter) {
        this.machineSquaredecimeter = machineSquaredecimeter;
    }


    public Map<String, Float> getMachineSquaredecimeter() {
        return machineSquaredecimeter;
    }

    public void setMachineSquaredecimeter(Map<String, Float> machineSquaredecimeter) {
        this.machineSquaredecimeter = machineSquaredecimeter;
    }
}