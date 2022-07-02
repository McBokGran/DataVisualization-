package com.example.demo.serviceInterfaces;
import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;

import java.sql.Date;
import java.util.List;

public interface IMediaPrepareService {

    MediaPrepare getMediaByMediaId (Long appointmentId);
    List<MediaPrepare> findAllByDateBetween(java.sql.Date startDate, Date endDate);

    List<String> getMediaTypes(Date startDate, Date endDate);
    List<MediaPrepare> getAllMedias();
    void save(MediaPrepare mediaPrepare);
    void addExtraColumn(long id, String columnName, String columnValue);

}