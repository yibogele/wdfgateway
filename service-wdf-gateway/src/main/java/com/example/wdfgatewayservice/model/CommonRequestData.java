package com.example.wdfgatewayservice.model;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 16:03 2018/8/13
 * Modified By:
 */
public class CommonRequestData {
    int qsm_low;
    int qsm_high;

//    int pack_length_low;
//    int pack_length_high;
    int pack_length;

//    int crc;

    int crc_low;
    int crc_high;

    public int getCrc_low() {
        return crc_low;
    }

    public void setCrc_low(int crc_low) {
        this.crc_low = crc_low;
    }

    public int getCrc_high() {
        return crc_high;
    }

    public void setCrc_high(int crc_high) {
        this.crc_high = crc_high;
    }

    public int getQsm_low() {
        return qsm_low;
    }

    public void setQsm_low(int qsm_low) {
        this.qsm_low = qsm_low;
    }

    public int getQsm_high() {
        return qsm_high;
    }

    public void setQsm_high(int qsm_high) {
        this.qsm_high = qsm_high;
    }

    public int getPack_length() {
        return pack_length;
    }

    public void setPack_length(int pack_length) {
        this.pack_length = pack_length;
    }

//    public int getCrc() {
//        return crc;
//    }
//
//    public void setCrc(int crc) {
//        this.crc = crc;
//    }

    @Override
    public String toString() {
        return "CommonRequestData{" +
                "qsm_low=" + qsm_low +
                ", qsm_high=" + qsm_high +
                ", pack_length=" + pack_length +
                 '}';
    }
}
