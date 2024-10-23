package com.mst.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.mst.dto.LogRequestDTO;
import com.mst.model.LogEntry;

@RequestMapping("/api/logs")
@Api(tags = "Logger API", description = "Operations pertaining to logs in the system")
public interface LoggerController {

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "Create a new log entry",
                 notes = "Creates a new log entry in the system with the provided details")
    ResponseEntity<LogEntry> createLogger(@RequestBody LogRequestDTO loggerRequest);
}
