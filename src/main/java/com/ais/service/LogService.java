package com.ais.service;

import com.ais.config.model.LogConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


}
