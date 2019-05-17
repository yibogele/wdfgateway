package com.example.wdfgatewayservice.constant;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:51 2018/8/14
 * Modified By:
 */
public interface BizMapKeys {

    // 包长度
    static final String PACK_LENGTH = "packLength";

    // 通信状态
    static final String COMM_STATUS = "commStatus";

    // 业务状态
    static final String BIZ_STATUS = "bizStatus";


    // 业主id
    static final String ORG_CODE = "ownerId";//"orgCode";

    // 单位id
    static final String UNIT_CODE = "unitId";//"unitCode";

    // 广告屏打开
    static final String AD_SCREEN_OPEN = "screenOpen";//"adScreenOpen";

    // 广告屏关闭
    static final String AD_SCREEN_CLOSE = "screenClose";//"adScreenClose";

    // 广告灯打开
    static final String AD_LED_OPEN = "lightOpen";//"adLedOpen";

    // 广告灯关闭
    static final String AD_LED_CLOSE = "lightClose";//"adLedClose";

    // 二维码照明打开
    static final String QR_CODE_LIGHTING_OPEN = "qrLightOpen";//"qrCodeLightingOpen";

    // 二维码照明关闭
    static final String QR_CODE_LIGHTING_CLOSE = "qrLightClose";//"qrCodeLightingClose";

    // 提示音打开
    static final String WARNING_TONE_OPEN = "promptOpen";//"warningToneOpen";

    // 提示音关闭
    static final String WARNING_TONE_CLOSE = "promptClose";//"warningToneClose";

    // wifi热点打开
    static final String WIFI_OPEN = "wifiOpen";

    // wifi热点关闭
    static final String WIFI_CLOSE = "wifiClose";

    // 消毒周期
    static final String DISINFECT_CYCLE = "disinfectionPeriod";//"disinfectCycle";

    // 消毒时长
    static final String DISINFECT_DURATION = "disinfectionTime";//"disinfectDuration";

    // 握手周期
    static final String SHAKE_HANDS_CYCLE = "heartBeatSeconds";//"shakeHandsCycle";

    static final String TRASHCAN_PREFIX = "barrel";
    static final String TRASHCAN_COUNT = "barrelNumber";
    // 桶类型
    static final String GARBAGE_TYPE = "garbageType";

    // 桶满阈值（％）
    static final String FULL_CAN_THRESHOLD = "fullCanThreshold";

    // 超重阈值(kg)
    static final String OVER_WEIGHT_THRESHOLD = "overWeightThreshold";

    // 垃圾桶
    static final String TRASH_CANS = "trashCans";

    // 垃圾桶类型
    static final String TRASHCAN_TYPE = "Type";//"trashCanType";

    // 垃圾桶 满阈值
    static final String TRASHCAN_FULL_TRHESHOLD = "Full";//"trashCanFullThreshold";

    // 垃圾桶 超重阈值
    static final String TRASHCAN_OVERWEIGHT_TRHESHOLD = "Overweight";//"trashCanOverweightThreshold";


}
