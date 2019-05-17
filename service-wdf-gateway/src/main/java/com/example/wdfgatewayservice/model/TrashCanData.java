package com.example.wdfgatewayservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 8:52 2018/8/14
 * Modified By:
 */
public class TrashCanData {
//    String devId;

    String garbageType;

    String binNum;
//    String id;
//    String connectTime;

    int faultStatus;
    int workStatus;
    int temperature;
    int volume;
    double realWeight;

    List<TrashCanFault> trashCanFaultList = new ArrayList<>();
    List<NeedCleanData> needCleanDataList = new ArrayList<>();

    public void addNeedCleanData(NeedCleanData needCleanData){
        needCleanDataList.add(needCleanData);
    }

    public List<NeedCleanData> getNeedCleanDataList() {
        return needCleanDataList;
    }

    public void addTrashCanFault(TrashCanFault trashCanFault){
        trashCanFaultList.add(trashCanFault);
    }

    public List<TrashCanFault> getTrashCanFaultList() {
        return trashCanFaultList;
    }

    public int getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(int faultStatus) {
        this.faultStatus = faultStatus;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(double realWeight) {
        this.realWeight = realWeight;
    }

//    public String getDevId() {
//        return devId;
//    }
//
//    public void setDevId(String devId) {
//        this.devId = devId;
//    }

    public String getBinNum() {
        return binNum;
    }

    public void setBinNum(String binNum) {
        this.binNum = binNum;
    }

//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public String getConnectTime() {
//        return connectTime;
//    }
//
//    public void setConnectTime(String connectTime) {
//        this.connectTime = connectTime;
//    }

    public String getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(String garbageType) {
        this.garbageType = garbageType;
    }

    @Override
    public String toString() {
        return "TrashCanData{" +
//                "devId='" + devId + '\'' +
                ", garbageType='" + garbageType + '\'' +
                ", binNum='" + binNum + '\'' +
//                ", id='" + id + '\'' +
//                ", connectTime='" + connectTime + '\'' +
                ", faultStatus=" + faultStatus +
                ", workStatus=" + workStatus +
                ", temperature=" + temperature +
                ", volume=" + volume +
                ", realWeight=" + realWeight +
                ", trashCanFaultList=" + trashCanFaultList +
                ", needCleanDataList=" + needCleanDataList +
                '}';
    }
}
