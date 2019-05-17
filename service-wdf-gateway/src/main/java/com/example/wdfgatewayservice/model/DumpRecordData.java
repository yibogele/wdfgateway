package com.example.wdfgatewayservice.model;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 13:28 2018/8/15
 * Modified By:
 */
public class DumpRecordData {
    private String type;
    private int weight;
    private String time;
    private String userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DumpRecordData{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
