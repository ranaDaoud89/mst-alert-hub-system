package com.mst.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mst.model.Notification;
import com.mst.service.KafkaProducerService;

@RestController
@RequestMapping("/evaluation/developer")
public class EvaluationController {

	@Value("${destination.email}")
	private String destinationEmail;
	
	@Autowired
	private LoaderClient loaderClient;
	
	@Autowired
	private KafkaProducerService kafkaProducer;

	@GetMapping("/most-label")
	public ResponseEntity<String> developerMostOccurrence(@RequestParam String label, @RequestParam String since) {
		String developerID = loaderClient.developerMostOccurrence(label, since);
		Notification notification = Notification.builder()
				.message("developer id: " + developerID)
				.destination(destinationEmail)
				.build();
		
		kafkaProducer.sendMessage(notification);
		return ResponseEntity.ok("developer id: " + developerID);
	}

	@GetMapping("/{developer_id}/label-aggregate")
	public ResponseEntity<String> aggregationOfLabel(@PathVariable String developer_id, @RequestParam String since) {
		HashMap<String, Integer> labelAggregationList = loaderClient.aggregationOfLabel(developer_id, since);
		Notification notification = Notification.builder()
				.message("return list: " + labelAggregationList)
				.destination(destinationEmail)
				.build();
		
		kafkaProducer.sendMessage(notification);
		return ResponseEntity.ok("return list: " + labelAggregationList);
	}

	@GetMapping("/{developer_id}/task-amount")
	public ResponseEntity<String> totalTasks(@PathVariable String developer_id, @RequestParam String since) {
		int totalTasks = loaderClient.totalTasks(developer_id, since);
		
		Notification notification = Notification.builder()
				.message("total tasks: " + totalTasks)
				.destination(destinationEmail)
				.build();
		
		kafkaProducer.sendMessage(notification);
		return ResponseEntity.ok("total tasks: " + totalTasks);
	}

}
