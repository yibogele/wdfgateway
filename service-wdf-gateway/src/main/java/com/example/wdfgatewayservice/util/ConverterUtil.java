package com.example.wdfgatewayservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:33 2018/8/13
 * Modified By:
 */
public class ConverterUtil {
    private static Logger logger = LoggerFactory.getLogger(ConverterUtil.class);

    private ConverterUtil(){}

    /**
     * byte to int
     * @param b
     * @return
     */
    public static int byte2int(byte b){
        return b&0xff;
    }

    /**
     *
     * @param i
     * @return
     */
    public static byte int2byte(int i){
        return (byte)(i&0xff);
    }

    /**
     *
     * @param barr
     * @return
     */
    public static int[] toIntArray(byte[] barr){
        int[] ret = new int[barr.length];

        for(int i = 0; i < barr.length; i++){
            ret[i] = barr[i] & 0xff;
        }

        return ret;
    }

    /**
     *
     * @param iarr
     * @return
     */
    public static byte[] toByteArray(int[] iarr){
        byte[] ret = new byte[iarr.length];

        for (int i = 0; i < iarr.length; i++) {
            ret[i] = (byte)(iarr[i]&0xff);
        }

        return ret;
    }


    /**
     *
     * @param iarr
     * @param from
     * @param to
     * @return
     */
    public static byte[] substringBytes(int[] iarr, int from, int to){
        int[] sub = Arrays.copyOfRange(iarr, from, to);

        return  toByteArray(sub);
    }

    /**
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
