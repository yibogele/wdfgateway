package com.example.servicefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:33 2018/8/10
 * Modified By:
 */
@FeignClient(value = "service-wdf-gateway/wdf")
public interface ScheduleGatewayService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHelloFromGateway(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/shakev2", method = RequestMethod.POST)
    ResponseEntity<StreamingResponseBody> handleShakeHands( InputStream in);

    @RequestMapping(value = "/shakev1", method = RequestMethod.POST)
    ResponseEntity<byte[]> handleShakeHandsv1(InputStream inputStream);

//    @RequestMapping(value = "/dump", method = RequestMethod.POST)
//    void handleDumpRecord(@RequestBody HttpServletRequest request, HttpServletResponse response);
//
//
//    @RequestMapping(value = "/cardnum", method = RequestMethod.POST)
//    void handleCardNum(@RequestBody HttpServletRequest request, HttpServletResponse response);
}
