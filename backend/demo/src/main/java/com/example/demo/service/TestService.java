package com.example.demo.service;

import com.example.demo.model.Image;
import com.example.demo.model.Test;
import com.example.demo.repository.image.IImageRepository;
import com.example.demo.repository.test.ITestRepository;
import com.example.demo.serviceInterfaces.IImageService;
import com.example.demo.serviceInterfaces.ITestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TestService implements ITestService{
    private final ITestRepository testRepository;

    public TestService(ITestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }
}
