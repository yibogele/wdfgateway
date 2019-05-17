package com.example.wdfgatewayservice.model;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:06 2018/8/13
 * Modified By:
 */
public class CardNumRequestData {
    private CommonRequestData commonRequestData;
    private String deviceId;
    private String cardNum;
    private String createTime;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public CommonRequestData getCommonRequestData() {
        return commonRequestData;
    }

    public void setCommonRequestData(CommonRequestData commonRequestData) {
        this.commonRequestData = commonRequestData;
    }


    @Override
    public String toString() {
        return "CardNumRequestData{" +
                "commonRequestData=" + commonRequestData +
                ", deviceId='" + deviceId + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
