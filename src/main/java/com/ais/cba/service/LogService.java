package com.ais.cba.service;

import com.ais.cba.config.model.Config;
import com.ais.cba.config.model.LogConfig;
import com.ais.cba.service.model.DBObject;
import com.ais.cba.service.model.config.CBA_REQUEST;
import com.ais.cba.util.AISLogUtil;
import com.ais.cba.util.AISUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;
import java.util.Map;

/**
 * User: son.nguyen
 * Date: 10/24/13
 * Time: 9:39 PM
 * <p/>
 * This is main class for write log to db or file, get config from db or file
 */
public class LogService {

    private Logger logger = LoggerFactory.getLogger(LogService.class);
    private LogConfig config;

    public LogService(LogConfig config) {
        this.config = config;
    }

    public DBObject AddCallbackRequest(final String _sessionId, final CBA_REQUEST params) {
        String configName = "AddCallbackRequest";
        final Config cf = config.lookup(configName);
        DBObject rs = new DBObject();
        if (cf != null) {
            try {
                AISLogUtil.printLine(logger, _sessionId, "Insert to db:" + cf.getProcerdure());
                SimpleJdbcCall simpleJdbcCall1 = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_IDX",
                                "IN_REQ_DATETIME",
                                "IN_LANG_ID",
                                "IN_TIMEZONE",
                                "IN_IVR_SESSIONID",
                                "IN_VDUID",
                                "IN_ANI",
                                "IN_MOBILE_NUMBER",
                                "IN_DNIS",
                                "IN_NETWORK_TYPE",
                                "IN_SUBNET_TYPE",
                                "IN_MOB_SEGMENT",
                                "IN_MOBILE_STATUS",
                                "IN_ARPU",
                                "IN_CALLBACK_PHONE",
                                "IN_SERVICE_ID",
                                "IN_CB_TYPE",
                                "IN_CB_REQUEST_TIME",
                                "IN_CB_STARTTIME",
                                "IN_CB_DELAY",
                                "IN_CB_START_BUFFER",
                                "IN_NEXT_CALLTIME",
                                "IN_MAX_TRY",
                                "IN_TRY_INTERVAL",
                                "IN_TRYS",
                                "IN_PRIORITY",
                                "IN_STATUS",
                                "IN_WORKER_ID",
                                "IN_LAST_UPDATE",
                                "IN_RESULT",
                                "IN_AGENT_ID",
                                "IN_AGENT_SPLIT",
                                "IN_AGENT_VDN"
                        ).declareParameters(
                                new SqlParameter("IN_IDX", Types.VARCHAR),
                                new SqlParameter("IN_REQ_DATETIME", Types.VARCHAR),
                                new SqlParameter("IN_LANG_ID", Types.VARCHAR),
                                new SqlParameter("IN_TIMEZONE", Types.VARCHAR),
                                new SqlParameter("IN_IVR_SESSIONID", Types.VARCHAR),
                                new SqlParameter("IN_VDUID", Types.VARCHAR),
                                new SqlParameter("IN_ANI", Types.VARCHAR),
                                new SqlParameter("IN_MOBILE_NUMBER", Types.VARCHAR),
                                new SqlParameter("IN_DNIS", Types.VARCHAR),
                                new SqlParameter("IN_NETWORK_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_SUBNET_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_MOB_SEGMENT", Types.VARCHAR),
                                new SqlParameter("IN_MOBILE_STATUS", Types.VARCHAR),
                                new SqlParameter("IN_ARPU", Types.VARCHAR),
                                new SqlParameter("IN_CALLBACK_PHONE", Types.VARCHAR),
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlParameter("IN_CB_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_CB_REQUEST_TIME", Types.DATE),
                                new SqlParameter("IN_CB_STARTTIME", Types.DATE),
                                new SqlParameter("IN_CB_DELAY", Types.NUMERIC),
                                new SqlParameter("IN_CB_START_BUFFER", Types.NUMERIC),
                                new SqlParameter("IN_NEXT_CALLTIME", Types.DATE),
                                new SqlParameter("IN_MAX_TRY", Types.NUMERIC),
                                new SqlParameter("IN_TRY_INTERVAL", Types.NUMERIC),
                                new SqlParameter("IN_TRYS", Types.NUMERIC),
                                new SqlParameter("IN_PRIORITY", Types.NUMERIC),
                                new SqlParameter("IN_STATUS", Types.NUMERIC),
                                new SqlParameter("IN_WORKER_ID", Types.VARCHAR),
                                new SqlParameter("IN_LAST_UPDATE", Types.VARCHAR),
                                new SqlParameter("IN_RESULT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_ID", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_SPLIT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_VDN", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = AISUtils.ob2SqlSource(params);
                Map<String, Object> execute = simpleJdbcCall1.execute(in);
                AISLogUtil.printLine(logger, _sessionId, "Done Insert to db: " + cf.getProcerdure());
            } catch (Exception e) {
                logger.error(_sessionId, e);
                rs.setErrorCode(1);
                rs.setErrorMsg(_sessionId + e.getMessage());
            }
            return rs;
        } else {
            rs.setErrorCode(2);
            rs.setErrorMsg(_sessionId + " : " + "Can not get config for method: " + configName);
            logger.error(_sessionId + " : " + "Can not get config for method: " + configName);
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        }
    }


}
