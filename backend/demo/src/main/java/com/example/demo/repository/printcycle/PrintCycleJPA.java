//package com.example.demo.repository.printcycle;
//
//import  com.example.demo.dalInterfaces.IPrintCycleDal;
//import com.example.demo.model.PrintCycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//public class PrintCycleJPA implements IPrintCycleDal {
//
//    @Autowired
//    IPrintCycleRepository repo;
//
//    @Override
//    public PrintCycle getCycleByCycleId(Long cycleId) {
//        return repo.getCycleByCycleId(cycleId);
//    }
//
//    @Override
//    public List<PrintCycle> getAllPrintCycles() {
//        return repo.findAll();
//    }
//
//
//    @Override
//    public void deleteById(Long cycleId) {
//        repo.deleteById(cycleId);
//    }
//
//
//}