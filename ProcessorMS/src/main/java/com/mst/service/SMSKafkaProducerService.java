package com.mst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mst.model.Notification;


@Service
public class SMSKafkaProducerService implements INotificationProducerService {

	@Value("${sms.kafka.topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, Notification> kafkaTemplate ; 
	
	@Override
	public void sendNotification(Notification notification)
	{
		kafkaTemplate.send(topicName , notification);
	}
}
