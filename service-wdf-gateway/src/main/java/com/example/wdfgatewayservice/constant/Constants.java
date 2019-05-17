package com.example.wdfgatewayservice.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:57 2018/8/13
 * Modified By:
 */
public class Constants {

    private Constants(){}
    public interface Test{
        static boolean isTest = false;
        static final String TEST_DEV_ID = "WDF347366093";
        static final String TEST_CARD_NUM = "z1535005912287908";
    }

    public interface Config {
        static final String KAFKA_BROKERS = "kafka.brokers";
        static final String BIZ_SHAKEHANDS_URL = "biz.shakehands.url";
        static final String BIZ_DUMPRECORD_URL = "biz.dumprecord.url";
        static final String BIZ_CARDNUM_URL = "biz.cardnum.url";
    }

    public interface ParseResult{

    }

    public interface GarbageTypeStr {
        // 厨余
        static final String GBT_KITCHEN = "kitchen";

        // 纸张
        static final String GBT_PAPER = "paper";

        // 金属
        static final String GBT_METAL = "metal";

        // 塑料
        static final String GBT_PLASTIC = "plastic";

        // 玻璃
        static final String GBT_GLASS = "glass";

        //  其他
        static final String GBT_OTHER = "other";

        //  回收
        static final String GBT_RECYCLE = "recycle";

        //  有害
        static final String GBT_HARMFUL = "harmful";

        //  织物
        static final String GBT_CLOTH = "cloth";
    }


    public interface MB {
        // 长度
        static final int LENGTH_LOW = 2;
        static final int LENGTH_HIGH = 3;

        // 起始码
        static final int QSM_LOW = 0;
        static final int QSM_HIGH = 1;

        // 设备ID
        static final int FENLEI_DEVID_START = 4;
        static final int FENLEI_DEVID_END = 15; //

        // ICC ID
        static final int FENLEI_ICCID_START = 16;
        static final int FENLEI_ICCID_END = 25;

        // 经度
        static final int FENLEI_LONGITUDE_START = 26;

        // 纬度
        static final int FENLEI_LATITUDE_START = 30;

        // 信号强度
        static final int FENLEI_SIG_STRENGTH = 34;


        // 固件版本
        static final int FENLEI_FIRMWARE_START = 35;

        // 充电电压
        static final int FENLEI_CHARGE_VOLT_START = 36;

        // 充电电流
        static final int FENLEI_CHARGE_DIANLIU_START = 38;

        // 日充电量
        static final int FENLEI_DAY_CHARGE_START = 40;

        // 日用电量
        static final int FENLEI_DAY_USAGE_START = 42;

        // 电池电压
        static final int FENLEI_BATT_VOLT_START = 44;

        // 主板温度
        static final int FENLEI_MB_TEMP_START = 46;

        // 电池温度
        static final int FENLEI_BATT_TEMP_START = 47;

        // URL
        static final int FENLEI_URL_START = 48;

        // 剩余纸张
        static final int FENLEI_LEFT_PAPER_START = 49;

        // 分通总数
        static final int FENLEI_BIN_COUNT_START = 50;

        // 分通状态起始
        static final int FENLEI_BIN_STATUS_START = 51;


        static final int MB_QSM_LOW = 65;
        static final int MB_QSM_HIGH = 66;
    }
}
