package com.mst.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mst.model.Metric;



public interface ProcessorController {

	public ResponseEntity<List<Metric>> getAllMetrics();
	
}
