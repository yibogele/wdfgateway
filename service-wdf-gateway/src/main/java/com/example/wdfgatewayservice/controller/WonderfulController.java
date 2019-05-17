package com.example.wdfgatewayservice.controller;

import com.example.wdfgatewayservice.async.AsyncHelper;
import com.example.wdfgatewayservice.constant.Protocol;
import com.example.wdfgatewayservice.service.FenleiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:45 2018/8/10
 * Modified By:
 */
@RestController
@RequestMapping(value = "/wdf")
public class WonderfulController {
    private final static Logger logger = LoggerFactory.getLogger(WonderfulController.class);

    @Autowired
    @Qualifier("ShakeHandsService")
    FenleiService shakeHandsService;

    @Autowired
    @Qualifier("DumpRecordService")
    FenleiService dumpRecordService;

    @Autowired
    @Qualifier("CardNumService")
    FenleiService cardNumService;


    @Autowired
    AsyncHelper asyncHelper;

    /**
     * 同步
     * @param request
     * @param response
     */
    @RequestMapping(value = "/shake", method = RequestMethod.POST)
    public void shake(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        shakeHandsService.handleRequest(request, response);

    }

    /**
     * 同步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/shakev0", method = RequestMethod.POST)
    public ResponseEntity<byte[]> shakev0(@RequestBody byte[] bytes){
        byte[] back = shakeHandsService.getResponseBytes(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        return new ResponseEntity<>(back, headers, HttpStatus.OK);
    }

    /**
     * 异步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/shakev1", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseEntity<StreamingResponseBody> shakev1(@RequestBody byte[] bytes){
        StreamingResponseBody streamingResponseBody = shakeHandsService.getResponse(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));

        return new ResponseEntity<>(streamingResponseBody, headers, HttpStatus.OK);
    }

    /**
     * 异步
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/shakev2", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<byte[]>> shakev2(@RequestBody byte[] bytes){
//        logger.info("Thread: {}, start shakev2", Thread.currentThread().getName());
        // 超时时间为10s，超时返回"ERROR"。
        DeferredResult<ResponseEntity<byte[]>> deferredResult =
                new DeferredResult<>(10000L,
                        new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR));


        asyncHelper.asyncProcess(Protocol.SHAKE_HAND, bytes, deferredResult);


        return deferredResult;
    }

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/dump", method = RequestMethod.POST)
    public void dumpRecord(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        dumpRecordService.handleRequest(request, response);
    }

    /**
     * 同步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/dumpv0", method = RequestMethod.POST)
    public ResponseEntity<byte[]> dumpv0(@RequestBody byte[] bytes){
        byte[] back = dumpRecordService.getResponseBytes(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
//        headers.setContentType(MediaType.parseMediaType("text/html;charset=UTF-8"));
        return new ResponseEntity<>(back, headers, HttpStatus.OK);
    }

    /**
     * 异步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/dumpv1", method = RequestMethod.POST)
    public ResponseEntity<StreamingResponseBody> dumpv1(@RequestBody byte[] bytes){
        StreamingResponseBody streamingResponseBody = dumpRecordService.getResponse(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(streamingResponseBody, headers, HttpStatus.OK);
    }

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public void validateCardNum(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");

        cardNumService.handleRequest(request, response);
    }

    /**
     * 同步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/cardv0", method = RequestMethod.POST)
    public ResponseEntity<byte[]> cardv0(@RequestBody byte[] bytes){
        byte[] back = cardNumService.getResponseBytes(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
//        headers.setContentType(MediaType.parseMediaType("text/html;charset=UTF-8"));
        return new ResponseEntity<>(back, headers, HttpStatus.OK);
    }

    /**
     * 异步处理
     * @param bytes
     * @return
     */
    @RequestMapping(value = "/cardv1", method = RequestMethod.POST)
    public ResponseEntity<StreamingResponseBody> cardv1(@RequestBody byte[] bytes){
        StreamingResponseBody streamingResponseBody = cardNumService.getResponse(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(streamingResponseBody, headers, HttpStatus.OK);
    }
}
