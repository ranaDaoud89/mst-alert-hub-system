package com.mst.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mst.model.Action;

public interface ActionController {

	ResponseEntity<Action> createAction(@RequestBody Action action);
	
	ResponseEntity<Action> getActionById(@PathVariable UUID id);
	
	ResponseEntity<Action> updateAction(@PathVariable UUID id, @RequestBody Action action);
	
	ResponseEntity<HttpStatus> deleteAction(@PathVariable UUID id);
	
	ResponseEntity<HttpStatus> markActionAsDeleted(UUID id);
	
	ResponseEntity<HttpStatus> disableAction(UUID id);

	ResponseEntity<List<Action>> getAllActions();

}
