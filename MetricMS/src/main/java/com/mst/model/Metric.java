package com.mst.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Integer id;

    @Column(name="name")
	private String name;
    
	@Column(name="label")
	@Enumerated(EnumType.STRING)
	private LabelType label;
	
	private int threshold;
	
	@Column(name="time_frame_hours")
	private int timeFrameHours;
}
