package com.devcenter.bedManagement.jwt.exception;


/**
 * Custom exception in case of a server failure
 * @author MN097789
 *
 */

public class ServerFailure extends RuntimeException {
	/**
	 * ServerFailure constructor for creating a ServerFailure exception
	 * @param message Represents the message that explains the cause of exception
	 */
	public ServerFailure(String message) {
		super(message);
	}

}
