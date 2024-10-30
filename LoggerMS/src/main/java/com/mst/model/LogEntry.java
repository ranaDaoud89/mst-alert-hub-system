package com.mst.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntry {
    @Id
    private String id;  // Unique identifier for the log entry
    private String serviceName;  // Name of the service that generated the log
    private LogLevel logLevel;  // Level of the log (INFO, DEBUG, etc.)
    private String message;  // Log message
    private LocalDateTime timestamp;  // Timestamp of the log entry

    // Enum for log levels
    public enum LogLevel {
        INFO, DEBUG, WARN, ERROR
    }
}


///MongoDB document model that represents a log entry with fields for timestamp, service name, log level, and message