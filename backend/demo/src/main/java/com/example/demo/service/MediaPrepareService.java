package com.example.demo.service;
import com.example.demo.dalInterfaces.IMediaPrepareDal;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;
import com.example.demo.repository.mediaprepare.IMediaPrepareRepository;
import com.example.demo.serviceInterfaces.IMediaPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MediaPrepareService implements IMediaPrepareService {

    private final IMediaPrepareRepository iMediaPrepareRepository;

    public MediaPrepareService(IMediaPrepareRepository iMediaPrepareRepository) {
        this.iMediaPrepareRepository = iMediaPrepareRepository;
    }

    @Override
    public MediaPrepare getMediaByMediaId(Long appointmentId) {
        return this.iMediaPrepareRepository.getById(appointmentId);
    }

    @Override
    public List<String> getMediaTypes(Date startDate, Date endDate) {
        return iMediaPrepareRepository.getMediaTypes(startDate, endDate);
    }

    @Override
    public List<MediaPrepare> findAllByDateBetween(Date startDate, Date endDate) {
        return iMediaPrepareRepository.findAllByDateBetween(startDate, endDate);
    }

    @Override
    public List<MediaPrepare> getAllMedias() {
        return this.iMediaPrepareRepository.findAll();
    }

    @Override
    public void save(MediaPrepare mediaPrepare) {
        this.iMediaPrepareRepository.save(mediaPrepare);
    }

    @Override
    public void addExtraColumn(long id, String columnName, String columnValue){
        MediaPrepare mediaPrepare = this.getMediaByMediaId(id);
        mediaPrepare.getColumn().put(columnName, columnValue);
        this.iMediaPrepareRepository.save(mediaPrepare);
    }

}