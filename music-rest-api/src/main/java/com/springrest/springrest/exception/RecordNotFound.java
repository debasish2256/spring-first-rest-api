package com.springrest.springrest.exception;

public class RecordNotFound extends Throwable {
	private static final long serialVersionUID = 1L;

	public RecordNotFound(String message){
        super(message);
    }
}
