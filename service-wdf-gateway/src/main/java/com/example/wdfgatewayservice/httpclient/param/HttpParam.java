package com.example.wdfgatewayservice.httpclient.param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:09 2018/8/16
 * Modified By:
 */
public abstract class HttpParam<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    public abstract String getParamFrom(T requestData);
}
