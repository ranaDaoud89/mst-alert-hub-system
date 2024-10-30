package com.mst.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {

	String message;
	String destination;
}
