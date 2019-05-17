package com.example.wdfgatewayservice.service.impl;

import com.example.wdfgatewayservice.codec.CodecManager;
import com.example.wdfgatewayservice.codec.de.Decoder;
import com.example.wdfgatewayservice.codec.en.Encoder;
import com.example.wdfgatewayservice.constant.Protocol;
import com.example.wdfgatewayservice.httpclient.HttpBizClient;
import com.example.wdfgatewayservice.model.ShakeHandRequestData;
import com.example.wdfgatewayservice.service.FenleiService;
import com.example.wdfgatewayservice.util.ConverterUtil;
import com.example.wdfgatewayservice.util.CrcUtil;
import com.example.wdfgatewayservice.util.IoUtil;
import com.example.wdfgatewayservice.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 17:05 2018/8/15
 * Modified By:
 */
@Service(value = "ShakeHandsService")
public class ShakeHandsServiceImpl implements FenleiService {
    private static Logger logger = LoggerFactory.getLogger(ShakeHandsServiceImpl.class);

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

            InputStream in = request.getInputStream();

            byte[] shakeHandBytes = IoUtil.getByteArray(in);

            byte[] resultBytes = generateResponseForDevice(shakeHandBytes);

            bos.write(resultBytes);
            bos.flush();
            bos.close();


        } catch (Exception e) {
            logger.warn("shake hands exception: [{}]", e);
        }
    }


    @Override
    public StreamingResponseBody getResponse(byte[] bytes) {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(outputStream);

                    byte[] resultBytes = generateResponseForDevice(bytes);

                    bos.write(resultBytes);
                    bos.flush();

                } catch (Exception e){
                    logger.info("getResponse exception: {}", e);
                }
            }
        };
    }

    @Override
    public byte[] getResponseBytes(byte[] bytes) {
        return generateResponseForDevice(bytes);
    }

    private byte[] generateResponseForDevice(byte[] bytes){
        byte[] back = new byte[0];
        try {
            byte[] shakeHandBytes = bytes;

            logger.info("shakeHandBytes: {}", shakeHandBytes);

            int[] shakeHandInts = ConverterUtil.toIntArray(shakeHandBytes);
            logger.info("ShakeHandInts: {}", shakeHandInts);

            // CRC检查
            logger.info("CheckCRC: {}", CrcUtil.validateCRC(shakeHandInts));
            logger.info("BuildCRC: {}", CrcUtil.buildCRC(shakeHandInts));

            logger.info("ShakeHandInts => ShakeHandBytes: {}", ConverterUtil.toByteArray(shakeHandInts));

            Decoder decoder = CodecManager.getDecoder(Protocol.SHAKE_HAND);
            if (decoder.decode(shakeHandInts)) {
                ShakeHandRequestData result = (ShakeHandRequestData) decoder.getResult();

                logger.info("ShakeHand decoder result: {}", result);

                // send to kafka

                // 发送到业务端，等待业务数据返回
                String bizResult = HttpBizClient.getShakeHands(result);
                logger.info("Return from biz domain: {}", bizResult);

                // JSON to OBJ
                Map<String, Object> bizObjData = JsonUtil.json2map(bizResult);
                logger.info("BizObjData = {}", bizObjData);

                // 设备应答
                Encoder encoder = CodecManager.getEncoder(Protocol.SHAKE_HAND);

                // 测试数据
//                encoder.setBizData(MockDevRespDataBuiler.buildShakeHandsData());
                encoder.setBizData(bizObjData);

                if (encoder.encode()) {
                    back = ConverterUtil.toByteArray(encoder.getResult());
                }
            }
        }catch(Exception e){
            logger.info("generateResponseForDevice exception: {}", e);
        }

        return back;
    }
}
