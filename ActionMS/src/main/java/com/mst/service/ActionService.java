package com.mst.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mst.exception.ActionNotFoundException;
import com.mst.model.Action;
import com.mst.model.Day;
import com.mst.repository.ActionRepository;

@Service
public class ActionService {

	@Autowired
	ActionRepository actionRepository ;
	
	@Autowired
	KafkaProducerService kafkaProducer;
		
	
	public Optional<Action> findById(UUID id )
	{
		return actionRepository.findById(id);
	}
	
	public Action save (Action action)
	{
		return actionRepository.save(action);
	}
	
	public void deleteAction(UUID id)
	{
		actionRepository.deleteById(id);
	}
	
	public void markActionAsDeleted(Action action)
	{
		action.setDeleted(true);
		actionRepository.save(action);
	}
	
	public void disableAction(Action action)
	{
		action.setEnabled(false);
		actionRepository.save(action);
	}

	 private Action getActionById(UUID id) throws ActionNotFoundException {
		  return findById(id)
		            .orElseThrow(() -> new ActionNotFoundException("Course not found with id " + id));
	}
	
	public Action updateAction(UUID id, Action actionDetails) throws ActionNotFoundException
	{
		Action actionToUpdate = getActionById(id);
		actionToUpdate.setName(actionDetails.getName() != null ? actionDetails.getName() : actionToUpdate.getName());
		actionToUpdate.setOwnerID(actionDetails.getOwnerID() != null ? actionDetails.getOwnerID() : actionToUpdate.getOwnerID());
		actionToUpdate.setMessage(actionDetails.getMessage() != null ? actionDetails.getOwnerID() : actionToUpdate.getOwnerID());
		actionToUpdate.setRunOnDay(actionDetails.getRunOnDay() != null ? actionDetails.getRunOnDay() : actionToUpdate.getRunOnDay());
		actionToUpdate.setRunOnTime(actionDetails.getRunOnTime() != null ? actionDetails.getRunOnTime() : actionToUpdate.getRunOnTime());
		actionToUpdate.setCondition(actionDetails.getCondition() != null ? actionDetails.getCondition() : actionToUpdate.getCondition());
        return actionRepository.save(actionToUpdate);
	}
	
	public void deleteAll()
	{
		actionRepository.deleteAll();
	}

	// This cron expression will run the task at minute 0 and 30 of every hour, meaning it will execute every 30 minutes.
    //@Scheduled(cron = "0 */30 * * * *")
	@Scheduled(fixedRate = 60000) // every 3 mins for testing
    public void scheduleTask()
    {

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
        List<Action> actionsToPush = actionRepository.findActionsByRunOnDay(Day.valueOf(dayName));
        
        int numberOfActionsToSend = actionsToPush.size();
        System.out.println("Cron job Scheduler: Job running at - " + currentDate.toString());
        System.out.println(String.format("Found %d actions today included ALL ", numberOfActionsToSend));
        
        // TODO filter actions by runOnTime
        // TODO check metric conditions and then publish to Kafka all actions
        for(Action action: actionsToPush){
            kafkaProducer.sendMessage(action);
        }
    }

}
