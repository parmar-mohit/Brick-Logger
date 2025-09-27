package com.brick.logger.appenders;

import com.brick.logger.utility.Message;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    Summary : Prints Log Messages to Console
 */
public class ConsoleAppender implements LogAppender {

    private Lock lock;

    public ConsoleAppender(){
        lock = new ReentrantLock();
    }

    /*
        Description: Prints Log Message to Console
                     Lock Ensure thread safety
     */
    @Override
    public void appendLog(Message message) {
        lock.lock();
        System.out.println(message.toString());
        lock.unlock();
    }
}
