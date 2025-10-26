package com.brick.logger.utilities;

import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    @Test
    public void test_success(){
        Message message = new Message();
        LocalDateTime currentDateTime = LocalDateTime.now();
        message.setTimestamp(currentDateTime);
        message.setLevel(LogLevel.INFO);
        message.setLogMessage("Hello World");

        assertEquals(currentDateTime,message.getTimestamp());
        assertEquals(LogLevel.INFO,message.getLevel());
        assertEquals("Hello World",message.getLogMessage());
        String logOutput = currentDateTime+" "+LogLevel.INFO+" log-message:Hello World";
        assertEquals(logOutput,message.toString());
    }
}
