package com.mst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mst.model.Metric;


@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer> {
	
	
}
