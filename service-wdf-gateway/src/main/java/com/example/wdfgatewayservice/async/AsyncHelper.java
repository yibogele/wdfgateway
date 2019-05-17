package com.example.wdfgatewayservice.async;

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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.nio.charset.Charset;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 13:34 2018/8/20
 * Modified By:
 */
@Component
public class AsyncHelper {
    private static Logger logger = LoggerFactory.getLogger(AsyncHelper.class);


    @Autowired
    @Qualifier("ShakeHandsService")
    FenleiService shakeHandsService;

    @Autowired
    @Qualifier("DumpRecordService")
    FenleiService dumpRecordService;

    @Autowired
    @Qualifier("CardNumService")
    FenleiService cardNumService;

    @Async
    public void asyncProcess(Protocol protocol, byte[] bytes, DeferredResult<ResponseEntity<byte[]>> deferredResult){
//        logger.info("Thread: {}, start async process", Thread.currentThread().getName());
        byte[] back;

        switch (protocol){
            case SHAKE_HAND:
                back = shakeHandsService.getResponseBytes(bytes);
                break;
            case VALIDATE_CARDNUM:
                back = cardNumService.getResponseBytes(bytes);
                break;
            case DUMP_RECORD:
                back = dumpRecordService.getResponseBytes(bytes);
                break;
            default:
                back = new byte[0];
                break;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        deferredResult.setResult(new ResponseEntity<>(back, headers, HttpStatus.OK));

        logger.info("end async process");
    }
}
