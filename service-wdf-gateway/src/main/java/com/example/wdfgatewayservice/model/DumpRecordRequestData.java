package com.example.wdfgatewayservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:07 2018/8/13
 * Modified By:
 */
public class DumpRecordRequestData {

    private String deviceId;
    private String createTime;

    private CommonRequestData commonRequestData;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private List<DumpRecordData> dumpRecordDataList = new ArrayList<>();

    public void addDumpRecord(DumpRecordData dumpRecordData){
        dumpRecordDataList.add(dumpRecordData);
    }

    public List<DumpRecordData> getDumpRecordDataList() {
        return dumpRecordDataList;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public CommonRequestData getCommonRequestData() {
        return commonRequestData;
    }

    public void setCommonRequestData(CommonRequestData commonRequestData) {
        this.commonRequestData = commonRequestData;
    }

    @Override
    public String toString() {
        return "DumpRecordRequestData{" +
                "deviceId='" + deviceId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", commonRequestData=" + commonRequestData +
                ", dumpRecordDataList=" + dumpRecordDataList +
                '}';
    }
}
