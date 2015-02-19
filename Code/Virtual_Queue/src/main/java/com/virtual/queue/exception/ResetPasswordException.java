package com.virtual.queue.exception;

public class ResetPasswordException extends Exception {

	private static final long serialVersionUID = -7001595884870644236L;

	public ResetPasswordException() {

		super();

	}

	private String message = null;

	public ResetPasswordException(String message) {
		super(message);
		this.message = message;
	}

	public ResetPasswordException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
