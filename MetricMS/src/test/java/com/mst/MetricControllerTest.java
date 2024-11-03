package com.mst;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mst.controller.MetricControllerImpl;
import com.mst.model.LabelType;
import com.mst.model.Metric;
import com.mst.service.MetricService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MetricControllerImpl.class)
public class MetricControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricService metricService;

    @InjectMocks
    private MetricControllerImpl courseController;

    @Test
    public void testGetCourseById() throws Exception {
        Metric metric = new Metric(1,"doc metric",LabelType.DOCUMENTATION, 7, 10);
        when(metricService.findById(1)).thenReturn(Optional.of(metric));

        mockMvc.perform(get("/metrics/get/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("doc metric"));
    }
     
}