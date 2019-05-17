package com.example.wdfgatewayservice.codec.de;

import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.constant.ErrorCode;
import com.example.wdfgatewayservice.constant.TrashCanFullType;
import com.example.wdfgatewayservice.model.TrashCanFault;
import com.example.wdfgatewayservice.model.NeedCleanData;
import com.example.wdfgatewayservice.model.ShakeHandRequestData;
import com.example.wdfgatewayservice.model.TrashCanData;
import com.example.wdfgatewayservice.util.CommonUtil;
import com.example.wdfgatewayservice.util.ConverterUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 15:31 2018/8/13
 * Modified By:
 */
public class ShakeHandDecoder extends Decoder<ShakeHandRequestData> {
    @Override
    public boolean decodeBody(int[] shakeHandsInts) {
        //
        result = new ShakeHandRequestData();
//        ShakeHandRequestData shakeHandRequestData = (ShakeHandRequestData)result;

        // set common data
        result.setCommonRequestData(commonRequestData);


        // device id
        byte[] binIdBytes = ConverterUtil.substringBytes(shakeHandsInts,
                Constants.MB.FENLEI_DEVID_START,
                Constants.MB.FENLEI_DEVID_END+1);
        String devId = ConverterUtil.bytesToHexString(binIdBytes).toUpperCase();
        result.setDeviceId(devId);

        //ICCID
        byte[] iccIdBytes = ConverterUtil.substringBytes(shakeHandsInts,
                Constants.MB.FENLEI_ICCID_START,
                Constants.MB.FENLEI_ICCID_END+1);
        String iccId = ConverterUtil.bytesToHexString(iccIdBytes);
        result.setIccId(iccId);

        // 经度
        double jingdu = ((double) (shakeHandsInts[Constants.MB.FENLEI_LONGITUDE_START + 3] << 24
                | shakeHandsInts[Constants.MB.FENLEI_LONGITUDE_START + 2] << 16
                | shakeHandsInts[Constants.MB.FENLEI_LONGITUDE_START + 1] << 8
                | shakeHandsInts[Constants.MB.FENLEI_LONGITUDE_START])) /1000000;
        result.setLongitude(jingdu);

        // 纬度
        double weidu = ((double) (shakeHandsInts[Constants.MB.FENLEI_LATITUDE_START + 3] << 24
                | shakeHandsInts[Constants.MB.FENLEI_LATITUDE_START + 2] << 16
                | shakeHandsInts[Constants.MB.FENLEI_LATITUDE_START + 1] << 8
                | shakeHandsInts[Constants.MB.FENLEI_LATITUDE_START])) /1000000;
        result.setLatitude(weidu);

        // 信号强度
        int signal_strength = shakeHandsInts[Constants.MB.FENLEI_SIG_STRENGTH];
        result.setSignal(signal_strength);

        // 固件版本
        int firmware_version = shakeHandsInts[Constants.MB.FENLEI_FIRMWARE_START];
        result.setFirmwareVersion(firmware_version);

        // 充电电压
        int charge_volt_low = shakeHandsInts[Constants.MB.FENLEI_CHARGE_VOLT_START];
        int charge_volt_high = shakeHandsInts[Constants.MB.FENLEI_CHARGE_VOLT_START+1];
        double charge_volt = CommonUtil.concatLowHigh(charge_volt_low, charge_volt_high)/10;
        result.setChargeVolt(charge_volt);

        // 充电电流
        int charge_current_low = shakeHandsInts[Constants.MB.FENLEI_CHARGE_DIANLIU_START];
        int charge_current_high = shakeHandsInts[Constants.MB.FENLEI_CHARGE_DIANLIU_START+1];
        int charge_current = CommonUtil.concatLowHigh(charge_current_low, charge_current_high);
        result.setChargeCurrent(charge_current);

        // 日充电量
        int day_charge_low = shakeHandsInts[Constants.MB.FENLEI_DAY_CHARGE_START];
        int day_charge_high = shakeHandsInts[Constants.MB.FENLEI_DAY_CHARGE_START+1];
        int day_charge = CommonUtil.concatLowHigh(day_charge_low, day_charge_high);
        result.setDayCharge(day_charge);

        // 日用电量
        int day_use_low = shakeHandsInts[Constants.MB.FENLEI_DAY_USAGE_START];
        int day_use_high = shakeHandsInts[Constants.MB.FENLEI_DAY_USAGE_START+1];
        int day_use = CommonUtil.concatLowHigh(day_use_low , day_use_high);
        result.setDayUse(day_use);


        // 电池电压
        int battery_volt_low = shakeHandsInts[Constants.MB.FENLEI_BATT_VOLT_START];
        int battery_volt_high = shakeHandsInts[Constants.MB.FENLEI_BATT_VOLT_START+1];
        double battery_volt = CommonUtil.concatLowHigh(battery_volt_low, battery_volt_high)/10;
        result.setBatteryVolt(battery_volt);

        // 主板温度
        int mb_temperature = shakeHandsInts[Constants.MB.FENLEI_MB_TEMP_START];
        result.setMainboardTemp(mb_temperature);

        // 电池温度
        int batt_temp = shakeHandsInts[Constants.MB.FENLEI_BATT_TEMP_START];
        result.setBatteryTemp(batt_temp);

        // URL
        // 剩余纸张

        // 分桶总数
        int bin_count = shakeHandsInts[Constants.MB.FENLEI_BIN_COUNT_START];
        result.setBinCount(bin_count);

        //
        return decodeBinDevices(shakeHandsInts);
    }

    private boolean decodeBinDevices(int[] shakeHandsInts){
        //
//        ShakeHandRequestData shakeHandRequestData = (ShakeHandRequestData)result;

        // 数据时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        result.setCreateTime(createTime);

        // 分桶
        int bin_count = shakeHandsInts[Constants.MB.FENLEI_BIN_COUNT_START];

        if (bin_count > 0){
            for (int i = 0; i < bin_count; i++) {

                TrashCanData trashCanData = new TrashCanData();
//                trashCanData.setDevId(result.getDeviceId());

                String sortNum = String.valueOf(i);
                trashCanData.setBinNum(sortNum);

//                trashCanData.setId(CommonUtil.getUUID());

//                trashCanData.setConnectTime(createTime);

                // 故障状态
                int faultStatus = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 6*i];
                trashCanData.setFaultStatus(faultStatus);

                // 工作状态
                int workStatus = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 1 + 6*i];
                trashCanData.setWorkStatus(workStatus);

                // 温度
                int temp;
                if ((workStatus & 0x01) == 0){
                    temp = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 2 + 6*i];

                    if (temp > 127){
                        temp = temp - 256;
                    }
                }else{
                    temp = 100;
                }
                trashCanData.setTemperature(temp);

                // 垃圾量
                int volume = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 3 + 6*i];
                volume = volume > 100 ? 100 : volume;
                trashCanData.setVolume(volume);

                // 实时重量
                int weight_low = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 4 + 6*i];
                int weight_high = shakeHandsInts[Constants.MB.FENLEI_BIN_STATUS_START + 5 + 6*i];

                short realWeight = (short)(weight_high<<8 | weight_low);
                if (realWeight < 0){
                    realWeight = 0;
                }
                double realWeightD = realWeight*10;
                trashCanData.setRealWeight(realWeightD);


                // 处理故障数据
                {
                    /**
                     * 异常构建工具
                     */
                    class FaultBuilder {
//                        private TrashCanData trashCanData;
                        private ErrorCode errorCode;

                        public FaultBuilder(ErrorCode errorCode) {
//                            this.trashCanData = trashCanData;
                            this.errorCode = errorCode;
                        }

                        public TrashCanFault buildException(){
                            TrashCanFault trashCanFault = new TrashCanFault();

                            trashCanFault.setErrorCode(errorCode);
//                            trashCanFault.setId(CommonUtil.getUUID());
//                            trashCanFault.setErrorTime(trashCanData.getConnectTime());
                            trashCanFault.setCreateUser("system");
//                            trashCanFault.setDevId(trashCanData.getDevId());

                            return trashCanFault;
                        }
                    }
                    // 温度传感器
                    if ((faultStatus&0x01) != 0){

                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_TEMP_SENSOR).buildException());
                    }

                    // 上限位故障
                    if((faultStatus & 0x40) != 0){
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_TOP_LIMIT).buildException());
                    }

                    // 下线位故障
                    if((faultStatus & 0x80) != 0){
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_BOTTOM_LITMIT).buildException());
                    }

                    // 容量传感器断开
                    if((faultStatus & 0x02) != 0){
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_VOLUME_SENSOR).buildException());
                    }

                    // 称重系统故障
                    if((faultStatus & 0x04) != 0){
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_WEIGHT_SENSOR).buildException());
                    }

                    // 电机断开
                    if((faultStatus & 0x10) != 0) {
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_MOTOR_DISCONNECT).buildException());
                    }

                    // 电机短路
                    if((faultStatus & 0x20) != 0) {
                        trashCanData.addTrashCanFault(new FaultBuilder(
                                ErrorCode.TRASHCAN_FAULT_MOTOR_SHORTCUT).buildException());
                    }

                }
                //
                result.addTrashCanData(trashCanData);

                // 处理工作状态
                {
                    /**
                     * 清理 构建工具
                     */
                    class CleanDataBuilder{
                        private TrashCanData trashCanData;
                        private TrashCanFullType trashCanFullType;

                        public CleanDataBuilder(TrashCanData trashCanData,
                                                TrashCanFullType trashCanFullType) {
                            this.trashCanData = trashCanData;
                            this.trashCanFullType = trashCanFullType;
                        }


                        public NeedCleanData buildCleanData(){
                            NeedCleanData needCleanData = new NeedCleanData();

//                            needCleanData.setId(CommonUtil.getUUID());
//                            needCleanData.setDevId(this.trashCanData.getDevId());
                            needCleanData.setWeight(trashCanData.getRealWeight());
//                            needCleanData.setFullTime(trashCanData.getConnectTime());
                            needCleanData.setCleanStatus("N");
                            needCleanData.setTrashCanFullType(trashCanFullType);
//                            needCleanData.setGarbageType();

                            return needCleanData;
                        }
                    }
                    // 容量已满
                    if ((workStatus&0x02) != 0 && (workStatus&0x04)==0){

                        // 容量传感器无故障
                        if ((faultStatus&0x02) == 0){
                            logger.info("full volume, no error");
                            trashCanData.addNeedCleanData(
                                    new CleanDataBuilder(trashCanData,
                                            TrashCanFullType.TRASHCAN_FULL_VOLUME).buildCleanData());
                        }
                    }
                    // 称重超限
                    if ((workStatus & 0x04) != 0 && (workStatus & 0x02)==0){
                        // 称重系统无故障
                        if ((faultStatus&0x04)==0){
                            logger.info("over weight, no error");
                            trashCanData.addNeedCleanData(
                                    new CleanDataBuilder(trashCanData,
                                            TrashCanFullType.TRASHCAN_FULL_WEIGHT).buildCleanData());
                        }
                    }

                    // 容量已满, 称重超限
                    if ((workStatus&0x02) != 0 && (workStatus&0x04)!=0){
                        // // 容量传感器无故障,称重系统无故障
                        if ((faultStatus&0x02) == 0 && (faultStatus&0x04)==0){
                            logger.info("full volume, over weight, no error");
                            trashCanData.addNeedCleanData(
                                    new CleanDataBuilder(trashCanData,
                                            TrashCanFullType.TRASHCAN_FULL_VOLUME_WEIGHT).buildCleanData());
                        }
                    }
                }
            }

            return true;
        }
        return false;
    }
}
