package com.mst.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import com.mst.model.LogEntry;
import com.mst.repository.LogRepository;

@Service
@RequiredArgsConstructor
public class LoggerService {

    private final LogRepository logRepository;

    public LogEntry createLog(LogEntry logger) {
        // Set timestamp if not already set
        if (logger.getTimestamp() == null) {
            logger.setTimestamp(LocalDateTime.now());
        }

        try {
            return logRepository.save(logger); // Saves the log entry in MongoDB
        } catch (Exception e) {
            // Log error and rethrow or handle as needed
            throw new RuntimeException("Error saving log entry: " + e.getMessage(), e);
        }
    }
}
