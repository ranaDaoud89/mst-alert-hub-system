package com.mst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mst.model.Action;


@Service
public class KafkaProducerService {

	@Value("${kafka.topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, Action> kafkaTemplate ; 
	
	
	public void sendMessage(Action action)
	{
		kafkaTemplate.send(topicName , action);
	}
}
