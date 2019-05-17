package com.example.wdfgatewayservice.constant;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 10:40 2018/8/14
 * Modified By:
 */
public enum TrashCanFullType {
    TRASHCAN_FULL_VOLUME("full volume", 1),
    TRASHCAN_FULL_WEIGHT("over weight",2),
    TRASHCAN_FULL_VOLUME_WEIGHT("full volume and over weight",3),

    ;

    TrashCanFullType(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    private String desc;
    private int code;

    public String getDesc() {
        return desc;
    }

    public static TrashCanFullType getByCode(int code){
        switch (code){
            case 1:
                return TRASHCAN_FULL_VOLUME;
            case 2:
                return TRASHCAN_FULL_WEIGHT;
            case 3:
                return TRASHCAN_FULL_VOLUME_WEIGHT;
            default:
                return TRASHCAN_FULL_VOLUME;
        }
    }

    public int getCode() {
        return code;
    }


    @Override
    public String toString() {
        return "TrashCanFullType{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}
