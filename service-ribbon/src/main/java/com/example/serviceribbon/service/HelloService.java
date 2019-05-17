package com.example.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:52 2018/8/8
 * Modified By:
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     *
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        return restTemplate.getForObject("http://SERVICE-WDF-GATEWAY/hi?name="+name, String.class);
    }

    public String hiError(String name){
        return "hi,"+name+",sorry,error!";
    }


    /**
     * 同步处理握手
     */
    @HystrixCommand
    public ResponseEntity<byte[]> shakeHandsV0(@RequestBody byte[] bytes){


        return restTemplate.postForEntity("http://SERVICE-WDF-GATEWAY/wdf/shakev0",
                bytes, byte[].class);
    }

    @HystrixCommand
//    @ResponseBody
    public ResponseEntity<StreamingResponseBody> shakeHandsV1(@RequestBody byte[] bytes){
        return restTemplate.postForEntity("http://SERVICE-WDF-GATEWAY/wdf/shakev1",
                bytes, StreamingResponseBody.class);
    }

    @HystrixCommand
    public DeferredResult<ResponseEntity<byte[]>> shakeHandsV2(@RequestBody byte[] bytes){
        return restTemplate.postForObject("http://SERVICE-WDF-GATEWAY/wdf/shakev2",
                bytes, DeferredResult.class);
    }
}
