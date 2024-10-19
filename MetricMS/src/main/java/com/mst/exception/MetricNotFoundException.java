package com.mst.exception;

public class MetricNotFoundException extends Exception {

	private static final long serialVersionUID = -6350800301350126374L;
	
	private String messgae;
	public MetricNotFoundException(String message) {
		super(message);
	}
}
