package com.example.demo;

import com.example.demo.model.PrintCycle;
import com.example.demo.repository.printcycle.IPrintCycleRepository;
import com.example.demo.service.PrintCycleService;
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
public class PrintCycleServiceTest {

    @MockBean
    IPrintCycleRepository printCycleRepository;
    @Autowired
    PrintCycleService printCycleService;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setup(){
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception{
        autoCloseable.close();
    }

    @Test
    void getAll_whenEmpty_returnTrue(){
        List<PrintCycle> printCycles = new ArrayList<>();

        when(this.printCycleRepository.findAll()).thenReturn(printCycles);

        Assert.isTrue(this.printCycleService.getAllPrintCycles().isEmpty());
    }


}
