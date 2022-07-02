package com.example.demo.model.dto;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name ="mediatypesdto")
public class MediaTypesDTO {
    @Id
    private Long id;
    //map mediaType and sum(imageLength * imageWidth)
    @ElementCollection
    @CollectionTable(name = "name_machineSquaredecimeter_mapping",
            joinColumns = {@JoinColumn(name = "toptendto_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "machineSquaredecimeter_name")
    @Column(name = "machineSquaredecimeters")
    Map<String, Float> mediaTypeSquaredecimeter = new HashMap<String, Float>();

    public MediaTypesDTO() {
    }

    public MediaTypesDTO(HashMap<String, Float> mediaTypeSquaredecimeter) {
        this.mediaTypeSquaredecimeter = mediaTypeSquaredecimeter;
    }

    public Map<String, Float> getMediaTypeSquaredecimeter() {
        return mediaTypeSquaredecimeter;
    }

    public void setMediaTypeSquaredecimeter(Map<String, Float> mediaTypeSquaredecimeter) {
        this.mediaTypeSquaredecimeter = mediaTypeSquaredecimeter;
    }
}