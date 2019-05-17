package com.example.wdfgatewayservice.codec.de;

import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.constant.ErrorCode;
import com.example.wdfgatewayservice.model.CommonRequestData;
import com.example.wdfgatewayservice.util.CommonUtil;
import com.example.wdfgatewayservice.util.CrcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 13:50 2018/8/13
 * Modified By:
 */
public abstract class Decoder<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 解析状态
    protected ErrorCode errorCode;

    // decode的结果对象
    protected T result;

    // 通用的请求数据
    protected CommonRequestData commonRequestData = new CommonRequestData();

    /**
     *
     * @return
     */
    public ErrorCode getErrorCode(){
        return errorCode;
    }

    /**
     *
     * @return
     */
    public T getResult(){
        return result;
    }

    /**
     * decode请求数据包
     * @param data
     * @return
     */
    public final boolean decode(int[] data)
    {
        if (data == null || data.length == 0)
        {
            errorCode = ErrorCode.MB_PARSE_STATUS_NULL;

            return false;
        }else
        {
            return decodeCommon(data) && decodeBody(data);
        }

    }

    /**
     * 解析通用请求数据，并将结果保存在commonRequestData
     * @param data
     * @return
     */
    protected final boolean decodeCommon(int[] data){

        int concatLength = CommonUtil.concatLowHigh(data[Constants.MB.LENGTH_LOW],
                data[Constants.MB.LENGTH_HIGH]);

        int byteLength = data.length;

        if (concatLength == byteLength){
            commonRequestData.setPack_length(concatLength);
            // 验证起始码
            int qsm_low = data[Constants.MB.QSM_LOW];
            int qsm_high = data[Constants.MB.QSM_HIGH];

            // test
//            {
//                int concatInt = CommonUtil.concatLowHigh(qsm_low, qsm_high);
//                logger.info("concatLowHigh of {},{}={}", qsm_low, qsm_high, concatInt);
//                logger.info("Low and High of {} is {},{}", concatInt, CommonUtil.getLow8Bit((short) concatInt),
//                        CommonUtil.getHigh8Bit((short) concatInt));
//            }


            if (qsm_low == Constants.MB.MB_QSM_LOW &&
                    qsm_high == Constants.MB.MB_QSM_HIGH){
                //
                commonRequestData.setQsm_low(qsm_low);
                commonRequestData.setQsm_high(qsm_high);


                // 验证CRC
                if (CrcUtil.validateCRC(data)){
                    return true;

                }else{
                    errorCode = ErrorCode.MB_PARSE_STATUS_CRC;
                }
            }else{
                errorCode = ErrorCode.MB_PARSE_STATUS_QSM;
            }
        }else if (byteLength == 57){
            errorCode = ErrorCode.MB_PARSE_STATUS_CG;
        } else {
            errorCode = ErrorCode.MB_PARSE_STATUS_CD;
        }


        return false;
    }

    /**
     * 解析业务数据，将结果保存在result
     * @param data
     * @return
     */
    protected abstract boolean decodeBody(int[] data);
}
