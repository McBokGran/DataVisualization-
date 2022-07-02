package com.example.demo.model;

import com.example.demo.model.Enum.GraphType;
import com.example.demo.model.Enum.PrintCycleResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name ="PrintCycle")
public class PrintCycle {
    @Id
    @CsvBindByPosition(position = 0)
    private Long ullId;

    @Column(name = "date")
    @CsvDate(value = "yyyy-dd-MM")
    @CsvBindByPosition(position = 1)
    private Date date;

    @Column(name="time")
    @CsvBindByPosition(position = 2)
    private Time time;

    @Column(name="LocalTime")
    @CsvBindByPosition(position = 3)
    private String localTime;

    @Column(name = "EnginceCycleId")
    @CsvBindByPosition(position = 4)
    private String engineCycleId;

    @Column(name = "PrintCycleId")
    @CsvBindByPosition(position = 5)
    private String printCycleId;

    @Column(name="ActionId")
    @CsvBindByPosition(position = 6)
    private String actionId;

    //@Enumerated(EnumType.STRING)
    @CsvBindByPosition(position = 7)
    private String result;

    @Column(name = "PrintMode")
    @CsvBindByPosition(position = 8)
    private String printMode;

    @Column(name = "Images")
    @CsvBindByPosition(position = 9)
    private String images;

    @Column(name = "SquareDecimeter")
    @CsvBindByPosition(position = 10)
    private float squareDecimeter;

    @Column(name = "MachineId")
    private Integer machineId;

    @Transient
    private GraphType graphType;

    @ElementCollection
    @CollectionTable(name = "print_cycle_extra_columns",
            joinColumns = {@JoinColumn(name = "printCycle_id", referencedColumnName = "ullId")})
    @MapKeyColumn(name = "column_name")
    @Column(name = "column_value")
    Map<String, String> column = new HashMap<String, String>();

    public PrintCycle() {
        graphType = GraphType.PrinctCycle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getEngineCycleId() {
        return engineCycleId;
    }

    public void setEngineCycleId(String engineCycleId) {
        this.engineCycleId = engineCycleId;
    }

    public String getPrintCycleId() {
        return printCycleId;
    }

    public void setPrintCycleId(String printCycleId) {
        this.printCycleId = printCycleId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPrintMode() {
        return printMode;
    }

    public void setPrintMode(String printMode) {
        this.printMode = printMode;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public float getSquareDecimeter() {
        return squareDecimeter;
    }

    public void setSquareDecimeter(float squareDecimeter) {
        this.squareDecimeter = squareDecimeter;
    }

    public GraphType getGraphType() {
        return graphType;
    }

    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Map<String, String> getColumn() {
        return column;
    }

    public void setColumn(Map<String, String> column) {
        this.column = column;
    }
}
