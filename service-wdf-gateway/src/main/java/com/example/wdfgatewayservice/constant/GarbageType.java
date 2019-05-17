package com.example.wdfgatewayservice.constant;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 17:00 2018/8/14
 * Modified By:
 */
public enum GarbageType {
    GT_KITCHEN(Constants.GarbageTypeStr.GBT_KITCHEN, 0),
    GT_PAPER(Constants.GarbageTypeStr.GBT_PAPER, 1),
    GT_METAL(Constants.GarbageTypeStr.GBT_METAL, 2),
    GT_PLASTIC(Constants.GarbageTypeStr.GBT_PLASTIC, 3),
    GT_GLASS(Constants.GarbageTypeStr.GBT_GLASS, 4),
    GT_OTHER(Constants.GarbageTypeStr.GBT_OTHER, 5),
    GT_RECYCLE(Constants.GarbageTypeStr.GBT_RECYCLE, 6),
    GT_HARMFUL(Constants.GarbageTypeStr.GBT_HARMFUL, 7),
    GT_CLOTH(Constants.GarbageTypeStr.GBT_CLOTH, 8),
    ;

    private String desc;
    private int code;

    public static GarbageType getByCode(int code) {
        switch (code){
            case 0:
                return GT_KITCHEN;
            case 1:
                return GT_PAPER;
            case 2:
                return GT_METAL;
            case 3:
                return GT_PLASTIC;
            case 4:
                return GT_GLASS;
            case 5:
                return GT_OTHER;
            case 6:
                return GT_RECYCLE;
            case 7:
                return GT_HARMFUL;
            case 8:
                return GT_CLOTH;
            default:
                return GT_OTHER;
        }
    }

    GarbageType(String desc, int code) {
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
        return "GarbageType{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}
