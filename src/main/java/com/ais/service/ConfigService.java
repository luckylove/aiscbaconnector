package com.ais.service;

import com.ais.config.model.Config;
import com.ais.config.model.GetConfig;
import com.ais.service.model.DBObject;
import com.ais.service.model.config.CBA_PARAM;
import com.ais.util.AISLogUtil;
import com.ais.util.AISUtils;
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
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


}
