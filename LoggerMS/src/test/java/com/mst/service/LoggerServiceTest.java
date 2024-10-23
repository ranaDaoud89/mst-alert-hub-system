package com.mst.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mst.model.LogEntry;
import com.mst.repository.LogRepository;

public class LoggerServiceTest {

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private LoggerService loggerService;

    private LogEntry logEntry;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logEntry = new LogEntry();
        logEntry.setServiceName("TestService");
        logEntry.setLogLevel(LogEntry.LogLevel.INFO);
        logEntry.setMessage("Test log message");
    }

    @Test
    public void testCreateLogWithTimestamp() {
        // Arrange
        logEntry.setTimestamp(LocalDateTime.now());
        when(logRepository.save(any(LogEntry.class))).thenReturn(logEntry);

        // Act
        LogEntry result = loggerService.createLog(logEntry);

        // Assert
        verify(logRepository).save(logEntry); // Verify that save was called
        assert result != null; // Ensure the returned log entry is not null
    }

    @Test
    public void testCreateLogWithoutTimestamp() {
        // Arrange
        logEntry.setTimestamp(null);
        when(logRepository.save(any(LogEntry.class))).thenReturn(logEntry);

        // Act
        LogEntry result = loggerService.createLog(logEntry);

        // Assert
        verify(logRepository).save(any(LogEntry.class)); // Verify that save was called
        assert result != null; // Ensure the returned log entry is not null
        assert result.getTimestamp() != null; // Ensure the timestamp was set
    }
}
