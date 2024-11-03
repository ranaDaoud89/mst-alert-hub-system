package com.mst;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.mst.model.LabelType;
import com.mst.model.Metric;
import com.mst.repository.MetricRepository;


@ActiveProfiles("test")
@DataJpaTest
public class MetricRepositoryTest {

    @Autowired
    private MetricRepository metricRepository;

    @Test
    public void testFindAll() {
    	Metric metric1 = new Metric(1,"doc metric",LabelType.DOCUMENTATION, 7, 10);
    	Metric metric2 = new Metric(2,"bug metric",LabelType.BUG, 10, 7);
    	metricRepository.save(metric1);
    	metricRepository.save(metric2);

        List<Metric> metrics = metricRepository.findAll();
        assertEquals(2, metrics.size());
    }
}