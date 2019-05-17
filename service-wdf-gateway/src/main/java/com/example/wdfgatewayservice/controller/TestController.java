package com.example.wdfgatewayservice.controller;

import com.example.wdfgatewayservice.mock.MockDevRespDataBuiler;
import com.example.wdfgatewayservice.publish.KafkaProducer;
import com.example.wdfgatewayservice.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:38 2018/8/16
 * Modified By:
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "fancx") String name) {
        return "Hello " + name + ", Iam from WDF gateway, port=" + port;
    }

    @RequestMapping(value = "/shakehand")
    public String shakehand(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "value", defaultValue = "65,66,65,0," +
                                    "67,34,113,38,53,49,71,77,5,-40,-1,52,-119,-122,6,23,4," +
                                    "0,37,-125,120,103,-93,-101,-58,6,0,-110,18,2,25,0,0,0," +
                                    "0,0,0,0,0,0,0,0,44,44,0,0,2,0,0,36,23,-48,6,0,0,36,0," +
                                    "110,8,-75,-43") String value) {
        return "shakehand: \n" + value;
    }


    /**
     * test kafka
     */
    @RequestMapping(value = "/kafka")
    public String handleKafka(HttpServletRequest request, HttpServletResponse response) {

        KafkaProducer.getInstance().sendMsg("test", "testFromWonderfulControler".getBytes(), "");

        return "done";
    }

    @RequestMapping(value = "/shakehands", method = RequestMethod.POST)
    @ResponseBody
    public String handleShakeHandsJson(@RequestBody String value) {
        String retJson = JsonUtil.obj2json(MockDevRespDataBuiler.buildShakeHandsData());
        return value;
    }

    @RequestMapping(value = "/shakehands", method = RequestMethod.GET)
    @ResponseBody
    public String handleShakeHands(@RequestParam(value = "devid") String devid){
        return JsonUtil.obj2json(MockDevRespDataBuiler.buildShakeHandsData());
    }



    @RequestMapping(value = "/dumprecord", method = RequestMethod.POST)
    @ResponseBody
    public String handleDumpRecordJson(@RequestBody String value) {

        return JsonUtil.obj2json(MockDevRespDataBuiler.buildShakeHandsData());
//        return value;
    }

    @RequestMapping(value = "/cardnum", method = RequestMethod.GET)
    @ResponseBody
    public String handleCardNumJson(@RequestBody String value) {

        return value;
    }

//    private Map<String, Object> getShakeHandsMapData(){
//        Map<String, Object> data = new HashMap<>();
//
//        return data;
//    }
//
//    private Map<String, Object> getDumpRecordMapData(){
//        Map<String, Object> data = new HashMap<>();
//
//        return data;
//    }
//
//    private Map<String, Object> getCardNumMapData(){
//        Map<String, Object> data = new HashMap<>();
//
//        return data;
//    }

}
