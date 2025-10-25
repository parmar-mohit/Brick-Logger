package com.brick.logger.handler;

import com.brick.logger.appenders.FileAppender;
import com.brick.logger.appenders.LogAppender;
import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;
import com.brick.utilities.Config;

import java.util.ArrayList;
import java.util.List;

public abstract class LogHandler {
    private static final String LOG_LEVEL = "all";
    private static final String CONFIG_ROOT = "logger";
    private static final String CLEAR_FLAG = CONFIG_ROOT + Config.SEPERATOR + "clear";

    protected List<LogAppender> appenders;
    protected LogHandler nextHandler;
    public LogHandler() {
        this.appenders = new ArrayList<>();

        initialiseFileAppenders(LOG_LEVEL);
    }

    protected void initialiseFileAppenders(String logLevel){

        boolean clearExistingLogs = false;
        try{
            clearExistingLogs  = Config.getConfigBoolean(CLEAR_FLAG);
        }catch (Exception e){
            e.printStackTrace();
        }

        // All Config
        String configKey = CONFIG_ROOT + Config.SEPERATOR + logLevel + Config.SEPERATOR + "file";
        try {
            Object fileAppenderData = Config.getConfigObject(configKey);
            if( fileAppenderData instanceof String ){
                this.appenders.add(new FileAppender((String)fileAppenderData, clearExistingLogs));
            }else if( fileAppenderData instanceof List ){
                for( String filePath: (List<String>)fileAppenderData ){
                    this.appenders.add(new FileAppender(filePath, clearExistingLogs));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void writeLog(Message message){
        if( message.getLevel() == getCurrentLogLevel() ){
            for (LogAppender appender : appenders) {
                appender.appendLog(message);
            }
        }else if( null != this.nextHandler ){
            this.nextHandler.writeLog(message);
        }
    }

    public static LogHandler getLogHandlerChain(){
        LogHandler fatalHandler = new FatalLogHandler();
        LogHandler errorHandler = new ErrorLogHandler();
        LogHandler warnHandler = new WarnLogHandler();
        LogHandler infoHandler = new InfoLogHandler();
        LogHandler debugHandler = new DebugLogHandler();
        LogHandler traceLogHandler = new TraceLogHandler();

        fatalHandler.setNextHandler(errorHandler);
        errorHandler.setNextHandler(warnHandler);
        warnHandler.setNextHandler(infoHandler);
        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(traceLogHandler);
        traceLogHandler.setNextHandler(null);

        return fatalHandler;
    }

    abstract  LogLevel getCurrentLogLevel();

}
