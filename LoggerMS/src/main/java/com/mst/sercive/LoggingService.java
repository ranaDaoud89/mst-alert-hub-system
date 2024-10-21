package com.mst.service;

import com.mst.model.LogEntry;
import com.mst.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoggingService {
    private final LogEntryRepository logEntryRepository;

    @Autowired
    public LoggingService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    public LogEntry saveLog(LogEntry logEntry) {
        logEntry.setTimestamp(Instant.now());
        return logEntryRepository.save(logEntry);
    }
}