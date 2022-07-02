package com.example.demo.repository.printcycle;
import com.example.demo.model.Image;
import com.example.demo.model.PrintCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface IPrintCycleRepository extends JpaRepository<PrintCycle, Long> {
    PrintCycle getById(Long cycleId);
    PrintCycle save (PrintCycle printCycle);
    @Query("Select p.printMode From PrintCycle p where p.date <= :endDate and p.date >= :startDate group by p.printMode, p.date")
    List<String> getPrintModes(Date startDate, Date endDate);
    @Query("Select p.machineId From PrintCycle p where p.date <= :endDate and p.date >= :startDate group by p.machineId, p.date")
    List<String> getMachineId(Date startDate, Date endDate);
    @Query("Select p From PrintCycle p where p.date <= :endDate and p.date >= :startDate order by p.date asc")
    List<PrintCycle> findAllByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
