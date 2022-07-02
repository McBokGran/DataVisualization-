package com.example.demo.controller;

import com.example.demo.model.MediaPrepare;
import com.example.demo.service.MediaPrepareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MediaPrepareController {
    private final MediaPrepareService mediaPrepareService;

    public MediaPrepareController(MediaPrepareService mediaPrepareService) {
        this.mediaPrepareService = mediaPrepareService;
    }

    @GetMapping("/api/media-prepare/all")
    public ResponseEntity<?> getAllMediaPrepare(){
        List<MediaPrepare> media = this.mediaPrepareService.getAllMedias();

        return new ResponseEntity<>(media, HttpStatus.OK);
    }
}
