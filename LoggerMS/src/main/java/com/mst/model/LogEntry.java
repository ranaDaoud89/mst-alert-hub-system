package com.mst.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "logs")
public class LogEntry {
    @Id
    private String id;
    private Instant timestamp;
    private String serviceName;
    private String logLevel;
    private String message;
}