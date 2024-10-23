package com.mst.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.mst.model.LogEntry;

@Repository
public interface LogRepository extends MongoRepository<LogEntry, String> {
}

///MongoDB repository interface for database operations, basic CRUD operations