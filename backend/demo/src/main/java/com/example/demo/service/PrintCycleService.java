package com.example.demo.service;

import com.example.demo.dalInterfaces.IPrintCycleDal;

import com.example.demo.model.Image;
import com.example.demo.model.PrintCycle;
import com.example.demo.repository.printcycle.IPrintCycleRepository;
import com.example.demo.serviceInterfaces.IPrintCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PrintCycleService implements IPrintCycleService {

    private final IPrintCycleRepository printCycleRepository;

    public PrintCycleService(IPrintCycleRepository printCycleRepository) {
        this.printCycleRepository = printCycleRepository;
    }

    @Override
    public PrintCycle getCycleByCycleId(Long cycleId) {
        return this.printCycleRepository.getById(cycleId);
    }

    @Override
    public List<String> getPrintModes(Date startDate, Date endDate) {
        return printCycleRepository.getPrintModes(startDate, endDate);
    }

    @Override
    public List<String> getMachineId(Date startDate, Date endDate) {
        return printCycleRepository.getMachineId(startDate, endDate);
    }

    @Override
    public List<PrintCycle> findAllByDateBetween(Date startDate, Date endDate) {
        return printCycleRepository.findAllByDateBetween(startDate, endDate);
    }

    @Override
    public List<PrintCycle> getAllPrintCycles() {
        return this.printCycleRepository.findAll();
    }

    @Override
    public void deleteById(Long employeeId)
    {
        this.printCycleRepository.deleteById(employeeId);
    }

    @Override
    public void save(PrintCycle printCycle) {
        this.printCycleRepository.save(printCycle);
    }

    @Override
    public void addExtraColumn(long id, String name, String value) {
        PrintCycle printCycle = this.printCycleRepository.getById(id);
        printCycle.getColumn().put(name, value);
        this.printCycleRepository.save(printCycle);
    }
}