package com.example.demo;

import com.example.demo.model.Image;
import com.example.demo.repository.image.IImageRepository;
import com.example.demo.service.ImageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
class ImageServiceTest {
    @MockBean
    IImageRepository imageRepository;
    @Autowired
    ImageService imageService;
    AutoCloseable autoCloseable;

    @BeforeEach
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void findAll_Images_whenNull_returnEmpty(){
        List<Image> images = new ArrayList<>();
        when(this.imageRepository.findAll()).thenReturn(images);

        Assert.isTrue(this.imageService.getAllImages().isEmpty());
    }

}
