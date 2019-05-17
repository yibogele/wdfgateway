package com.example.wdfgatewayservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:06 2018/8/13
 * Modified By:
 */
public class ShakeHandRequestData {
    private CommonRequestData commonRequestData;

    private String createTime;
    private String deviceId;
    private String iccId;
    private double longitude;
    private double latitude;
    private int signal;
    private int firmwareVersion;
    private double chargeVolt;
    private int chargeCurrent;
    private int dayCharge;
    private int dayUse;
    private double batteryVolt;
    private int mainboardTemp;
    private int batteryTemp;
    private int url;
    private int leftPaper;
    private int binCount;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(int firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public double getChargeVolt() {
        return chargeVolt;
    }

    public double getBatteryVolt() {
        return batteryVolt;
    }

    public void setChargeVolt(double chargeVolt) {
        this.chargeVolt = chargeVolt;
    }

    public void setBatteryVolt(double batteryVolt) {
        this.batteryVolt = batteryVolt;
    }


    public int getChargeCurrent() {
        return chargeCurrent;
    }

    public void setChargeCurrent(int chargeCurrent) {
        this.chargeCurrent = chargeCurrent;
    }

    public int getDayCharge() {
        return dayCharge;
    }

    public void setDayCharge(int dayCharge) {
        this.dayCharge = dayCharge;
    }

    public int getDayUse() {
        return dayUse;
    }

    public void setDayUse(int dayUse) {
        this.dayUse = dayUse;
    }

    public int getMainboardTemp() {
        return mainboardTemp;
    }

    public void setMainboardTemp(int mainboardTemp) {
        this.mainboardTemp = mainboardTemp;
    }

    public int getBatteryTemp() {
        return batteryTemp;
    }

    public void setBatteryTemp(int batteryTemp) {
        this.batteryTemp = batteryTemp;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getLeftPaper() {
        return leftPaper;
    }

    public void setLeftPaper(int leftPaper) {
        this.leftPaper = leftPaper;
    }

    public int getBinCount() {
        return binCount;
    }

    public void setBinCount(int binCount) {
        this.binCount = binCount;
    }

    private List<TrashCanData> trashCanDataList = new ArrayList<>();

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<TrashCanData> getTrashCanDataList() {
        return trashCanDataList;
    }

    public void setTrashCanDataList(List<TrashCanData> trashCanDataList) {
        this.trashCanDataList = trashCanDataList;
    }

    public void addTrashCanData(TrashCanData trashCanData){
        this.trashCanDataList.add(trashCanData);
    }

    public CommonRequestData getCommonRequestData() {
        return commonRequestData;
    }

    public void setCommonRequestData(CommonRequestData commonRequestData) {
        this.commonRequestData = commonRequestData;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIccId() {
        return iccId;
    }

    public void setIccId(String iccId) {
        this.iccId = iccId;
    }

    @Override
    public String toString() {
        return "ShakeHandRequestData{" +
                "commonRequestData=" + commonRequestData +
                ", createTime='" + createTime + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", iccId='" + iccId + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", signal=" + signal +
                ", firmwareVersion=" + firmwareVersion +
                ", chargeVolt=" + chargeVolt +
                ", chargeCurrent=" + chargeCurrent +
                ", dayCharge=" + dayCharge +
                ", dayUse=" + dayUse +
                ", batteryVolt=" + batteryVolt +
                ", mainboardTemp=" + mainboardTemp +
                ", batteryTemp=" + batteryTemp +
                ", url=" + url +
                ", leftPaper=" + leftPaper +
                ", binCount=" + binCount +
                ", trashCanDataList=" + trashCanDataList +
                '}';
    }
}
