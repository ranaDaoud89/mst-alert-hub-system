package com.mst.dto;

import com.mst.model.LogEntry;

import lombok.Data;

@Data
public class LogRequestDTO {
    private String serviceName;
    private LogEntry.LogLevel logLevel;  // Log level (INFO, DEBUG, etc.)
    private String message;
}

///Data Transfer Object for receiving log requests