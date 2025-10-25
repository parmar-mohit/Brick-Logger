package com.brick.logger;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private static String readFileAsString(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static boolean isWithinFiveMinutes(LocalDateTime reference) {
        // Calculate the duration between the two
        long minutesDifference = Duration.between(reference, LocalDateTime.now()).toMinutes();
        // Check if the difference is within 5 minutes (both past and future)
        return Math.abs(minutesDifference) <= 5;
    }

    @Test
    public void Logger_test() throws IOException, InterruptedException {
        Logger.trace("TraceLoggerMessage");
        Thread.sleep(50);
        Logger.debug("DebugLoggerMessage");
        Logger.info("InfoLoggerMessage");
        Logger.warn("WarnLoggerMessage");
        Logger.error("ErrorLoggerMessage");
        Logger.fatal("FatalLoggerMessage");


        // Trace Test
        String fileData = readFileAsString("target/trace-logfile.log");
        String[] logData = fileData.split("\r")[0].split(" ");
        LocalDateTime logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("TRACE",logData[1]);
        assertEquals("log-message:TraceLoggerMessage",logData[2]);


        //Debug Test
        fileData = readFileAsString("target/debug-logfile.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("DEBUG",logData[1]);
        assertEquals("log-message:DebugLoggerMessage",logData[2]);

        //Info Test
        fileData = readFileAsString("target/info-logfile1.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("INFO",logData[1]);
        assertEquals("log-message:InfoLoggerMessage",logData[2]);

        //Warn Test
        fileData = readFileAsString("target/warn-logfile.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("WARN",logData[1]);
        assertEquals("log-message:WarnLoggerMessage",logData[2]);

        //Error Test
        fileData = readFileAsString("target/error-logfile.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("ERROR",logData[1]);
        assertEquals("log-message:ErrorLoggerMessage",logData[2]);

        //Fatal Test
        fileData = readFileAsString("target/fatal-logfile.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("FATAL",logData[1]);
        assertEquals("log-message:FatalLoggerMessage",logData[2]);

        //All Test
        fileData = readFileAsString("target/all-logfile1.log");
        logData = fileData.split("\r")[0].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("TRACE",logData[1]);
        assertEquals("log-message:TraceLoggerMessage",logData[2]);

        //Exception Test
        Exception exception = new Exception("ExceptionMessage");
        Logger.logException(exception);
        fileData = readFileAsString("target/error-logfile.log");
        logData = fileData.split("\r\n")[1].split(" ");
        logDateTime = LocalDateTime.parse(logData[0]);
        assertTrue(isWithinFiveMinutes(logDateTime));
        assertEquals("ERROR",logData[1]);
        assertEquals("log-message:ExceptionMessage",logData[2]);
    }
}