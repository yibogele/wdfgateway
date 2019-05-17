package com.example.wdfgatewayservice.codec.de;

import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.model.CardNumRequestData;
import com.example.wdfgatewayservice.util.ConverterUtil;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:32 2018/8/13
 * Modified By:
 */
public class CardNumDecoder extends Decoder<CardNumRequestData> {
    @Override
    public boolean decodeBody(int[] cardNumInts) {
        result = new CardNumRequestData();

        result.setCommonRequestData(commonRequestData);

        // 数据时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        result.setCreateTime(createTime);

        // device id
        byte[] binIdBytes = ConverterUtil.substringBytes(cardNumInts,
                Constants.MB.FENLEI_DEVID_START,
                Constants.MB.FENLEI_DEVID_END+1);
        String devId = ConverterUtil.bytesToHexString(binIdBytes).toUpperCase();
        result.setDeviceId(devId);

        // card num, CRC2位，卡号末尾0,
        StringBuffer sbCardNum = new StringBuffer();
        for (int i = 16; i< cardNumInts.length-2; i++){

            if( Character.isLetterOrDigit(cardNumInts[i]))
                sbCardNum.append((char)cardNumInts[i]);
        }
        String strCardNum = sbCardNum.toString();
        result.setCardNum(strCardNum);


        return true;
    }
}
