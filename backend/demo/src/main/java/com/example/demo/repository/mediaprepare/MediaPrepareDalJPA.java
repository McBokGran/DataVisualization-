//package com.example.demo.repository.mediaprepare;
//
//import com.example.demo.dalInterfaces.IMediaPrepareDal;
//import com.example.demo.model.MediaPrepare;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//
//public class MediaPrepareDalJPA implements IMediaPrepareDal {
//
//
//    private final IMediaPrepareRepository repo;
//
//    public MediaPrepareDalJPA(IMediaPrepareRepository repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public MediaPrepare getMediaByMediaId(Long appointmentId) {
//        return repo.getMediaByMediaId(appointmentId);
//    }
//
//    @Override
//    public List<MediaPrepare> getAllMedias() {
//        return repo.findAll();
//    }
//
//    @Override
//    public void deleteById(Long mediaId) {
//        repo.deleteById(mediaId);
//    }
//
//}