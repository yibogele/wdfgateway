package com.example.wdfgatewayservice.model;

import com.example.wdfgatewayservice.constant.TrashCanFullType;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 10:26 2018/8/14
 * Modified By:
 */
public class NeedCleanData {
//    String id;
//    String devId;
    double weight;
    String garbageType;
//    String fullTime;
    String cleanStatus = "N";
    TrashCanFullType trashCanFullType;

    public TrashCanFullType getTrashCanFullType() {
        return trashCanFullType;
    }

    public void setTrashCanFullType(TrashCanFullType trashCanFullType) {
        this.trashCanFullType = trashCanFullType;
    }

//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public String getDevId() {
//        return devId;
//    }

//    public void setDevId(String devId) {
//        this.devId = devId;
//    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(String garbageType) {
        this.garbageType = garbageType;
    }

//    public String getFullTime() {
//        return fullTime;
//    }

//    public void setFullTime(String fullTime) {
//        this.fullTime = fullTime;
//    }

    public String getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(String cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    @Override
    public String toString() {
        return "NeedCleanData{" +
//                "id='" + id + '\'' +
//                ", devId='" + devId + '\'' +
                ", weight=" + weight +
                ", garbageType='" + garbageType + '\'' +
//                ", fullTime='" + fullTime + '\'' +
                ", cleanStatus='" + cleanStatus + '\'' +
                ", trashCanFullType=" + trashCanFullType +
                '}';
    }
}
