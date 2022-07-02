package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name ="imagedto")
public class ImageDTO {

    @Id
    private Long id;
    @Column(name = "name")
    private Date name;
    //map mediaType and sum(imageLength * imageWidth)
    @ElementCollection
    @CollectionTable(name = "name_mediatype_mapping",
            joinColumns = {@JoinColumn(name = "imagedto_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "mediatype_name")
    @Column(name = "mediaTypes")
    Map<String, Float> mediaTypes = new HashMap<String, Float>();

    public ImageDTO( ) {

    }
    public ImageDTO(Date name,Map<String, Float> mediaTypes ) {
        this.name = name;
        this.mediaTypes = mediaTypes;
    }

    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }

    public Map<String, Float> getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(Map<String, Float> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }
}
