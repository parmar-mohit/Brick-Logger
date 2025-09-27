package com.brick.logger.handler;

import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Debug Level Log Messages
 */
public class DebugLogHandler extends LogHandler {
    private static final String LOG_LEVEL = "debug";
    public DebugLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.DEBUG;
    }
}
