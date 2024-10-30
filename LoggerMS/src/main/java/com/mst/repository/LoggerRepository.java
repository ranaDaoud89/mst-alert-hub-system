package com.mst.repository;

import com.mst.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends MongoRepository<LogEntry, String> {
}
///MongoDB repository interface for database operations, basic CRUD operations