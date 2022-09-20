package com.devcenter.bedManagement.jwt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Exception if the given id does not exist
 * 
 * @author MN097789
 *
 */
public class RecordDoesNotExist extends RuntimeException {
	private static final Logger logger = LoggerFactory.getLogger(RecordDoesNotExist.class.getSimpleName());
	
	/**
	 * Constructor
	 * @param String message Exception message
	 *
	 */
	public RecordDoesNotExist(String message) {
		super(message);
		logger.warn("Record Not Found");
	}

}
