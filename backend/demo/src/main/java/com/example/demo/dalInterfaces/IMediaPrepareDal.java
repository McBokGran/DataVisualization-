package com.example.demo.dalInterfaces;


import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IMediaPrepareDal {
    MediaPrepare getMediaByMediaId (Long appointmentId);
    List<MediaPrepare> getAllMedias();
    void deleteById(Long appointmentId);
    void save(MediaPrepare mediaPrepare);

}