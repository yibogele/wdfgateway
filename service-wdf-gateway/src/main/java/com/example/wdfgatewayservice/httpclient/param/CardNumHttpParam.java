package com.example.wdfgatewayservice.httpclient.param;

import com.example.wdfgatewayservice.model.CardNumRequestData;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:13 2018/8/16
 * Modified By:
 */
public class CardNumHttpParam extends HttpParam<CardNumRequestData> {
    @Override
    public String getParamFrom(CardNumRequestData requestData) {
        StringBuffer sb = new StringBuffer();

//        String strUrl = ServerConfig.getInstance().getShakeHandsURL();
//        sb.append(strUrl);

        // TODO
        // 构造URL参数
//        sb.append(requestData.getDeviceId());
        sb.append(requestData.getCardNum());

        return sb.toString();
    }
}
