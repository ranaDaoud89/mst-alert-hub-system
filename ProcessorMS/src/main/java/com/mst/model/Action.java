package com.mst.model;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Action {

	private UUID id;

	private String name;
	
	private String message;
	
	private String destination;
	
	private LocalDate createDate;
	
	private String ownerID;
	
	private ActionType type;
	
	private LocalTime runOnTime;
	
	private Day runOnDay;
	
	private boolean isEnabled;
	
	private boolean isDeleted;
	
	private LocalDateTime lastUpdate;
	
	private Timestamp lastRun;
	

	private String arrayData;  // JSON column to store 2D array
	 
    private List<List<Integer>> condition;  // 2D array
  
}
