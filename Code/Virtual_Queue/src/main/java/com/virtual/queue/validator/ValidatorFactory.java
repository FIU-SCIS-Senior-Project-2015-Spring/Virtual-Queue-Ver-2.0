package com.virtual.queue.validator;

public class ValidatorFactory {

	public ValidatorFactory() {
	};

	public Validator getRideValidator() {

		Validator rideValidator = new RideValidator();

		return rideValidator;

	}

}
