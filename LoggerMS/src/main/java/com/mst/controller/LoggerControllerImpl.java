package com.mst.controller;

import com.mst.api.LoggerController;
import com.mst.dto.LogRequestDTO;
import com.mst.model.LogEntry;
import com.mst.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;


@Slf4j
@RestController
public class LoggerControllerImpl implements LoggerController {

    @Autowired
    private LoggerService loggerService;

    @Override
    public ResponseEntity<String> createLog(@RequestBody final LogRequestDTO loggerRequest) {
        try {
            log.info("Received log request for service: {}", loggerRequest.getServiceName());

            LogEntry logEntry = new LogEntry();
            logEntry.setServiceName(loggerRequest.getServiceName());
            logEntry.setLogLevel(loggerRequest.getLogLevel());
            logEntry.setMessage(loggerRequest.getMessage());
            logEntry.setTimestamp(LocalDateTime.now());  // Set current timestamp

            LogEntry savedLog = loggerService.saveLog(logEntry);
            log.info("Successfully saved log with ID: {}", savedLog.getId());

            return ResponseEntity.ok("Log created successfully");
        } catch (Exception e) {
            log.error("Error creating log: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error creating log");
        }
    }
}
