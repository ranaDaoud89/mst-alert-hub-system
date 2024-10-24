package com.mst.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mst.model.Metric;


@FeignClient(name = "metric-service", url = "${metric.service.url}")
public interface MetricClient {

	@GetMapping("/get-metrics-details")
	public List<Metric> getMetricsDetailsByIds(@RequestBody List<Integer> metricsIds);
	
	@GetMapping("/get-all")
	public List<Metric> getAllMetrics();
}
