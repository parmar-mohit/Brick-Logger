package com.brick.logger.handler;

import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Trace Level Log Messages
 */
public class TraceLogHandler extends LogHandler{
    private static final String LOG_LEVEL = "trace";
    public TraceLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.TRACE;
    }
}
