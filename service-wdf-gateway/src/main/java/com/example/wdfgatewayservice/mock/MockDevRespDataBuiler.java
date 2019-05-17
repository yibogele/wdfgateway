package com.example.wdfgatewayservice.mock;

import com.example.wdfgatewayservice.constant.BizMapKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MockDevRespDataBuiler {
    public static Map<String, Object> buildShakeHandsData(){
        Map<String, Object> retMap = new HashMap<>();

        retMap.put(BizMapKeys.COMM_STATUS, "0");
        retMap.put(BizMapKeys.BIZ_STATUS, "0");
        retMap.put(BizMapKeys.ORG_CODE, "012");
        retMap.put(BizMapKeys.UNIT_CODE, "AAAA");
        retMap.put(BizMapKeys.AD_SCREEN_OPEN, "07:00");
        retMap.put(BizMapKeys.AD_SCREEN_CLOSE, "22:00");
        retMap.put(BizMapKeys.AD_LED_OPEN, "18:00");
        retMap.put(BizMapKeys.AD_LED_CLOSE, "06:00");
        retMap.put(BizMapKeys.QR_CODE_LIGHTING_OPEN, "18:00");
        retMap.put(BizMapKeys.QR_CODE_LIGHTING_CLOSE, "06:00");
        retMap.put(BizMapKeys.WARNING_TONE_OPEN,"07:00");
        retMap.put(BizMapKeys.WARNING_TONE_CLOSE, "09:00");
        retMap.put(BizMapKeys.WIFI_OPEN, "07:00");
        retMap.put(BizMapKeys.WIFI_CLOSE, "09:00");
        retMap.put(BizMapKeys.DISINFECT_CYCLE, "10");
        retMap.put(BizMapKeys.DISINFECT_DURATION, "1");
        retMap.put(BizMapKeys.SHAKE_HANDS_CYCLE,"60");

        // 垃圾桶
        List<Map<String, String>> trashList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Map<String, String> trashCan = new HashMap<>();

            trashCan.put(BizMapKeys.TRASHCAN_TYPE, ""+i);
            trashCan.put(BizMapKeys.TRASHCAN_FULL_TRHESHOLD, "9" + i);
            trashCan.put(BizMapKeys.TRASHCAN_OVERWEIGHT_TRHESHOLD, "102"+i);

            trashList.add(trashCan);
        }
        retMap.put(BizMapKeys.TRASH_CANS, trashList);

        return retMap;
    }

//    public static
}
