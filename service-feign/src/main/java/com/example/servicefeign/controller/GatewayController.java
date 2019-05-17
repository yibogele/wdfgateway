package com.example.servicefeign.controller;

import com.example.servicefeign.service.ScheduleGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:36 2018/8/10
 * Modified By:
 */
@RestController
public class GatewayController {

    @Autowired
    ScheduleGatewayService scheduleGatewayService;

    @GetMapping(value = "/hello")
    public String sayHelloFromGateway(@RequestParam String name){
        return scheduleGatewayService.sayHelloFromGateway(name);
    }


    @PostMapping(value = "/shakehands")
    public ResponseEntity<StreamingResponseBody> handleShakeHands(InputStream request){
        return scheduleGatewayService.handleShakeHands(request);
    }

    @PostMapping(value = "/shakehandsv1")
    public ResponseEntity<byte[]> handleShakeHandsv1(InputStream request){
        return scheduleGatewayService.handleShakeHandsv1(request);
    }


//    @PostMapping(value = "/dumprecord")
//    public void handleDumpRecord(HttpServletRequest request, HttpServletResponse response){
//        scheduleGatewayService.handleDumpRecord(request, response);
//    }
//
//    @PostMapping(value = "/cardnum")
//    public void handleCardNum(HttpServletRequest request, HttpServletResponse response){
//        scheduleGatewayService.handleCardNum(request, response);
//    }
}
