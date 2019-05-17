package com.example.wdfgatewayservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:14 2018/8/13
 * Modified By:
 */
public class CommonUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil(){}

    public static int concatLowHigh(int low, int high){
        return high << 8 | low;
    }

    public static byte getLow8Bit(short val){
        return (byte) (val&0xff);
    }

    public static byte getHigh8Bit(short val){
        return (byte) (val>>8 & 0xff);
    }

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
