package com.example.wdfgatewayservice.service.impl;

import com.example.wdfgatewayservice.codec.CodecManager;
import com.example.wdfgatewayservice.codec.de.Decoder;
import com.example.wdfgatewayservice.codec.en.Encoder;
import com.example.wdfgatewayservice.constant.Protocol;
import com.example.wdfgatewayservice.httpclient.HttpBizClient;
import com.example.wdfgatewayservice.model.CardNumRequestData;
import com.example.wdfgatewayservice.service.FenleiService;
import com.example.wdfgatewayservice.util.ConverterUtil;
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
@Service(value = "CardNumService")
public class CardNumServiceImpl implements FenleiService {
    private static Logger logger = LoggerFactory.getLogger(CardNumServiceImpl.class);

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try{
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

            InputStream in = request.getInputStream();

            //
            byte[] cardNumBytes = IoUtil.getByteArray(in);

            byte[] resultBytes = generateResponseForDevice(cardNumBytes);

            bos.write(resultBytes);
            bos.flush();

            bos.close();
        }catch (Exception e){
            e.printStackTrace();
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

        try{
            byte[] cardNumBytes = bytes;
            logger.info("Validate cardNumBytes: {}", cardNumBytes);

            int[] cardNumInts = ConverterUtil.toIntArray(cardNumBytes);
            logger.info("Validate cardNumInts: {}", cardNumInts);

            Decoder decoder = CodecManager.getDecoder(Protocol.VALIDATE_CARDNUM);
            if (decoder.decode(cardNumInts)){

                CardNumRequestData decodeResult = (CardNumRequestData) decoder.getResult();
                logger.info("Validate CardNum decoder result: {}", decodeResult);

                // send to kafka

                // 发送到业务端，等待业务数据返回
                String bizResult = HttpBizClient.getCardNum(decodeResult);
                logger.info("Return from biz domain: {}", bizResult);

                // JSON to OBJ
                Map<String, Object> bizObjData = JsonUtil.json2map(bizResult);
                logger.info("BizObjData = {}", bizObjData);

                // 设备应答
                Encoder encoder = CodecManager.getEncoder(Protocol.VALIDATE_CARDNUM);

                // 测试数据
//                encoder.setBizData(MockDevRespDataBuiler.buildShakeHandsData());
                encoder.setBizData(bizObjData);

                if (encoder.encode()) {
                    back = ConverterUtil.toByteArray(encoder.getResult());

                }
            }
        }catch (Exception e){
            logger.info("generateResponseForDevice exception: {}", e);
        }

        return back;
    }
}
