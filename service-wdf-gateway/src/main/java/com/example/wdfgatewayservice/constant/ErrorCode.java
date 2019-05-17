package com.example.wdfgatewayservice.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:00 2018/8/13
 * Modified By:
 */
public enum ErrorCode {
    MB_PARSE_STATUS_SUCCESS("Decode Success",0),
    MB_PARSE_STATUS_CRC("CRC check failed", 1),
    MB_PARSE_STATUS_QSM("QSM check failed", 2),
    MB_PARSE_STATUS_CG("", 3),
    MB_PARSE_STATUS_CD("", 4),

    // 垃圾桶
    TRASHCAN_FAULT_TEMP_SENSOR("Temperature sensor disconnected", 5),
    TRASHCAN_FAULT_TOP_LIMIT("Top limit error", 6),
    TRASHCAN_FAULT_BOTTOM_LITMIT("Bottom limit error",7),
    TRASHCAN_FAULT_VOLUME_SENSOR("volume sensor disconnected", 8),
    TRASHCAN_FAULT_WEIGHT_SENSOR("weight sensor disconnected", 9),
    TRASHCAN_FAULT_MOTOR_DISCONNECT("electric motor disconnected", 10),
    TRASHCAN_FAULT_MOTOR_SHORTCUT("electric motor disconnected", 10),

    MB_PARSE_STATUS_NULL("Device Data Is Null", 1024),
    ;

    private String desc;
    private int resultCode;

    private ErrorCode(String desc, int resultCode){
        this.desc = desc;
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "desc='" + desc + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }
}
