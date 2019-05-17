package com.example.wdfgatewayservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 13:56 2018/8/13
 * Modified By:
 */
public class CrcUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrcUtil.class);


    private CrcUtil(){}


    /**
     *
     * @param deviceData
     * @return
     */
    public static boolean validateCRC(int[] deviceData){
        int temp = 0xffff, temp1;
        for (int i = 0; i < deviceData.length - 2; i++) {

            temp ^= deviceData[i];
            for (int j = 0; j < 8; j++) {
                temp1 = temp;
                temp >>= 1;
                if ((temp1 & 0x0001) == 0x0001)
                    temp ^= 0xa001;
            }
        }
        int _crc = deviceData[deviceData.length - 1] << 8 | deviceData[deviceData.length - 2];
        return _crc == temp;

    }

    /**
     *
     * @param deviceData
     * @return
     */
    public static int buildCRC(int[] deviceData){
        int temp = 0xffff, temp1;
        for (int i = 0; i < deviceData.length - 2; i++) {
            temp ^= deviceData[i];
            for (int j = 0; j < 8; j++) {
                temp1 = temp;
                temp >>= 1;
                if ((temp1 & 0x0001) == 0x0001)
                    temp ^= 0xa001;
            }
        }
        return temp;
    }
//    int crc_low = crc & 0xff;//拆成低位
//    int crc_high = crc >> 8;//拆成高位

}
