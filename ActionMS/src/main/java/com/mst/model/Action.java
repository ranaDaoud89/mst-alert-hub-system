package com.mst.model;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Action {


    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
	private UUID id;

    @Column(name="name")
	private String name;
	
	@Column(name="message")
	private String message;
	
	private String destination;
	
	@Column(name="create_date", nullable = false)
	private LocalDate createDate;
	
	@Column(name="owner_id")
	private String ownerID;
	
	@Column(name="action_type")
	@Enumerated(EnumType.STRING)
	private ActionType type;
	
	@Column(name="run_on_time")
	private LocalTime runOnTime;
	
	@Column(name="run_on_day", nullable = false)
    @Enumerated(EnumType.STRING)
	private Day runOnDay;
	
	@Column(name="is_enabled")
	private boolean isEnabled;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="last_update", nullable = false)
	private LocalDateTime lastUpdate;
	
	@Column(name="last_run")
	private Timestamp lastRun;
	
	@JsonIgnore
	@Column(columnDefinition = "JSON")
	private String arrayData;  // JSON column to store 2D array
	 
	@Transient
    private List<List<Integer>> condition;  // 2D array

    
    @PrePersist
    public void onCreate() throws JsonProcessingException {
    	// Convert 2D array to JSON before saving
        ObjectMapper objectMapper = new ObjectMapper();
        this.arrayData = objectMapper.writeValueAsString(condition);
        this.createDate= LocalDate.now();
        this.lastUpdate = LocalDateTime.now();  
    }

    @PreUpdate
    public void onUpdate() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.arrayData = objectMapper.writeValueAsString(condition);
        this.lastUpdate = LocalDateTime.now();  
    }
    
    // Convert JSON back to 2D array after loading
    @PostLoad
    public void deserializeArray() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.condition = objectMapper.readValue(arrayData, List.class);
    }

    
}
