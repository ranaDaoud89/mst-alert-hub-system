package com.mst.service;

import com.mst.model.LogEntry;
import com.mst.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LoggerService {

    @Autowired
    private LoggerRepository loggerRepository;

    public LogEntry saveLog(LogEntry logger) {
        logger.setTimestamp(LocalDateTime.now());
        return loggerRepository.save(logger);
    }
}
