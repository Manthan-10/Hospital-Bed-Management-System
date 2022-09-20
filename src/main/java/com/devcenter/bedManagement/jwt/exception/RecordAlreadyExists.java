package com.devcenter.bedManagement.jwt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Exception if the record already exists
 * 
 * @author MN097789
 *
 */

public class RecordAlreadyExists extends RuntimeException {
	
	private static final Logger logger = LoggerFactory.getLogger(RecordAlreadyExists.class.getSimpleName());
	/**
	 * Constructor
	 * @param String message Exception message
	 *
	 */
	public RecordAlreadyExists(String message) {
		super(message);
		logger.warn("Record already exists");
	}
	
}
