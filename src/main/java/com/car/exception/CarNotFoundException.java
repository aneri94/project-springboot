package com.car.exception;

public class CarNotFoundException extends RuntimeException {

	public CarNotFoundException(String exception) {
		super(exception);
	}

}