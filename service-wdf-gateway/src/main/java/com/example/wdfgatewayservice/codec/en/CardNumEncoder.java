package com.example.wdfgatewayservice.codec.en;

import com.example.wdfgatewayservice.util.CommonUtil;
import com.example.wdfgatewayservice.util.CrcUtil;

import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:58 2018/8/13
 * Modified By:
 */
public class CardNumEncoder extends Encoder {

    /**
     *
     * @return
     */
    @Override
    protected boolean init() {
        if (bizData == null || bizData.size() == 0){

            logger.error("No data to encode");
            return false;
        }
        Map<String, String> dataMap = (Map<String, String>)bizData.get("data");
        String strCardNum = dataMap.get("cardNumber");

        result = new int[15+ strCardNum.length()];
        return true;
    }


    /**
     *
     * @return
     */
    @Override
    public boolean encodeBody() {

        Map<String, String> dataMap = (Map<String, String>)bizData.get("data");
        String strCardNum = dataMap.get("cardNumber");

        char[] cardNums = strCardNum.toCharArray();
        for (int i = 0; i < cardNums.length; i++) {
            result[12+i] = cardNums[i];
        }
//        result[result.length-3] = 0;

        // CRC
        int crc = CrcUtil.buildCRC(result);

        result[result.length-2] = CommonUtil.getLow8Bit((short)crc);
        result[result.length-1] = CommonUtil.getHigh8Bit((short)crc);

        logger.info("{}: Encode Result with CRC: {}",getClass().getName(), result);

        return true;
    }
}
