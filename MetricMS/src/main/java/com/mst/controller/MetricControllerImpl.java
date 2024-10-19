package com.mst.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mst.api.MetricController;
import com.mst.exception.MetricNotFoundException;
import com.mst.model.Metric;
import com.mst.service.MetricService;


@RestController
@RequestMapping("/metrics")
public class MetricControllerImpl implements MetricController
{
	@Autowired
	MetricService metricService;

	@Override
	@PostMapping("/create-new-metric")
	public ResponseEntity<Metric> createMetric(@RequestBody Metric metric) {
		try {
			Metric newAction = metricService.save(metric);
			return new ResponseEntity<>(newAction, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/get/{id}")
	public ResponseEntity<Metric> getMetricById(@PathVariable Integer id) {
		Optional<Metric> actoin = metricService.findById(id);
		if(!actoin.isEmpty())
		{
			return new ResponseEntity<>(actoin.get(), HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<Metric> updateMetric(@PathVariable Integer id, @RequestBody Metric metricDetails) {
		try {
			return new ResponseEntity<Metric>(metricService.updateMetric(id, metricDetails),HttpStatus.OK);			
		}
		catch(MetricNotFoundException ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteMetric(@PathVariable Integer id) {
		metricService.deleteMetric(id);
		return ResponseEntity.noContent().build();
	}

}
