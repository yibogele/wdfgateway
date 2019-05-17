package com.example.wdfgatewayservice.codec.de;

import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.model.DumpRecordData;
import com.example.wdfgatewayservice.model.DumpRecordRequestData;
import com.example.wdfgatewayservice.util.CommonUtil;
import com.example.wdfgatewayservice.util.ConverterUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:32 2018/8/13
 * Modified By:
 */
public class DumpRecordDecoder extends Decoder<DumpRecordRequestData> {
    @Override
    public boolean decodeBody(int[] dumpRecordInts) {
        result = new DumpRecordRequestData();
//        DumpRecordRequestData dumpRecordRequestData = (DumpRecordRequestData)result;

        result.setCommonRequestData(commonRequestData);

        // 数据时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        result.setCreateTime(createTime);

        //设备id
        byte[] binIdBytes = ConverterUtil.substringBytes(dumpRecordInts,
                Constants.MB.FENLEI_DEVID_START,
                Constants.MB.FENLEI_DEVID_END+1);
        String devId = ConverterUtil.bytesToHexString(binIdBytes).toUpperCase();
        result.setDeviceId(devId);

        // 垃圾桶记录
        int idxRecord = 16;
        int cntRecord = 0;
        while (idxRecord+ dumpRecordInts[idxRecord] + 2 <= dumpRecordInts.length){
            DumpRecordData dumpRecordData = new DumpRecordData();

            // 记录的长度
            int recordLength = dumpRecordInts[idxRecord];

            // 垃圾类型
            String garbageType = String.valueOf(dumpRecordInts[idxRecord+1]);
            dumpRecordData.setType(garbageType);

            // 重量,单位10g
            // 实际重量为该数值*10，单位为g
            int weight_low = dumpRecordInts[idxRecord+ 4];
            int weight_high = dumpRecordInts[idxRecord + 5];
            int weight = CommonUtil.concatLowHigh(weight_low, weight_high);
            dumpRecordData.setWeight(weight*10);

            // 时间
            int year = dumpRecordInts[idxRecord+6];
            int month = dumpRecordInts[idxRecord+7];
            int day = dumpRecordInts[idxRecord+8];
            int hour = dumpRecordInts[idxRecord+9];
            int minute = dumpRecordInts[idxRecord+10];
            int second = dumpRecordInts[idxRecord+11];

            year += 2000;

//            Calendar calendar = Calendar.getInstance();
//            calendar.set(year, month, day, hour, minute, second);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDateTime = dateFormat.format(calendar.getTime());
            String strDateTime = String.format("%d-%02d-%02d %02d:%02d:%02d",
                    year, month, day, hour, minute,second);
            dumpRecordData.setTime(strDateTime);

            // 用户账号
            StringBuffer sbUserId = new StringBuffer();

            // 去除本条记录末尾的0
            for (int i = idxRecord+12; i < idxRecord+recordLength-1; i++){
                sbUserId.append((char)dumpRecordInts[i]);
            }
            String strUserId = sbUserId.toString();
            dumpRecordData.setUserId(strUserId);

            // 添加到结果中
            result.addDumpRecord(dumpRecordData);

            // 更新index到下一条记录
            idxRecord += dumpRecordInts[idxRecord];
            cntRecord++;
        }

        return true;
    }
}
