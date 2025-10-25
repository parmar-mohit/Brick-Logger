package com.brick.logger;

import com.brick.logger.handler.LogHandler;
import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;

import java.time.LocalDateTime;

/*
    Logger Class which uses singleton pattern to ensure only one instance is created,
    contains static method for different log level and exception that internally creates a log message
    and passes it to handler which then outputs it to various destinations
 */
public class Logger {

    static {
        instance = new Logger();
    }
    private static Logger instance;

    private final LogHandler handler;
    private Logger(){
        handler = LogHandler.getLogHandlerChain();
    }

    public LogHandler getHandler() {
        return handler;
    }

    public static void trace(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.TRACE);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void debug(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.DEBUG);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void info(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.INFO);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void warn(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.WARN);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void error(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.ERROR);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void fatal(String message){
        Message logMessage = new Message();
        logMessage.setLevel(LogLevel.FATAL);
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLogMessage(message);

        instance.handler.writeLog(logMessage);
    }

    public static void logException(Exception exception){
        Message exceptionMessage = new Message();
        exceptionMessage.setLevel(LogLevel.ERROR);
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setLogMessage(exception.getMessage());
        instance.handler.writeLog(exceptionMessage);

        Message exceptionMessageStackTrace = new Message();
        exceptionMessageStackTrace.setLevel(LogLevel.ERROR);
        exceptionMessageStackTrace.setTimestamp(LocalDateTime.now());

        StringBuilder exceptionTrace = new StringBuilder();
        for( StackTraceElement traceLine: exception.getStackTrace() ){
            exceptionTrace.append(traceLine.toString()).append("\n\t\t");
        }
        String stackTraceMessage = exceptionTrace.substring(0,exceptionTrace.length()-2);
        exceptionMessageStackTrace.setLogMessage(stackTraceMessage);
        instance.handler.writeLog(exceptionMessageStackTrace);
    }
}
