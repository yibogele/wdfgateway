package com.example.wdfgatewayservice.config;

import com.example.wdfgatewayservice.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 10:36 2018/8/16
 * Modified By:
 */
public final class ServerConfig {
    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

    final Properties props = new Properties();

    public String getShakeHandsURL() {
        return props.getProperty(Constants.Config.BIZ_SHAKEHANDS_URL);
    }

    public String getDumpRecordURL(){
        return props.getProperty(Constants.Config.BIZ_DUMPRECORD_URL);
    }

    public String getCardNumURL(){
        return props.getProperty(Constants.Config.BIZ_CARDNUM_URL);
    }

    // multithread singleto
    private static class SingletonHolder{
        static final ServerConfig instance = new ServerConfig();
    }

    public static ServerConfig getInstance(){
        return SingletonHolder.instance;
    }

    //
    private ServerConfig(){
        final String configPath = System.getProperty("app.path", null);
        final File fileConfig = new File(configPath, "config/my.conf");

        logger.info("config.path [{}]", configPath);
        logger.info("config file path [{}]", fileConfig.getAbsolutePath());
        if (!fileConfig.exists()) {
            logger.error("can not found config file.. exiting...");
            System.exit(0);
        }

        InputStream input = null;
        try {
            input = new FileInputStream(fileConfig);
            props.load(input);
        }
        catch (IOException e){
            logger.error("read config file error.");
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getKakfaBrokers(){
        return props.getProperty(Constants.Config.KAFKA_BROKERS);
    }
}
