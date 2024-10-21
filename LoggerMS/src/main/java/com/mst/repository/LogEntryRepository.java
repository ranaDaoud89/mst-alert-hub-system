package com.mst.repository;

import com.mst.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogEntryRepository extends MongoRepository<LogEntry, String> {
}