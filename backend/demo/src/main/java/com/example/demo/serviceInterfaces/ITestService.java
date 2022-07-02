package com.example.demo.serviceInterfaces;

import com.example.demo.model.Image;
import com.example.demo.model.Test;
import org.springframework.stereotype.Service;

@Service
public interface ITestService {
    void save(Test test);
}
