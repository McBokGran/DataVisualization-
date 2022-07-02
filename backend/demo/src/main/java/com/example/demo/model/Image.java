package com.example.demo.model;

import com.example.demo.model.Enum.GraphType;
import com.example.demo.model.Enum.ImageResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name ="Image")
public class Image {

    @Id
    @CsvBindByPosition(position = 0)
    private Long ullId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "date")
    @CsvDate(value = "yyyy-dd-MM")
    @CsvBindByPosition(position = 1)
    private Date date;

    @Column(name = "time")
    @CsvBindByPosition(position = 2)
    private Time time;

    @Column(name="LocalTime")
    @CsvBindByPosition(position = 3)
    private String localTime;

    @Column(name = "engineCycleId")
    @CsvBindByPosition(position = 4)
    private Integer engineCycleId;

    @Column(name = "ImageId")
    @CsvBindByPosition(position = 5)
    private String imageIdN;

    @Column(name = "imageResult")
    @CsvBindByPosition(position = 6)
    private String imageResult;

    @Column(name = "imageLengthAnnounced")
    @CsvBindByPosition(position = 7)
    private float imageLengthAnnounced;

    @Column(name = "imageLength")
    @CsvBindByPosition(position = 8)
    private float imageLength;

    @Column(name = "imageWidth")
    @CsvBindByPosition(position = 9)
    private float imageWidth;

    @Column(name = "MediaType")
    @CsvBindByPosition(position = 10)
    private String mediaType;

    @Column(name = "PrintMode")
    @CsvBindByPosition(position = 11)
    private String printMode;

    @Column(name = "Destination")
    @CsvBindByPosition(position = 12)
    private String destination;

    @Column(name = "JettedInkCyan")
    @CsvBindByPosition(position = 13)
    private float jettedInkCyan;

    @Column(name = "JettedInkMagenta")
    @CsvBindByPosition(position = 14)
    private float jettedInkMagenta;

    @Column(name = "JettedInkYellow")
    @CsvBindByPosition(position = 15)
    private float jettedInkYellow;

    @Column(name = "JettedInkBlack")
    @CsvBindByPosition(position = 16)
    private float jettedInkBlack;

    @Column(name = "AccountedInkCyan")
    @CsvBindByPosition(position = 17)
    private float accountedInkCyan;

    @Column(name = "AccountedInkMagenta")
    @CsvBindByPosition(position = 18)
    private float accountedInkMagenta;

    @Column(name = "AccountedInkYellow")
    @CsvBindByPosition(position = 19)
    private float accountedInkYellow;

    @Column(name = "AccountedInkBlack")
    @CsvBindByPosition(position = 20)
    private float accountedInkBlack;

    @Column(name = "PaperStepMarker")
    @CsvBindByPosition(position = 21)
    private String paperStepMarker;

    @Column(name = "MachineId")
    private Integer machineId;

    @Transient
    private GraphType graphType;

    public Image() {
        graphType = GraphType.Image;
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

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public Integer getEngineCycleId() {
        return engineCycleId;
    }

    public void setEngineCycleId(Integer engineCycleId) {
        this.engineCycleId = engineCycleId;
    }

    public String getImageIdN() {
        return imageIdN;
    }

    public void setImageIdN(String imageIdN) {
        this.imageIdN = imageIdN;
    }

    public String getImageResult() {
        return imageResult;
    }

    public void setImageResult(String imageResult) {
        this.imageResult = imageResult;
    }

    public float getImageLengthAnnounced() {
        return imageLengthAnnounced;
    }

    public void setImageLengthAnnounced(float imageLengthAnnounced) {
        this.imageLengthAnnounced = imageLengthAnnounced;
    }

    public float getImageLength() {
        return imageLength;
    }

    public void setImageLength(float imageLength) {
        this.imageLength = imageLength;
    }

    public float getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(float imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPrintMode() {
        return printMode;
    }

    public void setPrintMode(String printMode) {
        this.printMode = printMode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getJettedInkCyan() {
        return jettedInkCyan;
    }

    public void setJettedInkCyan(float jettedInkCyan) {
        this.jettedInkCyan = jettedInkCyan;
    }

    public float getJettedInkMagenta() {
        return jettedInkMagenta;
    }

    public void setJettedInkMagenta(float jettedInkMagenta) {
        this.jettedInkMagenta = jettedInkMagenta;
    }

    public float getJettedInkYellow() {
        return jettedInkYellow;
    }

    public void setJettedInkYellow(float jettedInkYellow) {
        this.jettedInkYellow = jettedInkYellow;
    }

    public float getJettedInkBlack() {
        return jettedInkBlack;
    }

    public void setJettedInkBlack(float jettedInkBlack) {
        this.jettedInkBlack = jettedInkBlack;
    }

    public float getAccountedInkCyan() {
        return accountedInkCyan;
    }

    public void setAccountedInkCyan(float accountedInkCyan) {
        this.accountedInkCyan = accountedInkCyan;
    }

    public float getAccountedInkMagenta() {
        return accountedInkMagenta;
    }

    public void setAccountedInkMagenta(float accountedInkMagenta) {
        this.accountedInkMagenta = accountedInkMagenta;
    }

    public float getAccountedInkYellow() {
        return accountedInkYellow;
    }

    public void setAccountedInkYellow(float accountedInkYellow) {
        this.accountedInkYellow = accountedInkYellow;
    }

    public float getAccountedInkBlack() {
        return accountedInkBlack;
    }

    public void setAccountedInkBlack(float accountedInkBlack) {
        this.accountedInkBlack = accountedInkBlack;
    }

    public String getPaperStepMarker() {
        return paperStepMarker;
    }

    public void setPaperStepMarker(String paperStepMarker) {
        this.paperStepMarker = paperStepMarker;
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
}
