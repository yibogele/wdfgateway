package com.example.wdfgatewayservice.codec.en;

import com.example.wdfgatewayservice.constant.BizMapKeys;
import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.mock.MockDevRespDataBuiler;
import com.example.wdfgatewayservice.util.CommonUtil;
import com.example.wdfgatewayservice.util.CrcUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:58 2018/8/13
 * Modified By:
 */
public class ShakeHandEncoder extends Encoder {
//    protected static Logger logger = LoggerFactory.getLogger(ShakeHandEncoder.class);

    private Map<String, Object> dataMap;
    /**
     *
     * @return
     */
    @Override
    protected boolean init() {
        // Get Business data
        if (bizData == null || bizData.size() == 0){

            logger.error("No data to encode");
            return false;
        }

        dataMap = (Map<String, Object>) bizData.get("data");
//        String canSizeKey = "barrelCount";

        // // 包长度 = 45 + 垃圾桶个数*4;
//        List<Map<String, String>> trashCanList =
//                (List<Map<String, String>>)bizData.get(BizMapKeys.TRASH_CANS);
//        result = new int[45+ trashCanList.size()*4];
        int trashCanSize =  (int) dataMap.get(BizMapKeys.TRASHCAN_COUNT);
        result = new int[45 + trashCanSize*4];

        // TODO
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean encodeBody() {


        // 业主id
        String orgCode = (String)dataMap.get(BizMapKeys.ORG_CODE);
        orgCode = StringUtils.isEmpty(orgCode) ? "012" : orgCode;
        orgCode += "0";

        char[] orgCodes = orgCode.toCharArray();
        for (int i = 0; i < orgCodes.length; i++){
            result[12+i] = orgCodes[i];
        }

        // 单位id
        String unitCode = (String)dataMap.get(BizMapKeys.UNIT_CODE);
        unitCode = StringUtils.isEmpty(unitCode) ? "AAAA" : unitCode;
        char[] unitCodes = unitCode.toCharArray();
        for (int i = 0; i < unitCodes.length; i++) {
            result[16+i] = unitCodes[i];
        }

        // 广告屏打开
        String adScrOpen = (String)dataMap.get(BizMapKeys.AD_SCREEN_OPEN);
        adScrOpen = StringUtils.isEmpty(adScrOpen) ? "07:00" : adScrOpen;

        String[] adScrOpenPis = StringUtils.split(adScrOpen, ":");
        result[20] = Integer.parseInt(adScrOpenPis[1]);
        result[21] = Integer.parseInt(adScrOpenPis[0]);

        // 广告屏关闭
        String adScrClose = (String)dataMap.get(BizMapKeys.AD_SCREEN_CLOSE);
        adScrClose = StringUtils.isEmpty(adScrClose) ? "22:00" : adScrClose;

        String[] adScrClosePis = StringUtils.split(adScrClose, ":");
        result[22] = Integer.parseInt(adScrClosePis[1]);
        result[23] = Integer.parseInt(adScrClosePis[0]);

        // 广告灯打开
        String adLedOpen = (String)dataMap.get(BizMapKeys.AD_LED_OPEN);
        adLedOpen = StringUtils.isEmpty(adLedOpen) ? "18:00" : adLedOpen;

        String[] adLedOpenPs = StringUtils.split(adLedOpen, ":");
        result[24] = Integer.parseInt(adLedOpenPs[1]);
        result[25] = Integer.parseInt(adLedOpenPs[0]);

        // 广告灯关闭
        String adLedClose = (String)dataMap.get(BizMapKeys.AD_LED_CLOSE);
        adLedClose = StringUtils.isEmpty(adLedClose) ? "06:00" : adLedClose;

        String[] adLedClosePs = StringUtils.split(adLedClose, ":");
        result[26] = Integer.parseInt(adLedClosePs[1]);
        result[27] = Integer.parseInt(adLedClosePs[0]);

        // 二维码照明打开
        String qrCodeOpen = (String)dataMap.get(BizMapKeys.QR_CODE_LIGHTING_OPEN);
        qrCodeOpen = StringUtils.isEmpty(qrCodeOpen) ? "18:00" : qrCodeOpen;

        String[] qrCodeOpens = StringUtils.split(qrCodeOpen, ":");
        result[28] = Integer.parseInt(qrCodeOpens[1]);
        result[29] = Integer.parseInt(qrCodeOpens[0]);

        // 二维码照明关闭
        String qrCodeClose = (String)dataMap.get(BizMapKeys.QR_CODE_LIGHTING_CLOSE);
        qrCodeClose = StringUtils.isEmpty(qrCodeClose) ? "06:00" : qrCodeClose;

        String[] qrCodeCloses = StringUtils.split(qrCodeClose, ":");
        result[30] = Integer.parseInt(qrCodeCloses[1]);
        result[31] = Integer.parseInt(qrCodeCloses[0]);

        // 提示音打开
        String warnToneOpen = (String)dataMap.get(BizMapKeys.WARNING_TONE_OPEN);
        warnToneOpen = StringUtils.isEmpty(warnToneOpen) ? "07:00" : warnToneOpen;

        String[] warnToneOpens = StringUtils.split(warnToneOpen, ":");
        result[32] = Integer.parseInt(warnToneOpens[1]);
        result[33] = Integer.parseInt(warnToneOpens[0]);

        // 提示音关闭
        String warnToneClose = (String)dataMap.get(BizMapKeys.WARNING_TONE_CLOSE);
        warnToneClose = StringUtils.isEmpty(warnToneClose) ? "09:00" : warnToneClose;

        String[] warnToneCloses = StringUtils.split(warnToneClose, ":");
        result[34] = Integer.parseInt(warnToneCloses[1]);
        result[35] = Integer.parseInt(warnToneCloses[0]);

        // wifi热点打开
        String wifiOpen = (String)dataMap.get(BizMapKeys.WIFI_OPEN);
        wifiOpen = StringUtils.isEmpty(wifiOpen) ? "07:00" : wifiOpen;

        String[] wifiOpens = StringUtils.split(wifiOpen, ":");
        result[36] = Integer.parseInt(wifiOpens[1]);
        result[37] = Integer.parseInt(wifiOpens[0]);

        // wifi热点关闭
        String wifiClose = (String)dataMap.get(BizMapKeys.WIFI_CLOSE);
        wifiClose = StringUtils.isEmpty(wifiClose) ? "09:00" : wifiClose;

        String[] wifiCloses = StringUtils.split(wifiClose, ":");
        result[38] = Integer.parseInt(wifiCloses[1]);
        result[39] = Integer.parseInt(wifiCloses[0]);

        // 消毒周期
        String disInfectCycle = (String)dataMap.get(BizMapKeys.DISINFECT_CYCLE);
        disInfectCycle = StringUtils.isEmpty(disInfectCycle) ? "10" : disInfectCycle;
        result[40] = Integer.parseInt(disInfectCycle);

        // 消毒时长
        String disInfectDur = (String)dataMap.get(BizMapKeys.DISINFECT_DURATION);
        disInfectDur = StringUtils.isEmpty(disInfectDur) ? "1" : disInfectDur;
        result[41] = Integer.parseInt(disInfectDur);

        // 握手周期
//        String shakeHandsCycle = (String)dataMap.get(BizMapKeys.SHAKE_HANDS_CYCLE);
//        shakeHandsCycle = StringUtils.isEmpty(shakeHandsCycle) ? "60" : shakeHandsCycle;
//        result[42] = Integer.parseInt(shakeHandsCycle);
        result[42] = (int)dataMap.get(BizMapKeys.SHAKE_HANDS_CYCLE);

        // 垃圾桶列表
//        List<Map<String, String>> trashCanList =
//                (List<Map<String, String>>)bizData.get(BizMapKeys.TRASH_CANS);
//        if (trashCanList != null){
//            for (int i = 0; i < trashCanList.size(); i++) {
//                Map<String, String> trashCanData = trashCanList.get(i);
//
//                result[43 + 4*i] = Integer.parseInt(trashCanData.get(BizMapKeys.TRASHCAN_TYPE));
//                result[43 + 1 + 4*i] = Integer.parseInt(
//                        trashCanData.get(BizMapKeys.TRASHCAN_FULL_TRHESHOLD));
//
//                int owThreshold = Integer.parseInt(
//                        trashCanData.get(BizMapKeys.TRASHCAN_OVERWEIGHT_TRHESHOLD));
//
//                result[43 + 2 + 4*i] = CommonUtil.getLow8Bit((short) owThreshold);
//                result[43 + 3 + 4*i] = CommonUtil.getHigh8Bit((short)owThreshold);
//            }
//        }
        int trashCanSize = (int)dataMap.get(BizMapKeys.TRASHCAN_COUNT);
        for (int i = 0; i < trashCanSize; i++) {
            String prefix = BizMapKeys.TRASHCAN_PREFIX+i;
            result[43 + 4*i] = (int)dataMap.get(prefix+BizMapKeys.TRASHCAN_TYPE);
            result[43 + 1 + 4*i] = (int)dataMap.get(prefix+BizMapKeys.TRASHCAN_FULL_TRHESHOLD);

            int owThreshold = (int)dataMap.get(prefix+BizMapKeys.TRASHCAN_OVERWEIGHT_TRHESHOLD);

            result[43 + 2 + 4*i] = CommonUtil.getLow8Bit((short) owThreshold);
            result[43 + 3 + 4*i] = CommonUtil.getHigh8Bit((short)owThreshold);

        }

        // CRC
        int crc = CrcUtil.buildCRC(result);

        result[result.length-2] = CommonUtil.getLow8Bit((short)crc);
        result[result.length-1] = CommonUtil.getHigh8Bit((short)crc);

        logger.info("{}: Encode Result with CRC: {}",getClass().getName(), result);
        return true;
    }
}
