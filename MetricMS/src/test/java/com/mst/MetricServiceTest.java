package com.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mst.model.LabelType;
import com.mst.model.Metric;
import com.mst.repository.MetricRepository;
import com.mst.service.MetricService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MetricServiceTest {

    @Mock
    private MetricRepository metricRepository;

    @InjectMocks
    private MetricService metricService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMetricById() {
    	Metric metric = new Metric(1,"doc metric",LabelType.DOCUMENTATION, 7, 10);
        when(metricRepository.findById(1)).thenReturn(Optional.of(metric));

        Optional<Metric> foundMetric = metricService.findById(1);
        assertEquals("doc metric", foundMetric.get().getName());
    }
    
    
    @Test
    public void testGetAllMetrics() {
    	Metric metric1 = new Metric(1,"doc metric",LabelType.DOCUMENTATION, 7, 10);
    	Metric metric2 = new Metric(1,"doc metric",LabelType.ENHANCEMENT, 9, 11);
        when(metricRepository.findAll()).thenReturn(List.of(metric1, metric2));

        List<Metric> foundMetrics = metricService.getAllMetrics();
        assertEquals(2, foundMetrics.size());
    }
    
}