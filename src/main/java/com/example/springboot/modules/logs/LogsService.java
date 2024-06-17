package com.example.springboot.modules.logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class LogsService {

    private static final Logger logger = LoggerFactory.getLogger(LogsService.class);

    @Autowired
    LogsRepository logsRepository;

    public void logInfo(String message) {
        LogsEntity logs = new LogsEntity();
        Date date = new Date();
        logs.setTimestamp(new Timestamp(date.getTime()));
        logs.setMessage(message);
        logsRepository.save(logs);
        logger.info(message);
    }

    public void logError(String message, Throwable throwable) {
        LogsEntity logs = new LogsEntity();
        Date date = new Date();
        logs.setTimestamp(new Timestamp(date.getTime()));
        logs.setMessage(message);
        logs.setException(throwable.getMessage());
        logsRepository.save(logs);
        logger.error(message, throwable);
    }

}
