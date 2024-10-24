package com.mst.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mst.model.Metric;


@FeignClient(name = "loader-service", url = "${loader.service.url}")
public interface LoaderClient {

	// Request body: [1,2,3,4,6] 
	// Response: { 1 -> true, 2-> false, 3->true}
	@PostMapping("/check-metrics-condition")
	public HashMap<Integer,Boolean> checkIfMetricsMeetTheCondition(List<Metric> metricsTocheck);	
}
