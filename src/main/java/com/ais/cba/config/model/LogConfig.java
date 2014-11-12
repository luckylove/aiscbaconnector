package com.ais.cba.config.model;

import com.ais.cba.util.AISUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: son.nguyen
 * Date: 10/24/13
 * Time: 1:28 PM
 */
public class LogConfig {

    private String localPath;

    private List<DataSource> dataSources = new ArrayList<DataSource>();

    //init later
    private Map<String, Config> configMap;

    //this method will init config and database
    public void initConfigMap() {
        configMap = new HashMap<String, Config>(10);
        for (DataSource ds : this.dataSources) {
            javax.sql.DataSource dataSource = AISUtils.initDataSource(ds);
            JdbcTemplate jdbcTemplate = AISUtils.initJdbcTemplate(dataSource);
            jdbcTemplate.setResultsMapCaseInsensitive(true);
            for (Config config : ds.getConfigs()) {
                config.setDataSource(dataSource);
                config.setJdbcTemplate(jdbcTemplate);
                configMap.put(config.getMethod(), config);
            }
        }
    }

    public Config lookup(String name) {
        return this.configMap.get(name);
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public void addDataSource(DataSource dataSource) {
        this.dataSources.add(dataSource);
    }
}
