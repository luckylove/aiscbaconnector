package com.ais.config.model;

import com.ais.util.AISUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetConfig {

    private String localPath;
    private String historyPath;

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
                config.setConnectStr(ds.getUrl() + "/" + ds.getUsername());
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

    public String getHistoryPath() {
        return historyPath;
    }

    public void setHistoryPath(String historyPath) {
        this.historyPath = historyPath;
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

    public Map<String, Config> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, Config> configMap) {
        this.configMap = configMap;
    }
}
