package com.example.demo.model.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name ="printmodedto")
public class PrintmodeDTO {

    @Id
    private Long id;
    @Column(name = "name")
    private Date name;
    //map mediaType and sum(imageLength * imageWidth)
    @ElementCollection
    @CollectionTable(name = "name_printmode_mapping",
            joinColumns = {@JoinColumn(name = "printmodedto_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "printmode_name")
    @Column(name = "printmodes")
    Map<String, Float> printmodes = new HashMap<String, Float>();

    public PrintmodeDTO() {
    }

    public PrintmodeDTO(Date name, Map<String, Float> printmodes) {
        this.name = name;
        this.printmodes = printmodes;
    }

    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }

    public Map<String, Float> getPrintmodes() {
        return printmodes;
    }

    public void setPrintmodes(Map<String, Float> printmodes) {
        this.printmodes = printmodes;
    }
}
