package com.brick.logger.handler;

import com.brick.logger.appenders.FileAppender;
import com.brick.logger.appenders.LogAppender;
import com.brick.logger.utility.Config;
import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LogHandler {
    private static final String LOG_LEVEL = "all";
    private static final String CLEAR_FLAG = "clear";

    protected List<LogAppender> appenders;
    protected LogHandler nextHandler;
    public LogHandler() {
        this.appenders = new ArrayList<>();

        initialiseFileAppenders(LOG_LEVEL);
    }

    protected void initialiseFileAppenders(String logLevel){
        Map<String,Object> loggerConfig = Config.getInstance().getLoggerConfig();

        boolean clearExistingLogs = false;
        try{
            clearExistingLogs = (boolean)loggerConfig.get(CLEAR_FLAG);
        }catch (Exception e){
            e.printStackTrace();
        }

        // All Config
        Map<String,Object> logLevelData = (Map<String, Object>) loggerConfig.get(logLevel);
        if( null != logLevelData ){
            Object fileAppenderData = logLevelData.get(Config.FILE);
            try {
                if( fileAppenderData instanceof String ){
                    this.appenders.add(new FileAppender((String)fileAppenderData, clearExistingLogs));
                }else if( fileAppenderData instanceof List ){
                    for( String filePath: (List<String>)fileAppenderData ){
                        this.appenders.add(new FileAppender(filePath, clearExistingLogs));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
