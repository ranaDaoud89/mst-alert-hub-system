package com.mst.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mst.api.LoggerController;
import com.mst.dto.LogRequestDTO;
import com.mst.model.LogEntry;
import com.mst.service.LoggerService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggerControllerImpl implements LoggerController {

    private final LoggerService loggerService;

    @Override
    public ResponseEntity<LogEntry> createLogger(@RequestBody LogRequestDTO loggerRequest) {
        try {
            LogEntry logger = new LogEntry();
            logger.setServiceName(loggerRequest.getServiceName());
            logger.setLogLevel(loggerRequest.getLogLevel());
            logger.setMessage(loggerRequest.getMessage());

            log.info("Received request to create log entry: {}", logger);
            LogEntry savedLog = loggerService.createLog(logger); // Save the log entry using the service
            log.info("Successfully created log entry with ID: {}", savedLog.getId());
            return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating log entry: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
