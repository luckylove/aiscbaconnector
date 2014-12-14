package com.ais.cba.service;

import com.ais.cba.config.model.Config;
import com.ais.cba.config.model.GetConfig;
import com.ais.cba.service.model.DBObject;
import com.ais.cba.service.model.config.*;
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

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * User: son.nguyen
 * Date: 10/27/13
 * Time: 9:16 AM
 */
public class ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private GetConfig config;
    private ExecutorService executor;

    public ConfigService(GetConfig config) {
        this.config = config;
        //new dynamic thread pool
        //executor = Executors.newCachedThreadPool();
    }

    public DBObject<CBA_PARAM> GetParam(final String _sessionId, final String paramName) {
        String configName = "GetParam";
        final Config cf = config.lookup(configName);
        DBObject<CBA_PARAM> rs = new DBObject<CBA_PARAM>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("paramName", paramName);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_PARAM_NAME"
                        ).declareParameters(
                                new SqlParameter("IN_PARAM_NAME", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_PARAM_NAME", Types.VARCHAR),
                                new SqlOutParameter("OUT_VALUE", Types.VARCHAR),
                                new SqlOutParameter("OUT_DESCRIPTION", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_PARAM_NAME", paramName);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String)execute.get("OUT_NO_RESULT"))) {
                    CBA_PARAM ob = new CBA_PARAM();
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

    public DBObject<CBA_BLACKLIST> GetBlackList(final String _sessionId, final String phone) {
        String configName = "GetBlackList";
        final Config cf = config.lookup(configName);
        DBObject<CBA_BLACKLIST> rs = new DBObject<CBA_BLACKLIST>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("phone", phone);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_PHONE"
                        ).declareParameters(
                                new SqlParameter("IN_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_CREATE_DATE", Types.DATE),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_PHONE", phone);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_BLACKLIST ob = new CBA_BLACKLIST();
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

    public DBObject<CBA_DNC> GetDNC(final String _sessionId, final String phone) {
        String configName = "GetDNC";
        final Config cf = config.lookup(configName);
        DBObject<CBA_DNC> rs = new DBObject<CBA_DNC>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("phone", phone);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_PHONE"
                        ).declareParameters(
                                new SqlParameter("IN_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_CREATE_DATE", Types.DATE),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_PHONE", phone);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_DNC ob = new CBA_DNC();
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


    public DBObject<CBA_TIMEZONE> GetTimeZone(final String _sessionId, final String timezoneId) {
        String configName = "GetTimeZone";
        final Config cf = config.lookup(configName);
        DBObject<CBA_TIMEZONE> rs = new DBObject<CBA_TIMEZONE>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("timezoneId", timezoneId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_TIMEZONE_ID"
                        ).declareParameters(
                                new SqlParameter("IN_TIMEZONE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_TIMEZONE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_TIMEZONE_NAME", Types.VARCHAR),
                                new SqlOutParameter("OUT_GMT_DIFF", Types.NUMERIC),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_TIMEZONE_ID", timezoneId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_TIMEZONE ob = new CBA_TIMEZONE();
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


    public DBObject<CBA_DNIS> GetDNIS(final String _sessionId, final String phone) {
        String configName = "GetDNIS";
        final Config cf = config.lookup(configName);
        DBObject<CBA_DNIS> rs = new DBObject<CBA_DNIS>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("phone", phone);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_DNIS"
                        ).declareParameters(
                                new SqlParameter("IN_DNIS", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_DNIS", Types.VARCHAR),
                                new SqlOutParameter("OUT_DESCRIPTION", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_TIME_CHECK", Types.VARCHAR),
                                new SqlOutParameter("OUT_START_DATETIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_END_DATETIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_DNIS", phone);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_DNIS ob = new CBA_DNIS();
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

    public DBObject<CBA_HOLIDAY> GetHoliday(final String _sessionId, final String holidayId) {
        String configName = "GetHoliday";
        final Config cf = config.lookup(configName);
        DBObject<CBA_HOLIDAY> rs = new DBObject<CBA_HOLIDAY>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("holidayId", holidayId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_HOLIDAY_ID"
                        ).declareParameters(
                                new SqlParameter("IN_HOLIDAY_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_HOLIDAY_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_DESCRIPTION", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_TIME_CHECK", Types.VARCHAR),
                                new SqlOutParameter("OUT_START_DATETIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_END_DATETIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_HOLIDAY_ID", holidayId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_HOLIDAY ob = new CBA_HOLIDAY();
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


    public DBObject<CBA_INBOUND_CONF> GetInboundConf(final String _sessionId, final String langId, final String networkType, final String subnetType,
                                                final String mobileSegment, final String arpu, final String xferVdn) {
        String configName = "GetInboundConf";
        final Config cf = config.lookup(configName);
        DBObject<CBA_INBOUND_CONF> rs = new DBObject<CBA_INBOUND_CONF>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("langId", langId);
                put("networkType", networkType);
                put("subnetType", subnetType);
                put("mobileSegment", mobileSegment);
                put("arpu", arpu);
                put("xferVdn", xferVdn);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_LANG_ID",
                                "IN_NETWORK_TYPE",
                                "IN_SUBNET_TYPE",
                                "IN_MOB_SEGMENT",
                                "IN_ARPU",
                                "IN_XFER_VDN"
                        ).declareParameters(
                                new SqlParameter("IN_LANG_ID", Types.VARCHAR),
                                new SqlParameter("IN_NETWORK_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_SUBNET_TYPE", Types.VARCHAR),
                                new SqlParameter("IN_MOB_SEGMENT", Types.VARCHAR),
                                new SqlParameter("IN_ARPU", Types.VARCHAR),
                                new SqlParameter("IN_XFER_VDN", Types.VARCHAR),

                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_NETWORK_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_SUBNET_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOB_SEGMENT", Types.VARCHAR),
                                new SqlOutParameter("OUT_ARPU_RANGE", Types.VARCHAR),
                                new SqlOutParameter("OUT_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_INBOUND_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_START_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_END_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_ACTIVE_REQ", Types.NUMERIC),
                                new SqlOutParameter("OUT_PRIORITY", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_CONCURRENT_CALL", Types.NUMERIC),
                                new SqlOutParameter("OUT_DUPLICATE_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CB_START_BUFFER", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_LANG_ID", langId)
                        .addValue("IN_NETWORK_TYPE", networkType)
                        .addValue("IN_SUBNET_TYPE", subnetType)
                        .addValue("IN_MOB_SEGMENT", mobileSegment)
                        .addValue("IN_ARPU", arpu)
                        .addValue("IN_XFER_VDN", xferVdn);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_INBOUND_CONF ob = new CBA_INBOUND_CONF();
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


    public DBObject<CBA_INBOUND_CONF> GetInboundConfByInboundVDN(final String _sessionId, final String inboundVdn) {
        String configName = "GetInboundConfByInboundVDN";
        final Config cf = config.lookup(configName);
        DBObject<CBA_INBOUND_CONF> rs = new DBObject<CBA_INBOUND_CONF>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("inboundVdn", inboundVdn);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_INBOUND_VDN"
                        ).declareParameters(
                                new SqlParameter("IN_INBOUND_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_NETWORK_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_SUBNET_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOB_SEGMENT", Types.VARCHAR),
                                new SqlOutParameter("OUT_ARPU_RANGE", Types.VARCHAR),
                                new SqlOutParameter("OUT_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_INBOUND_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_START_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_END_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_ACTIVE_REQ", Types.NUMERIC),
                                new SqlOutParameter("OUT_PRIORITY", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_CONCURRENT_CALL", Types.NUMERIC),
                                new SqlOutParameter("OUT_DUPLICATE_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CB_START_BUFFER", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_INBOUND_VDN", inboundVdn);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_INBOUND_CONF ob = new CBA_INBOUND_CONF();
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

    public DBObject<CBA_INBOUND_CONF> GetInboundConfByServiceID(final String _sessionId, final String serviceId) {
        String configName = "GetInboundConfByServiceID";
        final Config cf = config.lookup(configName);
        DBObject<CBA_INBOUND_CONF> rs = new DBObject<CBA_INBOUND_CONF>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("serviceId", serviceId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_SERVICE_ID"
                        ).declareParameters(
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_NETWORK_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_SUBNET_TYPE", Types.VARCHAR),
                                new SqlOutParameter("OUT_MOB_SEGMENT", Types.VARCHAR),
                                new SqlOutParameter("OUT_ARPU_RANGE", Types.VARCHAR),
                                new SqlOutParameter("OUT_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_XFER_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_CBA_INBOUND_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_START_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_END_TIME", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_ACTIVE_REQ", Types.NUMERIC),
                                new SqlOutParameter("OUT_PRIORITY", Types.VARCHAR),
                                new SqlOutParameter("OUT_MAX_CONCURRENT_CALL", Types.NUMERIC),
                                new SqlOutParameter("OUT_DUPLICATE_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CB_START_BUFFER", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_CBA_REPEAT_VDN", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_SERVICE_ID", serviceId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_INBOUND_CONF ob = new CBA_INBOUND_CONF();
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

    public DBObject<CBA_SERVICE> GetService(final String _sessionId, final String serviceId) {
        String configName = "GetService";
        final Config cf = config.lookup(configName);
        DBObject<CBA_SERVICE> rs = new DBObject<CBA_SERVICE>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("serviceId", serviceId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_SERVICE_ID"
                        ).declareParameters(
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_SERVICE_NAME", Types.VARCHAR),
                                new SqlOutParameter("OUT_DESCRIPTION", Types.VARCHAR),
                                new SqlOutParameter("OUT_SLA", Types.NUMERIC),
                                new SqlOutParameter("OUT_MENU_SET_ID", Types.NUMERIC),
                                new SqlOutParameter("OUT_MAX_TRY", Types.NUMERIC),
                                new SqlOutParameter("OUT_TRY_INTERVAL", Types.NUMERIC),
                                new SqlOutParameter("OUT_AGENT_SPLIT", Types.VARCHAR),
                                new SqlOutParameter("OUT_PHR_AGENT_GREETING", Types.VARCHAR),
                                new SqlOutParameter("OUT_PHR_CUST_GREETING", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_SERVICE_ID", serviceId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_SERVICE ob = new CBA_SERVICE();
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

    public DBObject<CBA_MENU_SET> GetMenuSet(final String _sessionId, final Long menuSetId) {
        String configName = "GetMenuSet";
        final Config cf = config.lookup(configName);
        DBObject<CBA_MENU_SET> rs = new DBObject<CBA_MENU_SET>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("menuSetId", menuSetId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_MENU_SET_ID"
                        ).declareParameters(
                                new SqlParameter("IN_MENU_SET_ID", Types.NUMERIC),
                                new SqlOutParameter("OUT_MENU_SET_ID", Types.NUMERIC),
                                new SqlOutParameter("OUT_MENU_SET_NAME", Types.VARCHAR),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_DESCRIPTION", Types.VARCHAR),
                                new SqlOutParameter("OUT_PHR_MENU_SET", Types.VARCHAR),
                                new SqlOutParameter("OUT_MENU_SET_MENUID", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_MENU_SET_ID", menuSetId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //AISLogUtil.printInput(logger, _sessionId, cf, null, execute);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_MENU_SET ob = new CBA_MENU_SET();
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

    public DBObject<List<CBA_MENU_DIGIT>> GetMenuDigits(final String _sessionId, final Long menuSetId) {
        String configName = "GetMenuDigits";
        final Config cf = config.lookup(configName);
        DBObject<List<CBA_MENU_DIGIT>> rs = new DBObject<List<CBA_MENU_DIGIT>>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("menuSetId", menuSetId);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_MENU_SET_ID"
                        ).declareParameters(
                                new SqlParameter("IN_MENU_SET_ID", Types.NUMERIC),
                                new SqlOutParameter("C_DBUSER", OracleTypes.CURSOR)
                        )
                        .withProcedureName(cf.getProcerdure()).returningResultSet("C_DBUSER", new BeanPropertyRowMapperCustom(CBA_MENU_DIGIT.class));

                SqlParameterSource in = new MapSqlParameterSource().addValue("IN_MENU_SET_ID", menuSetId);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                List<CBA_MENU_DIGIT> list = (List) execute.get("C_DBUSER");
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


    public DBObject<CBA_PRIORITY> GetPriority(final String _sessionId, final Long idx) {
        String configName = "GetPriority";
        final Config cf = config.lookup(configName);
        DBObject<CBA_PRIORITY> rs = new DBObject<CBA_PRIORITY>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("idx", idx);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_IDX"
                        ).declareParameters(
                                new SqlParameter("IN_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_PRIORITY", Types.VARCHAR),
                                new SqlOutParameter("OUT_SCORE", Types.NUMERIC),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_IDX", idx);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_PRIORITY ob = new CBA_PRIORITY();
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

    public DBObject<CBA_NOCBA> GetNoCBA(final String _sessionId, final String phone) {
        String configName = "GetNoCBA";
        final Config cf = config.lookup(configName);
        DBObject<CBA_NOCBA> rs = new DBObject<CBA_NOCBA>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("phone", phone);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_PHONE"
                        ).declareParameters(
                                new SqlParameter("IN_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_IDX", Types.NUMERIC),
                                new SqlOutParameter("OUT_PHONE", Types.VARCHAR),
                                new SqlOutParameter("OUT_CREATE_DATE", Types.DATE),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_PHONE", phone);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_NOCBA ob = new CBA_NOCBA();
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

    public DBObject<CBA_SMS_CONF> GetSMSConf(final String _sessionId, final String serviceId, final Long status, final Long statusCount) {
        String configName = "GetSMSConf";
        final Config cf = config.lookup(configName);
        DBObject<CBA_SMS_CONF> rs = new DBObject<CBA_SMS_CONF>();
        if (cf != null) {
            AISLogUtil.printInput(logger, _sessionId, cf, null, new HashMap<String, Object>() {{
                put("serviceId", serviceId);
                put("status", status);
                put("statusCount", statusCount);
            }});
            try {
                SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(cf.getJdbcTemplate()).withoutProcedureColumnMetaDataAccess()
                        .useInParameterNames(
                                "IN_SERVICE_ID" ,
                                "IN_STATUS" ,
                                "IN_STATUS_COUNT"
                        ).declareParameters(
                                new SqlParameter("IN_SERVICE_ID", Types.VARCHAR),
                                new SqlParameter("IN_STATUS", Types.NUMERIC),
                                new SqlParameter("IN_STATUS_COUNT", Types.NUMERIC),

                                new SqlOutParameter("OUT_SMS_CONF_ID", Types.NUMERIC),
                                new SqlOutParameter("OUT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_STATUS", Types.NUMERIC),
                                new SqlOutParameter("OUT_LANG_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_STATUS_COUNT", Types.NUMERIC),
                                new SqlOutParameter("OUT_ENABLE", Types.VARCHAR),
                                new SqlOutParameter("OUT_IS_SMS", Types.VARCHAR),
                                new SqlOutParameter("OUT_SMS_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_IS_GENACT", Types.VARCHAR),
                                new SqlOutParameter("OUT_GENACT_SERVICE_ID", Types.VARCHAR),
                                new SqlOutParameter("OUT_NO_RESULT", Types.VARCHAR)
                        )
                        .withProcedureName(cf.getProcerdure());
                SqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_SERVICE_ID", serviceId)
                        .addValue("IN_STATUS", status)
                        .addValue("IN_STATUS_COUNT", statusCount);

                Map<String, Object> execute = simpleJdbcCall.execute(in);
                //map to object
                if (StringUtils.isEmpty((String) execute.get("OUT_NO_RESULT"))) {
                    CBA_SMS_CONF ob = new CBA_SMS_CONF();
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
