package com.mst.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${sms.kafka.topic.name}")
	private String smsTopicName;
	
	@Value("${email.kafka.topic.name}")
	private String emailTopicName;
	
	@Bean
	NewTopic smsNotificationTopic()
	{
		return TopicBuilder.name(smsTopicName)
				.partitions(3)
				.replicas(1)
				.build();
	}
	
	@Bean
	NewTopic emailNotificationTopic()
	{
		return TopicBuilder.name(emailTopicName)
				.partitions(3)
				.replicas(1)
				.build();
	}
	
	
}
