package com.brick.logger.handler;

import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Warn Level Log Messages
 */
public class WarnLogHandler extends LogHandler {
    private static final String LOG_LEVEL = "warn";
    public WarnLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.WARN;
    }
}
