package com.ais.cba.service;

import com.ais.cba.config.model.Config;
import com.ais.cba.config.model.LogConfig;
import com.ais.cba.service.model.DBObject;
import com.ais.cba.service.model.config.CBA_ACT;
import com.ais.cba.service.model.config.CBA_ACT_DETAIL;
import com.ais.cba.service.model.config.CBA_REQUEST;
import com.ais.cba.util.AISLogUtil;
import com.ais.cba.util.AISUtils;
import com.ais.cba.util.BeanPropertyRowMapperCustom;
import oracle.jdbc.driver.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
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

    public DBObject UpdateCallbackRequest(final String _sessionId, final CBA_REQUEST params) {
        String configName = "UpdateCallbackRequest";
        final Config cf = config.lookup(configName);
        DBObject rs = new DBObject();
        if (cf != null) {
            try {
                AISLogUtil.printLine(logger, _sessionId, "Update to db:" + cf.getProcerdure());
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
                                new SqlParameter("IN_AGENT_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_ROW_AFFECTED", Types.NUMERIC)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = AISUtils.ob2SqlSource(params);
                Map<String, Object> execute = simpleJdbcCall1.execute(in);
                Object out_row_affected = execute.get("OUT_ROW_AFFECTED");
                if (out_row_affected != null) {
                    BigDecimal bigDecimal = (BigDecimal)out_row_affected;
                    rs.setResult(bigDecimal.longValue());
                }
                AISLogUtil.printLine(logger, _sessionId, "Done Update to db: " + cf.getProcerdure());
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

    public DBObject<List<CBA_REQUEST>> GetCallbackRequest(final String _sessionId, final Integer size) {
        String configName = "GetCallbackRequest";
        final Config cf = config.lookup(configName);
        DBObject<List<CBA_REQUEST>> rs = new DBObject<List<CBA_REQUEST>>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("size", size);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate())
                        .withProcedureName(cf.getProcerdure()).returningResultSet("C_DBUSER", new BeanPropertyRowMapperCustom(CBA_REQUEST.class))
                        .useInParameterNames(
                                "IN_SIZE"
                        ).declareParameters(
                                new SqlParameter("IN_SIZE", Types.NUMERIC),
                                new SqlOutParameter("C_DBUSER", OracleTypes.CURSOR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_SIZE", size);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                List<CBA_REQUEST> list = (List) execute.get("C_DBUSER");
                rs.setResult(list);
            } catch (Exception e) {
                logger.error(_sessionId, e);
            }
            //as not found anything
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        } else {
            rs.setErrorCode(2);
            rs.setErrorMsg(_sessionId + " : " + "Can not get config for method: " + configName);
            logger.error(_sessionId + " : " + "Can not get config for method: " + configName);
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        }
    }

    public DBObject<Boolean> CheckDuplicateCallbackRequest(final String _sessionId, final String mobileNumber, final String serviceId, final int minute) {
        String configName = "CheckDuplicateCallbackRequest";
        final Config cf = config.lookup(configName);
        DBObject<Boolean> rs = new DBObject<Boolean>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("mobileNumber", mobileNumber);
                put("serviceId", serviceId);
                put("minute", minute);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_MOBILE_NUMBER",
                                "IN_SERVICE_ID" ,
                                "IN_INTERVAL"
                        ).declareParameters(
                                new SqlParameter("IN_MOBILE_NUMBER", Types.VARCHAR),
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlParameter("IN_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_MOBILE_NUMBER", mobileNumber)
                        .addValue("IN_SERVICE_ID", serviceId)
                        .addValue("IN_INTERVAL", minute);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if ("DUP".equals(execute.get("OUT_NO_RESULT"))) {
                    rs.setResult(true);
                } else {
                    rs.setResult(false);
                }
            } catch (Exception e) {
                logger.error(_sessionId, e);
            }
            //as not found anything
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        } else {
            rs.setErrorCode(2);
            rs.setErrorMsg(_sessionId + " : " + "Can not get config for method: " + configName);
            logger.error(_sessionId + " : " + "Can not get config for method: " + configName);
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        }
    }

    public DBObject<Boolean> CheckMaxConcurrentCallbackRequest(final String _sessionId, final String serviceId, final int maxCall) {
        String configName = "CheckMaxConcurrentCallbackRequest";
        final Config cf = config.lookup(configName);
        DBObject<Boolean> rs = new DBObject<Boolean>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("serviceId", serviceId);
                put("maxCall", maxCall);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_SERVICE_ID" ,
                                "IN_MAX_CONCURRENT_CALL"
                        ).declareParameters(
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlParameter("IN_MAX_CONCURRENT_CALL", Types.NUMERIC),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_SERVICE_ID", serviceId)
                        .addValue("IN_MAX_CONCURRENT_CALL", maxCall);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if ("NO".equals(execute.get("OUT_NO_RESULT"))) {
                    rs.setResult(true);
                } else {
                    rs.setResult(false);
                }
            } catch (Exception e) {
                logger.error(_sessionId, e);
            }
            //as not found anything
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        } else {
            rs.setErrorCode(2);
            rs.setErrorMsg(_sessionId + " : " + "Can not get config for method: " + configName);
            logger.error(_sessionId + " : " + "Can not get config for method: " + configName);
            AISLogUtil.printOutput(logger, _sessionId, cf, null, rs);
            return rs;
        }
    }

    public DBObject AddAct(final String _sessionId, final CBA_ACT params) {
        String configName = "AddAct";
        final Config cf = config.lookup(configName);
        DBObject rs = new DBObject();
        if (cf != null) {
            try {
                AISLogUtil.printLine(logger, _sessionId, "Update to db:" + cf.getProcerdure());
                SimpleJdbcCall simpleJdbcCall1 = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_ACT_ID",
                                "IN_STARTTIME",
                                "IN_ENDTIME",
                                "IN_CBA_REQ_ID",
                                "IN_TRYS",
                                "IN_RESULT"
                        ).declareParameters(
                                new SqlParameter("IN_ACT_ID", Types.VARCHAR),
                                new SqlParameter("IN_STARTTIME", Types.VARCHAR),
                                new SqlParameter("IN_ENDTIME", Types.VARCHAR),
                                new SqlParameter("IN_CBA_REQ_ID", Types.VARCHAR),
                                new SqlParameter("IN_TRYS", Types.NUMERIC),
                                new SqlParameter("IN_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = AISUtils.ob2SqlSource(params);
                Map<String, Object> execute = simpleJdbcCall1.execute(in);
                AISLogUtil.printLine(logger, _sessionId, "Done Update to db: " + cf.getProcerdure());
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

    public DBObject AddActDetail(final String _sessionId, final CBA_ACT_DETAIL params) {
        String configName = "AddActDetail";
        final Config cf = config.lookup(configName);
        DBObject rs = new DBObject();
        if (cf != null) {
            try {
                AISLogUtil.printLine(logger, _sessionId, "Update to db:" + cf.getProcerdure());
                SimpleJdbcCall simpleJdbcCall1 = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_ACTD_ID",
                                "IN_STARTTIME",
                                "IN_ENDTIME",
                                "IN_CBA_REQ_ID",
                                "IN_ACT_ID",
                                "IN_ACT_TYPE_ID",
                                "IN_ACT_PARAM",
                                "IN_RESULT"
                        ).declareParameters(
                                new SqlParameter("IN_ACTD_ID", Types.VARCHAR),
                                new SqlParameter("IN_STARTTIME", Types.VARCHAR),
                                new SqlParameter("IN_ENDTIME", Types.VARCHAR),
                                new SqlParameter("IN_CBA_REQ_ID", Types.VARCHAR),
                                new SqlParameter("IN_ACT_ID", Types.VARCHAR),
                                new SqlParameter("IN_ACT_TYPE_ID", Types.NUMERIC),
                                new SqlParameter("IN_ACT_PARAM", Types.VARCHAR),
                                new SqlParameter("IN_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = AISUtils.ob2SqlSource(params);
                Map<String, Object> execute = simpleJdbcCall1.execute(in);
                AISLogUtil.printLine(logger, _sessionId, "Done Update to db: " + cf.getProcerdure());
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
