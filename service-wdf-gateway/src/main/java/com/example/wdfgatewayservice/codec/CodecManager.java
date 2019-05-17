package com.example.wdfgatewayservice.codec;

import com.example.wdfgatewayservice.codec.de.CardNumDecoder;
import com.example.wdfgatewayservice.codec.de.Decoder;
import com.example.wdfgatewayservice.codec.de.DumpRecordDecoder;
import com.example.wdfgatewayservice.codec.de.ShakeHandDecoder;
import com.example.wdfgatewayservice.codec.en.CardNumEncoder;
import com.example.wdfgatewayservice.codec.en.DumpRecordEncoder;
import com.example.wdfgatewayservice.codec.en.Encoder;
import com.example.wdfgatewayservice.codec.en.ShakeHandEncoder;
import com.example.wdfgatewayservice.constant.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:22 2018/8/13
 * Modified By:
 */
public class CodecManager {
    private static Logger logger = LoggerFactory.getLogger(CodecManager.class);

    private CodecManager(){}

    public static final Decoder getDecoder(Protocol protocol){
        switch (protocol){
            case SHAKE_HAND:
                return new ShakeHandDecoder();
            case VALIDATE_CARDNUM:
                return new CardNumDecoder();
            case DUMP_RECORD:
                return new DumpRecordDecoder();
        }
        return null;
    }

    public final static Encoder getEncoder(Protocol protocol){
        switch (protocol){
            case SHAKE_HAND:
                return new ShakeHandEncoder();
            case VALIDATE_CARDNUM:
                return new CardNumEncoder();
            case DUMP_RECORD:
                return new DumpRecordEncoder();
        }

        return null;
    }
}
