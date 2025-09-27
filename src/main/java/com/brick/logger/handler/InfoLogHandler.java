package com.brick.logger.handler;


import com.brick.logger.utility.LogLevel;

/*
    Description: Handles Info Level Log Messages
 */
public class InfoLogHandler extends LogHandler {
    private static final String LOG_LEVEL = "info";

    public InfoLogHandler(){
        super();
        initialiseFileAppenders(LOG_LEVEL);
    }

    /*
        Description: Returns LogLevel of Handler
     */
    @Override
    LogLevel getCurrentLogLevel() {
        return LogLevel.INFO;
    }
}
