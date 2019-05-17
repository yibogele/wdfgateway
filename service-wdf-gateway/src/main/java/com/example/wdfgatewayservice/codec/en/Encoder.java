package com.example.wdfgatewayservice.codec.en;

import com.example.wdfgatewayservice.constant.BizMapKeys;
import com.example.wdfgatewayservice.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 13:50 2018/8/13
 * Modified By:
 */
public abstract class Encoder {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 存放encode的结果，init()负责初始化
    protected int[] result;

    // 业务数据
    protected Map<String, Object> bizData;

    public void setBizData(Map<String, Object> bizData) {
        this.bizData = bizData;
    }

    public boolean encode(){
        if(init()){

            return encodeCommon() &&  encodeBody();
        }
        return false;
    }

    protected boolean encodeCommon(){
        if (result == null || result.length == 0)
        {
            return false;
        }

        // build common response packet
        result[0] = 66; // B
        result[1] = 65;  // A

        // 包长度
        result[2] = CommonUtil.getLow8Bit((short) result.length);
        result[3] = CommonUtil.getHigh8Bit((short) result.length);

        // 时间
        LocalDateTime now = LocalDateTime.now();
        result[4] = now.getYear() % 100;
        result[5] = now.getMonthValue();
        result[6] = now.getDayOfMonth();
        result[7] = now.getHour();
        result[8] = now.getMinute();
        result[9] = now.getSecond();

        // 通讯状态
//        String commStatus = (String) bizData.get(BizMapKeys.COMM_STATUS);
        String commStatus = "0";
        result[10] = Integer.parseInt(commStatus);

        // 业务状态
//        String bizStatus = (String)bizData.get(BizMapKeys.BIZ_STATUS);
        String bizStatus = "0";
        result[11] = Integer.parseInt(bizStatus);


        return true;
    }

    public int[] getResult() {
        return result;
    }

    protected abstract boolean init();
    protected abstract boolean encodeBody();
}
