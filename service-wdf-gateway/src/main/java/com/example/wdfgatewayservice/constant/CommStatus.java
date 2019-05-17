package com.example.wdfgatewayservice.constant;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:01 2018/8/14
 * Modified By:
 */
public enum CommStatus {

    COMM_STATUS_NORMAL("communication/parse normal", 0),
    COMM_STATUS_PACK_LENGTH_ERROR("packet length not match", 1),
    COMM_STATUS_START_CODE_ERROR("start code not match", 2),
    COMM_STATUS_CRC_ERROR("crc validation error",3),
    COMM_STATUS_DATA_PARSE_ERROR("data parse error",4),
    ;
    private String desc;
    private int code;

    public static CommStatus getByCode(int code){
        switch (code){
            case 0:
                return COMM_STATUS_NORMAL;
            case 1:
                return COMM_STATUS_PACK_LENGTH_ERROR;
            case 2:
                return COMM_STATUS_START_CODE_ERROR;
            case 3:
                return COMM_STATUS_CRC_ERROR;
            case 4:
                return COMM_STATUS_DATA_PARSE_ERROR;
            default:
                return COMM_STATUS_NORMAL;
        }
    }

    CommStatus(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }


    public int getCode() {
        return code;
    }


    @Override
    public String toString() {
        return "CommStatus{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}
