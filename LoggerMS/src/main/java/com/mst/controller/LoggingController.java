package com.mst.controller;

import com.mst.model.LogEntry;
import com.mst.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {
    private final LoggingService loggingService;

    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLog(@RequestBody LogEntry logEntry) {
        LogEntry savedLog = loggingService.saveLog(logEntry);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }
}