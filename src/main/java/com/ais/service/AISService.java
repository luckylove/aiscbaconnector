package com.ais.service;

import com.ais.config.ServiceConfig;
import com.ais.config.model.Env;
import com.ais.config.model.Environments;
import com.ais.service.model.DBObject;
import com.ais.service.model.config.*;
import com.ais.util.AISUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    public static DBObject<CBA_DNIS> GetDNIS(String _sessionId, String dnis) {
        DBObject<CBA_DNIS> prepaiddbObject =  configService.GetDNIS(_sessionId, dnis);
        return prepaiddbObject;
    }

    public static DBObject<CBA_HOLIDAY> GetHoliday(String _sessionId, String holidayId) {
        DBObject<CBA_HOLIDAY> prepaiddbObject = configService.GetHoliday(_sessionId, holidayId);
        return prepaiddbObject;
    }

    public static DBObject<CBA_INBOUND_CONF> GetInboundConf(String _sessionId, String langId, String networkType, String subnetType,
                                                            String mobileSegment, String arpu, String xferVdn) {
        DBObject<CBA_INBOUND_CONF> prepaiddbObject = configService.GetInboundConf(_sessionId, langId, networkType, subnetType, mobileSegment, arpu, xferVdn);
        return prepaiddbObject;
    }

    public static DBObject<CBA_INBOUND_CONF> GetInboundConfByInboundVDN(String _sessionId, String inboundVdn) {
        DBObject<CBA_INBOUND_CONF> prepaiddbObject = configService.GetInboundConfByInboundVDN(_sessionId, inboundVdn);
        return prepaiddbObject;
    }

    public static DBObject<CBA_INBOUND_CONF> GetInboundConfByServiceID(String _sessionId, String serviceId) {
        DBObject<CBA_INBOUND_CONF> prepaiddbObject = new DBObject<CBA_INBOUND_CONF>();
        return prepaiddbObject;
    }


    public static DBObject<CBA_SERVICE> GetService(String _sessionId, String serviceId) {
        DBObject<CBA_SERVICE> prepaiddbObject = new DBObject<CBA_SERVICE>();
        return prepaiddbObject;
    }

    public static DBObject<CBA_MENU_SET> GetMenuSet(String _sessionId, String menuSetId) {
        DBObject<CBA_MENU_SET> prepaiddbObject = new DBObject<CBA_MENU_SET>();
        return prepaiddbObject;
    }

    public static DBObject<CBA_MENU_DIGIT> GetMenuDigits(String _sessionId, String menuSetId) {
        DBObject<CBA_MENU_DIGIT> prepaiddbObject = new DBObject<CBA_MENU_DIGIT>();
        return prepaiddbObject;
    }

    public static DBObject<CBA_PRIORITY> GetPriority(String _sessionId, Long idx) {
        DBObject<CBA_PRIORITY> prepaiddbObject = new DBObject<CBA_PRIORITY>();
        return prepaiddbObject;
    }


    public static DBObject AddCallbackRequest(String _sessionId, CBA_REQUEST params) {
        DBObject prepaiddbObject = new DBObject();
        return prepaiddbObject;
    }

    public static DBObject UpdateCallbackRequest(String _sessionId, CBA_REQUEST params) {
        DBObject prepaiddbObject = new DBObject();
        return prepaiddbObject;
    }

    public static DBObject<List<CBA_REQUEST>> GetCallbackRequest(String _sessionId, Integer size) {
        DBObject prepaiddbObject = new DBObject();
        prepaiddbObject.setResult(new ArrayList<CBA_REQUEST>());
        return prepaiddbObject;
    }

    public static DBObject<Boolean> CheckDuplicateCallbackRequest(String _sessionId, String mobileNumber, String serviceId, Integer interval) {
        DBObject<Boolean> prepaiddbObject = new DBObject<Boolean>();
        prepaiddbObject.setResult(true);
        return prepaiddbObject;
    }

    public static DBObject<Boolean> CheckMaxConcurrentCallbackRequest(String _sessionId, String serviceId, Integer maxConcurrentCall) {
        DBObject prepaiddbObject = new DBObject();
        prepaiddbObject.setResult(true);
        return prepaiddbObject;
    }

    public static DBObject AddAct(String _sessionId, CBA_ACT params) {
        DBObject prepaiddbObject = new DBObject();
        return prepaiddbObject;
    }

    public static DBObject AddActDetail(String _sessionId, CBA_ACT_DETAIL params) {
        DBObject prepaiddbObject = new DBObject();
        return prepaiddbObject;
    }


}
