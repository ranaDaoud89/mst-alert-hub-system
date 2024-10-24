package com.mst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mst.exception.MetricNotFoundException;
import com.mst.model.Metric;
import com.mst.repository.MetricRepository;

@Service
public class MetricService {

	@Autowired
	MetricRepository metricRepository ;		
	
	public Optional<Metric> findById(Integer id )
	{
		return metricRepository.findById(id);
	}
	
    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public List<Metric> getMetricsByIds(List<Integer> metricsIds) {
        return metricRepository.findAllById(metricsIds);
    }
    
    public Map<Integer, Boolean> checkIfMetricsIdsExist(List<Integer> metricsIds) {
        List<Metric> retrievedMetrics = metricRepository.findAllById(metricsIds);
        List<Integer> foundedMetrics = retrievedMetrics.stream().map(metric-> metric.getId()).collect(Collectors.toList());
        
        Map<Integer, Boolean> result = new HashMap();
        for(Integer metricId : metricsIds) {
        	if(foundedMetrics.contains(metricId)){
        		result.put(metricId, true);
        	}
        	else {
        		result.put(metricId, false);
        	}
        }
        
        return result;
    }
    
	public Metric save(Metric action)
	{
		return metricRepository.save(action);
	}
	
	public void deleteMetric(Integer id)
	{
		metricRepository.deleteById(id);
	}
	
	public Metric updateMetric(Integer id, Metric metricDetails) throws MetricNotFoundException
	{
		Metric metricToUpdate = getMetricById(id);
		metricToUpdate.setName(metricDetails.getName() != null ? metricDetails.getName() : metricToUpdate.getName());
		metricToUpdate.setLabel(metricDetails.getLabel() != null ? metricDetails.getLabel() : metricToUpdate.getLabel());
		metricToUpdate.setThreshold(metricDetails.getThreshold());
		metricToUpdate.setTimeFrameHours(metricDetails.getTimeFrameHours());
		
        return metricRepository.save(metricToUpdate);
	}

	 private Metric getMetricById(Integer id) throws MetricNotFoundException {
		  return findById(id)
		            .orElseThrow(() -> new MetricNotFoundException("Course not found with id " + id));
	}
}
