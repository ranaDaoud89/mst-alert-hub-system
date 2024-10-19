package com.mst.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.mst.model.Metric;

public interface MetricController {

	ResponseEntity<Metric> createMetric(@RequestBody Metric metric);
	
	ResponseEntity<Metric> getMetricById(@PathVariable Integer id);
	
	ResponseEntity<Metric> updateMetric(@PathVariable Integer id, @RequestBody Metric metric);
	
	ResponseEntity<HttpStatus> deleteMetric(@PathVariable Integer id);

}
