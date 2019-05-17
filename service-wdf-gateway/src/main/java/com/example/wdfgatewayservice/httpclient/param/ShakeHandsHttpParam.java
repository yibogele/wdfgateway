package com.example.wdfgatewayservice.httpclient.param;

import com.example.wdfgatewayservice.config.ServerConfig;
import com.example.wdfgatewayservice.model.ShakeHandRequestData;
import com.example.wdfgatewayservice.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:12 2018/8/16
 * Modified By:
 */
public class ShakeHandsHttpParam extends HttpParam<ShakeHandRequestData> {
    @Override
    public String getParamFrom(ShakeHandRequestData requestData) {
        StringBuffer sb = new StringBuffer();

        /**
        // 传json字符串
//        String jsonData = JsonUtil.obj2json(requestData);
//        logger.info("jsonData: {}", jsonData);

        //
//        sb.append(jsonData);
         */

        // 改为只穿devid
        sb.append(requestData.getDeviceId());

        return sb.toString();
    }
}
