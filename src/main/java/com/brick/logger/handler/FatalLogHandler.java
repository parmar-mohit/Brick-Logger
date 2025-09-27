package com.brick.logger.handler;

import com.brick.logger.appenders.ConsoleAppender;
import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Fatal Level Log Messages
 */
public class FatalLogHandler extends LogHandler {
    private static final String LOG_LEVEL = "fatal";
    public FatalLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
        this.appenders.add(new ConsoleAppender());
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.FATAL;
    }
}
