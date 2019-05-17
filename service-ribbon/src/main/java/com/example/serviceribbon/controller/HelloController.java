package com.example.serviceribbon.controller;

import com.example.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:55 2018/8/8
 * Modified By:
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }


    @PostMapping(value = "/shakev0")
    public ResponseEntity<byte[]> shakev0(@RequestBody byte[] bytes){
        return helloService.shakeHandsV0(bytes);
    }

    @PostMapping(value = "/shakev1")
//    @ResponseBody
    public ResponseEntity<StreamingResponseBody> shakev1(@RequestBody byte[] bytes){
        return helloService.shakeHandsV1(bytes);
    }


    @PostMapping(value = "/shakev2")
    public DeferredResult<ResponseEntity<byte[]>> shakev2(@RequestBody byte[] bytes){
        return helloService.shakeHandsV2(bytes);
    }
}
