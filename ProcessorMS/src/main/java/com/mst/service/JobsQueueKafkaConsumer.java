package com.mst.service;

import org.springframework.stereotype.Service;

import com.mst.model.Action;

import org.springframework.kafka.annotation.KafkaListener;

@Service
public class JobsQueueKafkaConsumer {
	
	@KafkaListener(topics = "${kafka.topic.name}" , groupId = "groupId")
	public void listen(Action action)
	{
		System.out.println("Action recieved :"+ action);
	}

}
