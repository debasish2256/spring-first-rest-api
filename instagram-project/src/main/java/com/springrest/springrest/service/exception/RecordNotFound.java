package com.springrest.springrest.service.exception;

public class RecordNotFound extends Throwable {
	private static final long serialVersionUID = 1L;

	public RecordNotFound(String message){
        super(message);
    }
}
