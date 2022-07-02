package com.example.demo;


import com.example.demo.model.MediaPrepare;
import com.example.demo.repository.mediaprepare.IMediaPrepareRepository;
import com.example.demo.service.MediaPrepareService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MediaPrepareServiceTest {

    @MockBean
    IMediaPrepareRepository mediaPrepareRepository;
    @Autowired
    MediaPrepareService mediaPrepareService;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setup(){
        this.autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close()  throws  Exception{
        this.autoCloseable.close();
    }

    @Test
    void getAll_whenNull_returnEmpty(){
        List<MediaPrepare> mediaPrepare = new ArrayList<>();

        when(this.mediaPrepareRepository.findAll()).thenReturn(mediaPrepare);

        Assert.isTrue(this.mediaPrepareService.getAllMedias().isEmpty());
    }


}
