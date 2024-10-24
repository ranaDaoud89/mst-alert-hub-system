package com.mst.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mst.api.ActionController;
import com.mst.exception.ActionNotFoundException;
import com.mst.exception.BadRequestException;
import com.mst.exception.MetricNotFoundException;
import com.mst.model.Action;
import com.mst.service.ActionService;


@RestController
@RequestMapping("/actions")
public class ActionControllerImpl implements ActionController {

	@Autowired
	ActionService actionService;
	
	@Override
	@PostMapping("/create-new-action")
	public ResponseEntity<Action> createAction(@RequestBody Action action) {
		try {
			Action newAction = actionService.save(action);
			return new ResponseEntity<>(newAction, HttpStatus.CREATED);
		
		}catch (MetricNotFoundException e) {
			System.out.print(e.getMessage());
			throw new BadRequestException(e.getMessage());
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/get/{id}")
	public ResponseEntity<Action> getActionById(UUID id) {
		Optional<Action> actoin = actionService.findById(id);
		if(!actoin.isEmpty())
		{
			return new ResponseEntity<>(actoin.get(), HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	@GetMapping("/get-all")
	public ResponseEntity<List<Action>> getAllActions() {
		List<Action> actoins = actionService.getAllActions();

		return new ResponseEntity<>(actoins, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteAction(UUID id) {
		actionService.deleteAction(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	@Override
	@PutMapping("/mark-as-deleted/{id}")
	public ResponseEntity<HttpStatus> markActionAsDeleted(@PathVariable UUID id) {
		 Optional<Action> retrievedAction = actionService.findById(id);
		 if(retrievedAction.isPresent())
		 {
			 actionService.markActionAsDeleted(retrievedAction.get());
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		 }else
		 {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	}
	
	@Override
	@PutMapping("/disable/{id}")
	public ResponseEntity<HttpStatus> disableAction(@PathVariable UUID id) {
		 Optional<Action> retrievedAction = actionService.findById(id);
		 if(retrievedAction.isPresent())
		 {
			 actionService.disableAction(retrievedAction.get());
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		 }else
		 {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	}

	
	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<Action> updateAction(UUID id, Action actionDetails) {

		try {
			return new ResponseEntity<Action>(actionService.updateAction(id, actionDetails),HttpStatus.OK);			
		}
		catch(ActionNotFoundException ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
