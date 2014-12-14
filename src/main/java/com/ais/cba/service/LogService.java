package com.ais.cba.service;

import com.ais.cba.config.model.Config;
import com.ais.cba.config.model.LogConfig;
import com.ais.cba.service.model.DBObject;
import com.ais.cba.service.model.config.AV_INFOVIEW;
import com.ais.cba.service.model.config.CBA_ACT;
import com.ais.cba.service.model.config.CBA_ACT_DETAIL;
import com.ais.cba.service.model.config.CBA_REQUEST;
import com.ais.cba.util.AISLogUtil;
import com.ais.cba.util.AISUtils;
import com.ais.cba.util.BeanPropertyRowMapperCustom;
import oracle.jdbc.driver.OracleTypes;
import org.apache.commons.lang.StringUtils;
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
                                "IN_ATTEMPT",
                                "IN_PRIORITY",
                                "IN_STATUS",
                                "IN_WORKER_ID",
                                "IN_LAST_UPDATE",
                                "IN_RESULT",
                                "IN_AGENT_ID",
                                "IN_AGENT_SPLIT",
                                "IN_AGENT_VDN",
                                "IN_STATUS_COUNT",
                                "IN_CHANNEL_ID",
                                "IN_INTERACTION_ID"
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
                                new SqlParameter("IN_CB_REQUEST_TIME", Types.TIMESTAMP),
                                new SqlParameter("IN_CB_STARTTIME", Types.TIMESTAMP),
                                new SqlParameter("IN_CB_DELAY", Types.NUMERIC),
                                new SqlParameter("IN_CB_START_BUFFER", Types.NUMERIC),
                                new SqlParameter("IN_NEXT_CALLTIME", Types.TIMESTAMP),
                                new SqlParameter("IN_MAX_TRY", Types.NUMERIC),
                                new SqlParameter("IN_TRY_INTERVAL", Types.NUMERIC),
                                new SqlParameter("IN_ATTEMPT", Types.NUMERIC),
                                new SqlParameter("IN_PRIORITY", Types.NUMERIC),
                                new SqlParameter("IN_STATUS", Types.NUMERIC),
                                new SqlParameter("IN_WORKER_ID", Types.VARCHAR),
                                new SqlParameter("IN_LAST_UPDATE", Types.VARCHAR),
                                new SqlParameter("IN_RESULT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_ID", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_SPLIT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_VDN", Types.VARCHAR) ,
                                new SqlParameter("IN_STATUS_COUNT", Types.NUMERIC),
                                new SqlParameter("IN_CHANNEL_ID", Types.VARCHAR),
                                new SqlParameter("IN_INTERACTION_ID", Types.VARCHAR)
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
                                "IN_ATTEMPT",
                                "IN_PRIORITY",
                                "IN_STATUS",
                                "IN_WORKER_ID",
                                "IN_LAST_UPDATE",
                                "IN_RESULT",
                                "IN_AGENT_ID",
                                "IN_AGENT_SPLIT",
                                "IN_AGENT_VDN",
                                "IN_STATUS_COUNT",
                                "IN_CHANNEL_ID",
                                "IN_INTERACTION_ID"
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
                                new SqlParameter("IN_ATTEMPT", Types.NUMERIC),
                                new SqlParameter("IN_PRIORITY", Types.NUMERIC),
                                new SqlParameter("IN_STATUS", Types.NUMERIC),
                                new SqlParameter("IN_WORKER_ID", Types.VARCHAR),
                                new SqlParameter("IN_LAST_UPDATE", Types.VARCHAR),
                                new SqlParameter("IN_RESULT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_ID", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_SPLIT", Types.VARCHAR),
                                new SqlParameter("IN_AGENT_VDN", Types.VARCHAR),
                                new SqlParameter("IN_STATUS_COUNT", Types.NUMERIC),
                                new SqlParameter("IN_CHANNEL_ID", Types.VARCHAR),
                                new SqlParameter("IN_INTERACTION_ID", Types.VARCHAR),
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


    public DBObject AddOneX(final String _sessionId, final AV_INFOVIEW params) {
        String configName = "AddOneX";
        final Config cf = config.lookup(configName);
        DBObject rs = new DBObject();
        if (cf != null) {
            try {
                AISLogUtil.printLine(logger, _sessionId, "Insert to db:" + cf.getProcerdure());
                SimpleJdbcCall simpleJdbcCall1 = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_SITEID",
                                "IN_GUID",
                                "IN_UUI",
                                "IN_DNIS",
                                "IN_CALLID",
                                "IN_IVR_PORT",
                                "IN_BUSINESS_GROUP",
                                "IN_MOB_SEGMENT",
                                "IN_SECRET_PASSWORD",
                                "IN_CUST_NAME",
                                "IN_CALLER_VERIFY",
                                "IN_MOBILE_NO_STATUS",
                                "IN_BIRTH_DATE",
                                "IN_SERVICE_YEAR",
                                "IN_CHURN_SCORE",
                                "IN_ACC_BAL",
                                "IN_MOBILE_NUMBER",
                                "IN_CLV_SEGMENT",
                                "IN_CUST_INSIGHT",
                                "IN_CUST_CLASS",
                                "IN_IVR_POINT",
                                "IN_MNP",
                                "IN_XFER_VDN",
                                "IN_XFER_VDN_NAME",
                                "IN_PMCC_VERIFY",
                                "IN_ANI",
                                "IN_CRM_SEGMENT",
                                "IN_LOCATION_CODE",
                                "IN_BILL_CYCLE",
                                "IN_BLACK_LIST",
                                "IN_PAYMENT_TYPE",
                                "IN_ID_CARD_NO",
                                "IN_PP_VERIFY",
                                "IN_PP_VAS_PACKAGE",
                                "IN_CC_VERIFY",
                                "IN_CC_NUM",
                                "IN_EXP_DATE",
                                "IN_OVERFLOW",
                                "IN_CUST_TYPE",
                                "IN_SUBNET_TYPE",
                                "IN_NETWORK_TYPE"
                        ).declareParameters(
                                new SqlParameter("IN_SITEID", Types.VARCHAR),
                                new SqlParameter("IN_GUID", Types.VARCHAR),
                                new SqlParameter("IN_UUI", Types.VARCHAR),
                                new SqlParameter("IN_DNIS", Types.VARCHAR),
                                new SqlParameter("IN_CALLID", Types.VARCHAR),
                                new SqlParameter("IN_IVR_PORT", Types.VARCHAR),
                                new SqlParameter("IN_BUSINESS_GROUP", Types.VARCHAR),
                                new SqlParameter("IN_MOB_SEGMENT", Types.VARCHAR),
                                new SqlParameter("IN_SECRET_PASSWORD", Types.VARCHAR),
                                new SqlParameter("IN_CUST_NAME", Types.VARCHAR),
                                new SqlParameter("IN_CALLER_VERIFY", Types.VARCHAR),
                                new SqlParameter("IN_MOBILE_NO_STATUS", Types.VARCHAR),
                                new SqlParameter("IN_BIRTH_DATE", Types.VARCHAR),
                                new SqlParameter("IN_SERVICE_YEAR", Types.VARCHAR),
                                new SqlParameter("IN_CHURN_SCORE", Types.VARCHAR),
                                new SqlParameter("IN_ACC_BAL", Types.VARCHAR),
                                new SqlParameter("IN_MOBILE_NUMBER", Types.VARCHAR),
                                new SqlParameter("IN_CLV_SEGMENT", Types.VARCHAR),
                                new SqlParameter("IN_CUST_INSIGHT", Types.VARCHAR),
                                new SqlParameter("IN_CUST_CLASS", Types.VARCHAR),
                                new SqlParameter("IN_IVR_POINT", Types.VARCHAR),
                                new SqlParameter("IN_MNP", Types.VARCHAR),
                                new SqlParameter("IN_XFER_VDN", Types.VARCHAR),
                                new SqlParameter("IN_XFER_VDN_NAME", Types.VARCHAR),
                                new SqlParameter("IN_PMCC_VERIFY", Types.VARCHAR),
                                new SqlParameter("IN_ANI", Types.VARCHAR),
                                new SqlParameter("IN_CRM_SEGMENT", Types.VARCHAR),
                                new SqlParameter("IN_LOCATION_CODE", Types.VARCHAR),
                                new SqlParameter("IN_BILL_CYCLE", Types.VARCHAR),
                                new SqlParameter("IN_BLACK_LIST", Types.VARCHAR),
                                new SqlParameter("IN_PAYMENT_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_ID_CARD_NO", Types.VARCHAR),
                                new SqlParameter("IN_PP_VERIFY", Types.VARCHAR),
                                new SqlParameter("IN_PP_VAS_PACKAGE", Types.VARCHAR),
                                new SqlParameter("IN_CC_VERIFY", Types.VARCHAR),
                                new SqlParameter("IN_CC_NUM", Types.VARCHAR),
                                new SqlParameter("IN_EXP_DATE", Types.VARCHAR),
                                new SqlParameter("IN_OVERFLOW", Types.VARCHAR),
                                new SqlParameter("IN_CUST_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_SUBNET_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_NETWORK_TYPE", Types.VARCHAR)
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

    public DBObject<CBA_REQUEST> GetCallbackRequestLog(final String _sessionId, final String idx) {
        String configName = "GetCallbackRequestLog";
        final Config cf = config.lookup(configName);
        DBObject<CBA_REQUEST> rs = new DBObject<CBA_REQUEST>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("idx", idx);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_IDX"
                        ).declareParameters(
                                new SqlParameter("IN_IDX", Types.VARCHAR),

                                new SqlOutParameter("OUT_IDX", Types.VARCHAR),
                                new SqlOutParameter("OUT_REQ_DATETIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_TIMEZONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_IVR_SESSIONID", Types.VARCHAR),
                                new SqlOutParameter("OUT_VDUID", Types.VARCHAR),
                                new SqlOutParameter("OUT_ANI", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOBILE_NUMBER", Types.VARCHAR),
                                new SqlOutParameter("OUT_DNIS", Types.VARCHAR),
                                new SqlOutParameter("OUT_NETWORK_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_SUBNET_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOB_SEGMENT", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOBILE_STATUS", Types.VARCHAR),
                                new SqlOutParameter("OUT_ARPU", Types.VARCHAR),
                                new SqlOutParameter("OUT_CALLBACK_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_CB_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_CB_REQUEST_TIME", Types.DATE),
                                new SqlOutParameter("OUT_CB_STARTTIME", Types.DATE),
                                new SqlOutParameter("OUT_CB_DELAY", Types.NUMERIC),
                                new SqlOutParameter("OUT_CB_START_BUFFER", Types.NUMERIC),
                                new SqlOutParameter("OUT_NEXT_CALLTIME", Types.DATE),
                                new SqlOutParameter("OUT_MAX_TRY", Types.NUMERIC),
                                new SqlOutParameter("OUT_TRY_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_ATTEMPT", Types.NUMERIC),
                                new SqlOutParameter("OUT_PRIORITY", Types.NUMERIC),
                                new SqlOutParameter("OUT_STATUS", Types.NUMERIC),
                                new SqlOutParameter("OUT_WORKER_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_LAST_UPDATE", Types.VARCHAR),
                                new SqlOutParameter("OUT_RESULT", Types.VARCHAR),
                                new SqlOutParameter("OUT_AGENT_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_AGENT_SPLIT", Types.VARCHAR),
                                new SqlOutParameter("OUT_AGENT_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_STATUS_COUNT", Types.VARCHAR),
                                new SqlOutParameter("OUT_CHANNEL_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_INTERACTION_ID", Types.VARCHAR),

                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_IDX", idx);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_REQUEST ob = new CBA_REQUEST();
                    AISUtils.map2Object(ob, execute);
                    rs.setResult(ob);
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

}
