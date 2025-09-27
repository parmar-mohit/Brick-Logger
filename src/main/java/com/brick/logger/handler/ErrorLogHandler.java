package com.brick.logger.handler;

import com.brick.logger.appenders.ConsoleAppender;
import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Error Level Log Messages
 */
public class ErrorLogHandler extends LogHandler {
    private static final String LOG_LEVEL = "error";
    public ErrorLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
        this.appenders.add(new ConsoleAppender());
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.ERROR;
    }
}
