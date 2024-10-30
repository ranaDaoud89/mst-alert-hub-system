package com.mst.exception;

public class RestException extends RuntimeException {

    private static final long serialVersionUID = -3065618227725397825L;

	public RestException(String message) {
        super(message);
    }
}
