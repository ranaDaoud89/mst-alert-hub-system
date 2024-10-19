package com.mst.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mst.model.Action;
import com.mst.model.Day;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {
	
    @Query("SELECT a FROM Action a WHERE a.runOnDay = :day or a.runOnDay = 'ALL'")
    List<Action> findActionsByRunOnDay(@Param("day") Day currentDay);
    
    

}
