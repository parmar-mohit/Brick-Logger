package com.brick.logger.appenders;

import com.brick.logger.utility.Message;

public interface LogAppender {
    void appendLog(Message message);
}
