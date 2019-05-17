package com.example.wdfgatewayservice.httpclient;

import com.example.wdfgatewayservice.constant.Protocol;
import com.example.wdfgatewayservice.httpclient.param.CardNumHttpParam;
import com.example.wdfgatewayservice.httpclient.param.DumpRecordHttpParam;
import com.example.wdfgatewayservice.httpclient.param.HttpParam;
import com.example.wdfgatewayservice.httpclient.param.ShakeHandsHttpParam;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:18 2018/8/16
 * Modified By:
 */
public final class HttpParamBuilder {
    public static HttpParam getByProtocol(Protocol protocol){
        switch (protocol){
            case DUMP_RECORD:
                return new DumpRecordHttpParam();
            case SHAKE_HAND:
                return new ShakeHandsHttpParam();
            case VALIDATE_CARDNUM:
                return new CardNumHttpParam();
            default:
                return null;
        }
    }
}
