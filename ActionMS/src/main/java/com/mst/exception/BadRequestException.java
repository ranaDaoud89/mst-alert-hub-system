package com.mst.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -3065618227725397825L;

	public BadRequestException(String message) {
        super(message);
    }
}
