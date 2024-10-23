package com.mst.dto;

import com.mst.model.LogEntry.LogLevel;
import lombok.Data;

@Data
public class LogRequestDTO {
    private String serviceName;
    private LogLevel logLevel;
    private String message;
}

///Data Transfer Object for receiving log requests