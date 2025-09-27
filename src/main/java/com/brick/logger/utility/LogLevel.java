package com.brick.logger.utility;

/*
    Description: LogLevel for Messages, Higher the value Higher the prioritys
 */
public enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5),
    FATAL(6);

    private final int value;
    LogLevel(int value) {
        this.value = value;
    }
}
