package com.ais.service;

import com.ais.config.ServiceConfig;
import com.ais.config.model.Env;
import com.ais.config.model.Environments;
import com.ais.service.model.DBObject;
import com.ais.service.model.config.CBA_BLACKLIST;
import com.ais.service.model.config.CBA_DNC;
import com.ais.service.model.config.CBA_PARAM;
import com.ais.service.model.config.CBA_TIMEZONE;
import com.ais.util.AISUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * User: son.nguyen
 * Date: 10/24/13
 * Time: 9:36 PM
 * This is  main class for init the resource (dbconfig.xml) from local file and init database connection pool...
 * This class is also entry point for calling the services , it provide some static method for you can call everywhere.
 * AISService.GetPackagePrepaid("aa",123);
 */
public class AISService {
    private static Logger logger = LoggerFactory.getLogger(AISService.class);
    private static String DEFAULT_CONFIG_PATH = "dbconfig.xml";
    private static LogService logService;
    private static ConfigService configService;

    private static boolean serviceStart = false;

    static {
        try {
            initService();
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException("Can not start db service");
        }
    }

    /**
     * for init the service at beginning.
     * @throws Exception
     */
    public static void initService() throws Exception {
        if (serviceStart == false) {
            String path = AISUtils.getConfigFile(null, DEFAULT_CONFIG_PATH, 0);
            InputStream inputStream = null;
            if (path != null) {
                logger.info("===== Init service ===== " + path);
                inputStream = new FileInputStream(new File(path));
            } else {
                //load config from classpath
                logger.info("===== Init service from Class path =====");
                ClassPathResource cpr = new ClassPathResource(DEFAULT_CONFIG_PATH);
                inputStream = cpr.getInputStream();
            }
            //map xml config to object
            Environments environments = ServiceConfig.parse(inputStream);
            //get active environment
            Env activeEnv = environments.getActiveEnv();
            logger.info("===== Using profile: " + activeEnv.getName());
            //init database connection and convert all config list to map
            activeEnv.getGetConfig().initConfigMap();
            activeEnv.getLogConfig().initConfigMap();

            //create service instance
            configService = new ConfigService(activeEnv.getGetConfig());
            logService = new LogService(activeEnv.getLogConfig());
            serviceStart = true;
        }
    }


    /**
     * collection of static class is called by flow for get config
     */


    public static DBObject<CBA_PARAM> GetParam(String _sessionId, String paramName) {
        DBObject<CBA_PARAM> prepaiddbObject = configService.GetParam(_sessionId, paramName);
        return prepaiddbObject;
    }


    public static DBObject<CBA_BLACKLIST> GetBlackList(String _sessionId, String phone) {
        DBObject<CBA_BLACKLIST> prepaiddbObject = configService.GetBlackList(_sessionId, phone);
        return prepaiddbObject;
    }


    public static DBObject<CBA_DNC> GetDNC(String _sessionId, String phone) {
        DBObject<CBA_DNC> prepaiddbObject = configService.GetDNC(_sessionId, phone);
        return prepaiddbObject;
    }

    public static DBObject<CBA_TIMEZONE> GetTimeZone(String _sessionId, String timezoneId) {
        DBObject<CBA_TIMEZONE> prepaiddbObject = configService.GetTimeZone(_sessionId, timezoneId);
        return prepaiddbObject;
    }



}
