package com.example.wdfgatewayservice.constant;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:01 2018/8/14
 * Modified By:
 */
public enum BizStatus {
    BIZ_STATUS_SUCCESS("success", 0),
    BIZ_STATUS_DEV_NOT_REG("device not registered", 1),
    BIZ_STATUS_SERVER_ERROR("server error", 2),
    BIZ_STATUS_CARDNUM_ERROR("card number authentication error", 3),
    ;
    private String desc;
    private int code;

    public static BizStatus getByCode(int code){
        switch (code){
            case 0:
                return BIZ_STATUS_SUCCESS;
            case 1:
                return BIZ_STATUS_DEV_NOT_REG;
            case 2:
                return BIZ_STATUS_SERVER_ERROR;
            case 3:
                return BIZ_STATUS_CARDNUM_ERROR;
            default:
                return BIZ_STATUS_SUCCESS;
        }
    }

    public String getDesc() {
        return desc;
    }


    public int getCode() {
        return code;
    }


    BizStatus(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    @Override
    public String toString() {
        return "BizStatus{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}
