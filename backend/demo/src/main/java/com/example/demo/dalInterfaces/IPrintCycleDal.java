package com.example.demo.dalInterfaces;

import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IPrintCycleDal {
    PrintCycle getCycleByCycleId (Long cycleId);
    List<PrintCycle> getAllPrintCycles();
    void deleteById(Long cycleId);
    void save(PrintCycle printCycle);
    List<String> getPrintModes(Date startDate, Date endDate);
    List<PrintCycle> findAllByDateBetween(Date startDate, Date endDate);

}
