package com.mst.exception;

public class ActionNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String messgae;
	public ActionNotFoundException(String message) {
		super(message);
	}

}
