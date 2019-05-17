package com.example.wdfgatewayservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:16 2018/8/13
 * Modified By:
 */
public class IoUtil {
    private static Logger logger = LoggerFactory.getLogger(IoUtil.class);

    private IoUtil(){}

    private static final int TOTAL_LENGTH = 64 * 1024; // 最大长度64K
    private static final int BUF_LENGTH = 1024;

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] getByteArray(InputStream in) throws IOException {
        byte[] totalBuf = new byte[TOTAL_LENGTH];
        byte[] buf = new byte[BUF_LENGTH];
        byte[] result;

        int totalCount = 0;
        int count = 0;

        while ( (count  = in.read(buf)) > 0)
        {
            System.arraycopy(buf, 0, totalBuf, totalCount, count);

            totalCount += count;
        }

        if(totalCount != 0){
            result = Arrays.copyOf(totalBuf, totalCount);
        }else{
            result = new byte[0];
        }
        return result;
    }
}
