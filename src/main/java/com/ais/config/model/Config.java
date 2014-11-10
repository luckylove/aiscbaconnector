package com.ais.config.model;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * User: son.nguyen
 * Date: 10/30/13
 * Time: 10:06 PM
 */
public class Config {

    public static final String DB = "DB";
    public static final String FILE = "FILE";
    public static final String MEMORY = "MEMORY";

    private String method;
    private String procerdure;
    private String queryMode;
    private boolean fallback;

    private String config;
    private String connectStr;//just for log
    //refer later
    private javax.sql.DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private String tableName;


    private boolean dbDown;
    private int timeoutFirst = -1;
    private int timeoutLast = -1;

    public int getTimeoutFirst() {
        if (timeoutFirst == -1) {
            return 5;
        }
        return timeoutFirst;
    }

    public void setTimeoutFirst(int timeoutFirst) {
        this.timeoutFirst = timeoutFirst;
    }

    public int getTimeoutLast() {
        if (timeoutLast == -1) {
            return 5;
        }
        return timeoutLast;
    }

    public void setTimeoutLast(int timeoutLast) {
        this.timeoutLast = timeoutLast;
    }

    public void setDbDown(boolean dbDown) {
        this.dbDown = dbDown;
    }

    public String getXqueryText() {
        return config;
    }


    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProcerdure() {
        return procerdure;
    }

    public void setProcerdure(String procerdure) {
        this.procerdure = procerdure;
    }

    public String getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public boolean isFallback() {
        return fallback;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isDBQuery() {
        return DB.equals(this.queryMode);
    }

    /**
     * we no support this mode
     * @return
     */
    public boolean isFileQuery() {
        return false;
        //return FILE.equals(this.queryMode);
    }

    public boolean isMemoryQuery() {
        return MEMORY.equals(this.queryMode)||FILE.equals(this.queryMode);
    }

    public String getConnectStr() {
        return connectStr;
    }

    public void setConnectStr(String connectStr) {
        this.connectStr = connectStr;
    }
}
