package com.brick.logger.utility;

import java.time.LocalDateTime;

/*
    Description: An Object Representing all details of the LogMessage
 */
public class Message {
    private LocalDateTime timestamp;
    private LogLevel level;
    private String logMessage;
    private String logThread;

    private static final String SEPERATOR = " ";

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.logThread = Thread.currentThread().getName();
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public String toString() {
        return timestamp + SEPERATOR + level + SEPERATOR + "thread-name:" + logThread + SEPERATOR + "log-message:"+logMessage;
    }
}
