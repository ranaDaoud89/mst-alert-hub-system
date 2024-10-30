package com.mst.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Value("${kafka.topic.name}")
	private String topicName;

	@Bean
	public NewTopic notificationQueueTopic()
	{
		return TopicBuilder.name(topicName) .partitions(3)       // Define number of partitions
	            .replicas(1)         // Define replication factor for redundancy
	            .build();
	}
	
	
}