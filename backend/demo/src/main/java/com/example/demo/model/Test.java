package com.example.demo.model;

import com.example.demo.model.Enum.ImageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name ="Test")
public class Test {
    @Id
    @CsvBindByPosition(position = 0)
    private Long ullId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "date")
    @CsvDate(value = "dd/MM/yyyy")
    //04/02/2021
    @CsvBindByPosition(position = 1)

    private Date date;

    @Column(name = "time")
    @CsvBindByPosition(position = 2)

    private Time time;



    public Test() {
    }


    public Long getUllId() {
        return ullId;
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

}
