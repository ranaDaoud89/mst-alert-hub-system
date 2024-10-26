package com.mst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mst.controller.LoaderClient;
import com.mst.controller.MetricClient;
import com.mst.model.Action;
import com.mst.model.ActionType;
import com.mst.model.Metric;
import com.mst.model.Notification;

import jakarta.annotation.PostConstruct;

@Service
public class ProcessorService {

	@Autowired
	SMSKafkaProducerService smsProducer;

	@Autowired
	EmailKafkaProducerService emailProducer;

	@Autowired
	private MetricClient metricClient;

	@Autowired
	private LoaderClient loaderClient;

	private Map<ActionType, INotificationProducerService> producerPerType;

	@PostConstruct
	public void initializeService() {
		producerPerType = new HashMap<ActionType, INotificationProducerService>();

		producerPerType.put(ActionType.EMAIL, emailProducer);
		producerPerType.put(ActionType.SMS, smsProducer);

	}

	public List<Metric> getAllMetrics() {
		List<Metric> foundMetrics = metricClient.getAllMetrics();

		System.out.println("Number of Found metrics:" + foundMetrics.size());
		return foundMetrics;
	}

	@KafkaListener(topics = "${action.queue.kafka.topic.name}", groupId = "groupId-0")
	public void listen(Action action) {
		System.out.println("Action recieved :" + action);
		List<Integer> metricsIdsInCondition = action.getCondition().stream().flatMap(List::stream)
				.collect(Collectors.toList());

		System.out.println("Metrics to check for action: " + metricsIdsInCondition);

		List<Metric> metricsTochek = metricClient.getMetricsDetailsByIds(metricsIdsInCondition);
		Map<Integer, Boolean> metricsMeetTheConditionMap = loaderClient.checkIfMetricsMeetTheCondition(metricsTochek);

		List<List<Integer>> conditionsList = action.getCondition();

		boolean shouldPublishNotifocation = false;
		for (List<Integer> condition : conditionsList) {
			boolean conditionFlag = true;
			for (Integer metricId : condition) {
				Boolean doesMetricMeetTheCondition = metricsMeetTheConditionMap.get(metricId);
				if (!doesMetricMeetTheCondition) {
					conditionFlag = false;
					break;
				}
			}

			if (conditionFlag) {
				shouldPublishNotifocation = true;
				break;
			}
		}

		if (shouldPublishNotifocation) {
			System.out.println("Publishing notification for action [" + action.getId() + "]");
			ActionType notificationType = action.getType();

			Notification notificationToSend = Notification.builder().message(action.getMessage())
					.destination(action.getDestination()).build();
			producerPerType.get(notificationType).sendNotification(notificationToSend);

		}
	}
}
