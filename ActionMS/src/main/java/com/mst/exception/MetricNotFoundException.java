package com.mst.exception;

public class MetricNotFoundException extends Exception {
	
	private static final long serialVersionUID = -8858154696210627262L;
	private String messgae;
	public MetricNotFoundException(String message) {
		super(message);
	}

}
