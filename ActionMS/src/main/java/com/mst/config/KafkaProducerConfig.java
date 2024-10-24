package com.mst.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mst.model.Action;

@Configuration
public class KafkaProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers ;
	
	public Map<String , Object> producerConfig()
	{
		Map<String ,Object> props= new HashMap<String, Object>();
		props.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);
		return props;
	}
	
	public ProducerFactory<String, Action> producerFactory()
	{
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean  //way how to send message 
	public KafkaTemplate<String, Action> kafkaTemplate(ProducerFactory<String, Action> producerFactory )
	{
		return new KafkaTemplate<>(producerFactory);
	}
	
}
