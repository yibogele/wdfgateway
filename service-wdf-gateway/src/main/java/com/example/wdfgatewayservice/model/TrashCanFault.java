package com.example.wdfgatewayservice.model;

import com.example.wdfgatewayservice.constant.ErrorCode;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 9:31 2018/8/14
 * Modified By:
 */
public class TrashCanFault {

    ErrorCode errorCode;

//    String id;

//    String errorTime;
    String createUser;
//    String devId;
    String garbageType;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public String getErrorTime() {
//        return errorTime;
//    }

//    public void setErrorTime(String errorTime) {
//        this.errorTime = errorTime;
//    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

//    public String getDevId() {
//        return devId;
//    }

//    public void setDevId(String devId) {
//        this.devId = devId;
//    }

    public String getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(String garbageType) {
        this.garbageType = garbageType;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
