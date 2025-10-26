package com.brick.logger.appenders;

import com.brick.logger.utility.LogLevel;
import com.brick.logger.utility.Message;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAppenderTest {

    private static String readFileAsString(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Test
    public void test_fileOutput() throws IOException, InterruptedException {
        String filePath = "target/file-appender.log";
        LogAppender logAppender = new FileAppender(filePath,true);

        Message logMessage = new Message();
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLevel(LogLevel.INFO);
        logMessage.setLogMessage("Hello World");

        logAppender.appendLog(logMessage);

        Thread.sleep(100);
        assertEquals(logMessage.toString(), readFileAsString(filePath).split("\r")[0]);
    }
}
