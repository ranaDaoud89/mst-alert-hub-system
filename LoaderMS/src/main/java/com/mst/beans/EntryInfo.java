package com.mst.beans;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "platformInformation")
@Data
@NoArgsConstructor
public class EntryInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long task_id; 
	private LocalDateTime timestamp = LocalDateTime.now();
	private String owner_id;//long
	private String project;
	private String Tag;
	private String label;
	private String developer_id;//long
	private String task_number;
	private String environment;
	private String user_story;
	private String task_point;//long
	private String sprint;
}