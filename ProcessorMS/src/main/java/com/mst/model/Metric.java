package com.mst.model;

import lombok.Data;

@Data
public class Metric {

	private Integer id;

	private String name;
    
	private LabelType label;
	
	private int threshold;

	private int timeFrameHours;
}

