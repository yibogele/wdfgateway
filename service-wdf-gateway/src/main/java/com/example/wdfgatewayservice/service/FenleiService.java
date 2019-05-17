package com.example.wdfgatewayservice.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 9:39 2018/8/13
 * Modified By:
 */
public interface FenleiService {

    void handleRequest(HttpServletRequest request, HttpServletResponse response);

    StreamingResponseBody getResponse(@RequestBody byte[] bytes);

    byte[] getResponseBytes(@RequestBody byte[] bytes);
}
