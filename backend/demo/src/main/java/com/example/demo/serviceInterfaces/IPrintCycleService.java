package com.example.demo.serviceInterfaces;
import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;

import java.sql.Date;
import java.util.List;

public interface IPrintCycleService {

    PrintCycle getCycleByCycleId (Long employeeId);
    List<String> getPrintModes(Date startDate, Date endDate);
    List<String> getMachineId(Date startDate, Date endDate);
    List<PrintCycle> findAllByDateBetween(Date startDate, Date endDate);
    List<PrintCycle> getAllPrintCycles();
    void deleteById(Long employeeId);
    void save(PrintCycle printCycle);
    void addExtraColumn(long id, String name, String value);
}