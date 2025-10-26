package com.brick.logger.appenders;

import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleAppenderTest {
    @Test
    public void test_consolePrint(){
        // Arrange
        LogAppender consoleAppender = new ConsoleAppender();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out

        System.setOut(new PrintStream(outputStream)); // Redirect System.out

        Message logMessage = new Message();
        LocalDateTime currentDateTime = LocalDateTime.now();
        logMessage.setTimestamp(currentDateTime);
        logMessage.setLevel(LogLevel.INFO);
        logMessage.setLogMessage("Hello World");

        // Act
        consoleAppender.appendLog(logMessage);

        // Restore System.out
        System.setOut(originalOut);

        // Assert
        String output = outputStream.toString().trim();
        assertEquals(logMessage.toString(), output);
    }
}
