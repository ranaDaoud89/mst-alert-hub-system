package com.mst.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mst.api.ProcessorController;
import com.mst.model.Metric;
import com.mst.service.ProcessorService;


@RestController
@RequestMapping("/processor")
public class ProcessorControllerImpl implements ProcessorController
{
	@Autowired
	ProcessorService processorService;
	
	@Autowired
	private MetricClient metricClient;

	@Override
	@GetMapping("/get-all-metrics")
	public ResponseEntity<String> getAllMetrics() {
		List<Metric> foundMetrics = metricClient.getAllMetrics();
		System.out.print("Number of Found metrics:" + foundMetrics.size());
		return null;
	}
	
	

}
