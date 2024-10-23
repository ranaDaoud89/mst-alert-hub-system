package com.mst.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntry {
    @Id
    @Field("_id")
    private String id;

    @Field("timestamp")
    private LocalDateTime timestamp;

    @Field("service_name")
    private String serviceName;

    @Field("log_level")
    private LogLevel logLevel;

    @Field("message")
    private String message;

    public enum LogLevel {
        INFO, DEBUG, WARN, ERROR
    }
}

///MongoDB document model that represents a log entry with fields for timestamp, service name, log level, and message