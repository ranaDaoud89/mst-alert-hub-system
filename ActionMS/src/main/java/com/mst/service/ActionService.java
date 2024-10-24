package com.mst.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mst.exception.ActionNotFoundException;
import com.mst.exception.MetricNotFoundException;
import com.mst.model.Action;
import com.mst.model.Day;
import com.mst.repository.ActionRepository;

@Service
public class ActionService {

	@Autowired
	private ActionRepository actionRepository;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${metric.service.url}")
	private String metricServiceUrl;
	
	@Autowired
	private KafkaProducerService kafkaProducer;
		
	
	public Optional<Action> findById(UUID id )
	{
		return actionRepository.findById(id);
	}
	
    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }
	
	public Action save(Action action) throws MetricNotFoundException
	{				
		validateConditionMetrics(action.getCondition());
		return actionRepository.save(action);
	}
	
	private void validateConditionMetrics(List<List<Integer>> actionCondition) throws MetricNotFoundException {
		
		List<Integer> metricsIdsAsList = extractMeticsIds(actionCondition);
		
	    Map<Integer, Boolean> result = getMetricsByIdsFromMetricService(metricsIdsAsList);
	    List<Integer> missingMetricsIds = new ArrayList<>();
	    
        for (Map.Entry<Integer, Boolean> set :result.entrySet()) {
			if (!set.getValue()){
				Integer metricId = set.getKey();
				missingMetricsIds.add(metricId);			
			}	       
        }
	   	    
		if(missingMetricsIds.size()> 0) {
			throw new MetricNotFoundException(String.format("The metrics %s in the given condition were not found", missingMetricsIds.toString()));			
		}	
	}

	private Map<Integer, Boolean> getMetricsByIdsFromMetricService(List<Integer> metricsIdsAsList) {
		restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> request = new HttpEntity<String>(metricsIdsAsList.toString(), headers);
	    
        // Use ParameterizedTypeReference to handle generic type
        ParameterizedTypeReference<Map<Integer, Boolean>> responseType =new ParameterizedTypeReference<Map<Integer, Boolean>>() {};
		
		ResponseEntity<Map<Integer, Boolean>> result = restTemplate.exchange(metricServiceUrl+ "/check-if-metrics-exist" , HttpMethod.POST, request , responseType);
		Map<Integer, Boolean> mapResult = result.getBody();
		return mapResult;
	}

	private List<Integer> extractMeticsIds(List<List<Integer>> actionCondition) {
		Set<Integer> metricsIds = actionCondition.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());
		List<Integer> metricsIdsAsList= metricsIds.stream().collect(Collectors.toList());
		return metricsIdsAsList;
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

        LocalDateTime currentDate = LocalDateTime.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
        int currentHour = currentDate.getHour();
        
        LocalTime startTime = LocalTime.of(currentHour, 0);
        LocalTime endTime = LocalTime.of(currentHour, 29);
        if( currentDate.getMinute() > 30) {
        	startTime = LocalTime.of(currentHour, 30);
        	endTime = LocalTime.of(currentHour, 59);
        }
        
        List<Action> actionsResult = actionRepository.findActionsByRunOnDay(Day.valueOf(dayName), startTime, endTime);
        
        int numberOfFoundActions = actionsResult.size();
        System.out.println("Cron job Scheduler: Job running at - " + currentDate.toString());
        System.out.println(String.format("Found %d actions today included ALL ", numberOfFoundActions));
        
        // Filter deleted or disabled actions
        List<Action> actionsToPush = actionsResult.stream()
                .filter(action -> !action.isDeleted() && action.isEnabled())
                .collect(Collectors.toList());
        
        System.out.println(String.format("Found %d actions to push ", actionsToPush.size()));

        for(Action action: actionsToPush){
            kafkaProducer.sendMessage(action);
        }
    }
	
	 private Action getActionById(UUID id) throws ActionNotFoundException {
		  return findById(id)
		            .orElseThrow(() -> new ActionNotFoundException("Course not found with id " + id));
	}

}
