package com.example.wdfgatewayservice.httpclient.param;

import com.example.wdfgatewayservice.config.ServerConfig;
import com.example.wdfgatewayservice.model.DumpRecordData;
import com.example.wdfgatewayservice.model.DumpRecordRequestData;
import com.example.wdfgatewayservice.util.JsonUtil;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:13 2018/8/16
 * Modified By:
 */
public class DumpRecordHttpParam extends HttpParam<DumpRecordRequestData> {
    @Override
    public String getParamFrom(DumpRecordRequestData requestData) {
        Map<String ,String> maps = new HashMap<>();
        maps.put("machineCode", requestData.getDeviceId());

        //TODO
        for (DumpRecordData dumpRecordData :
                requestData.getDumpRecordDataList()) {
            // TODO
            maps.put("cardNumber", dumpRecordData.getUserId());
            maps.put("weight"+dumpRecordData.getType(),
                    String.valueOf(dumpRecordData.getWeight()));
        }
//        DumpRecordData dumpRecordData = requestData.getDumpRecordDataList().get(0);
//        Preconditions.checkNotNull(dumpRecordData, "there is no dump record data");
//
//
//        maps.put("cardNumber", dumpRecordData.getUserId());
//        maps.put("weight0", String.valueOf(dumpRecordData.getWeight()));

        //
        String jsonBody = JsonUtil.obj2json(maps);

        return jsonBody;
    }


}
