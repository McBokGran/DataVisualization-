package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.example.demo.model.Enum.GraphType;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name ="MediaPrepare")
public class MediaPrepare {

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

    @Column(name = "MediaPrepareId")
    @CsvBindByPosition(position = 4)
    private Integer mediaPrepareId;

    @Column(name = "Purpose")
    @CsvBindByPosition(position = 5)
    private String purpose;

    @Column(name = "EngineCycleId")
    @CsvBindByPosition(position = 6)
    private String engineCycleId;

    @Column(name = "Category")
    @CsvBindByPosition(position =7)
    private String category;

    @Column(name = "MediaTypeDisplayName")
    @CsvBindByPosition(position = 8)
    private String mediaTypeDisplayName;

    @Column(name = "MediaId")
    @CsvBindByPosition(position = 9)
    private String mediaId;

    @Column(name = "NominalWidth")
    @CsvBindByPosition(position = 10)
    private Integer nominalWidth;

    @Column(name = "Measured")
    @CsvBindByPosition(position = 11)
    private Integer measured;

    @Column(name = "SideEdgeLeft")
    @CsvBindByPosition(position = 12)
    private Integer sideEdgeLeft;

    @Column(name = "SideEdgeRight")
    @CsvBindByPosition(position = 13)
    private Integer sideEdgeRight;

    @Column(name = "WaitingForCleancut")
    @CsvBindByPosition(position = 14)
    private Integer waitingForCleancut;

    @Column(name = "ManualCut")
    @CsvBindByPosition(position = 15)
    private Integer manualCut;

    @Column(name = "ManualLoading")
    @CsvBindByPosition(position = 16)
    private Integer manualLoading;

    @Column(name = "MediaThickness")
    @CsvBindByPosition(position = 17)
    private Integer mediaThickness;

    @Column(name = "AutomaticMediaJogging")
    @CsvBindByPosition(position = 18)
    private Integer automaticMediaJogging;

    @Column(name = "AdvanceCorrection")
    @CsvBindByPosition(position = 19)
    private float advanceCorrection;

    //Change to moist correction
    @Column(name = "MoistCorrection")
    @CsvBindByPosition(position = 20)
    private Integer moistProtection;

    @Column(name = "PreCurePower")
    @CsvBindByPosition(position = 21)
    private Integer preCurePower;

    @Column(name = "PostCurePower")
    @CsvBindByPosition(position = 22)
    private Integer postCurePower;

    @Column(name = "BufferHeaterSetpoint")
    @CsvBindByPosition(position = 23)
    private Integer bufferHeaterSetpoint;

    @Column(name = "CarriageHeightIndex")
    @CsvBindByPosition(position = 24)
    private Integer carriageHeightIndex;

    //Change to step correction
    @Column(name = "ControlPaperSterCorrection")
    @CsvBindByPosition(position = 25)
    private Integer controlPaperStepCorrection;

    @Column(name = "ControlPaperStepPatternType")
    @CsvBindByPosition(position = 26)
    private String controlPaperStepPatternType;

    @Column(name = "FanFrictionCorrectionTable")
    @CsvBindByPosition(position = 27)
    private String fanFrictionCorrectionTable;

    @Column(name = "FanFrictionXCoordTable")
    @CsvBindByPosition(position = 28)
    private String fanFrictionXCoordTable;

    @Column(name = "PaperStepCorrectionFactor")
    @CsvBindByPosition(position = 29)
    private float paperStepCorrectionFactor;

    @Column(name = "PrinterPlatenTemperature")
    @CsvBindByPosition(position = 30)
    private float printerPlatenTemperature;

    @Column(name = "VacuumSurfaceSettings")
    @CsvBindByPosition(position = 31)
    private String vacuumSurfaceSettings;

    @Column(name = "PrintStrategy")
    @CsvBindByPosition(position = 32)
    private Integer printStrategy;

    @Column(name = "RobustBacklit")
    @CsvBindByPosition(position = 33)
    private Integer robustBacklit;

    @Column(name = "mediaMinimalSwathDuration")
    @CsvBindByPosition(position = 34)
    private Integer mediaMinimalSwathDuration;

    @Column(name = "WindingType")
    @CsvBindByPosition(position = 35)
    private String windingType;

    @Column(name = "RollPosition")
    @CsvBindByPosition(position = 36)
    private Integer rollPosition;

    @Column(name = "PrepareFromDrawer")
    @CsvBindByPosition(position = 37)
    private Integer prepareFromDrawer;

    @Column(name = "InsideRoll")
    @CsvBindByPosition(position = 38)
    private Integer insideRoll;

    @Column(name = "SmartInkLimit")
    @CsvBindByPosition(position = 39)
    private Integer smartInkLimit;

    @Column(name = "MachineId")
    private Integer machineId;

    @Transient
    private GraphType graphType;

    @ElementCollection
    @CollectionTable(name = "media_prepare_extra_columns",
            joinColumns = {@JoinColumn(name = "mediaPrepare_id", referencedColumnName = "ullId")})
    @MapKeyColumn(name = "column_name")
    @Column(name = "column_value")
    Map<String, String> column = new HashMap<String, String>();

    public MediaPrepare() {
        graphType = GraphType.MediaPrepare;
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

    public Integer getMediaPrepareId() {
        return mediaPrepareId;
    }

    public void setMediaPrepareId(Integer mediaPrepareId) {
        this.mediaPrepareId = mediaPrepareId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getEngineCycleId() {
        return engineCycleId;
    }

    public void setEngineCycleId(String engineCycleId) {
        this.engineCycleId = engineCycleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMediaTypeDisplayName() {
        return mediaTypeDisplayName;
    }

    public void setMediaTypeDisplayName(String mediaTypeDisplayName) {
        this.mediaTypeDisplayName = mediaTypeDisplayName;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getNominalWidth() {
        return nominalWidth;
    }

    public void setNominalWidth(Integer nominalWidth) {
        this.nominalWidth = nominalWidth;
    }

    public Integer getMeasured() {
        return measured;
    }

    public void setMeasured(Integer measured) {
        this.measured = measured;
    }

    public Integer getSideEdgeLeft() {
        return sideEdgeLeft;
    }

    public void setSideEdgeLeft(Integer sideEdgeLeft) {
        this.sideEdgeLeft = sideEdgeLeft;
    }

    public Integer getSideEdgeRight() {
        return sideEdgeRight;
    }

    public void setSideEdgeRight(Integer sideEdgeRight) {
        this.sideEdgeRight = sideEdgeRight;
    }

    public Integer getWaitingForCleancut() {
        return waitingForCleancut;
    }

    public void setWaitingForCleancut(Integer waitingForCleancut) {
        this.waitingForCleancut = waitingForCleancut;
    }

    public Integer getManualCut() {
        return manualCut;
    }

    public void setManualCut(Integer manualCut) {
        this.manualCut = manualCut;
    }

    public Integer getManualLoading() {
        return manualLoading;
    }

    public void setManualLoading(Integer manualLoading) {
        this.manualLoading = manualLoading;
    }

    public Integer getMediaThickness() {
        return mediaThickness;
    }

    public void setMediaThickness(Integer mediaThickness) {
        this.mediaThickness = mediaThickness;
    }

    public Integer getAutomaticMediaJogging() {
        return automaticMediaJogging;
    }

    public void setAutomaticMediaJogging(Integer automaticMediaJogging) {
        this.automaticMediaJogging = automaticMediaJogging;
    }

    public float getAdvanceCorrection() {
        return advanceCorrection;
    }

    public void setAdvanceCorrection(float advanceCorrection) {
        this.advanceCorrection = advanceCorrection;
    }

    public Integer getMoistProtection() {
        return moistProtection;
    }

    public void setMoistProtection(Integer moistProtection) {
        this.moistProtection = moistProtection;
    }

    public Integer getPreCurePower() {
        return preCurePower;
    }

    public void setPreCurePower(Integer preCurePower) {
        this.preCurePower = preCurePower;
    }

    public Integer getPostCurePower() {
        return postCurePower;
    }

    public void setPostCurePower(Integer postCurePower) {
        this.postCurePower = postCurePower;
    }

    public Integer getBufferHeaterSetpoint() {
        return bufferHeaterSetpoint;
    }

    public void setBufferHeaterSetpoint(Integer bufferHeaterSetpoint) {
        this.bufferHeaterSetpoint = bufferHeaterSetpoint;
    }

    public Integer getCarriageHeightIndex() {
        return carriageHeightIndex;
    }

    public void setCarriageHeightIndex(Integer carriageHeightIndex) {
        this.carriageHeightIndex = carriageHeightIndex;
    }

    public Integer getControlPaperStepCorrection() {
        return controlPaperStepCorrection;
    }

    public void setControlPaperStepCorrection(Integer controlPaperStepCorrection) {
        this.controlPaperStepCorrection = controlPaperStepCorrection;
    }

    public String getControlPaperStepPatternType() {
        return controlPaperStepPatternType;
    }

    public void setControlPaperStepPatternType(String controlPaperStepPatternType) {
        this.controlPaperStepPatternType = controlPaperStepPatternType;
    }

    public String getFanFrictionCorrectionTable() {
        return fanFrictionCorrectionTable;
    }

    public void setFanFrictionCorrectionTable(String fanFrictionCorrectionTable) {
        this.fanFrictionCorrectionTable = fanFrictionCorrectionTable;
    }

    public String getFanFrictionXCoordTable() {
        return fanFrictionXCoordTable;
    }

    public void setFanFrictionXCoordTable(String fanFrictionXCoordTable) {
        this.fanFrictionXCoordTable = fanFrictionXCoordTable;
    }

    public float getPaperStepCorrectionFactor() {
        return paperStepCorrectionFactor;
    }

    public void setPaperStepCorrectionFactor(float paperStepCorrectionFactor) {
        this.paperStepCorrectionFactor = paperStepCorrectionFactor;
    }

    public float getPrinterPlatenTemperature() {
        return printerPlatenTemperature;
    }

    public void setPrinterPlatenTemperature(float printerPlatenTemperature) {
        this.printerPlatenTemperature = printerPlatenTemperature;
    }

    public String getVacuumSurfaceSettings() {
        return vacuumSurfaceSettings;
    }

    public void setVacuumSurfaceSettings(String vacuumSurfaceSettings) {
        this.vacuumSurfaceSettings = vacuumSurfaceSettings;
    }

    public Integer getPrintStrategy() {
        return printStrategy;
    }

    public void setPrintStrategy(Integer printStrategy) {
        this.printStrategy = printStrategy;
    }

    public Integer getRobustBacklit() {
        return robustBacklit;
    }

    public void setRobustBacklit(Integer robustBacklit) {
        this.robustBacklit = robustBacklit;
    }

    public Integer getMediaMinimalSwathDuration() {
        return mediaMinimalSwathDuration;
    }

    public void setMediaMinimalSwathDuration(Integer mediaMinimalSwathDuration) {
        this.mediaMinimalSwathDuration = mediaMinimalSwathDuration;
    }

    public String getWindingType() {
        return windingType;
    }

    public void setWindingType(String windingType) {
        this.windingType = windingType;
    }

    public Integer getRollPosition() {
        return rollPosition;
    }

    public void setRollPosition(Integer rollPosition) {
        this.rollPosition = rollPosition;
    }

    public Integer getPrepareFromDrawer() {
        return prepareFromDrawer;
    }

    public void setPrepareFromDrawer(Integer prepareFromDrawer) {
        this.prepareFromDrawer = prepareFromDrawer;
    }

    public Integer getInsideRoll() {
        return insideRoll;
    }

    public void setInsideRoll(Integer insideRoll) {
        this.insideRoll = insideRoll;
    }

    public Integer getSmartInkLimit() {
        return smartInkLimit;
    }

    public void setSmartInkLimit(Integer smartInkLimit) {
        this.smartInkLimit = smartInkLimit;
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
