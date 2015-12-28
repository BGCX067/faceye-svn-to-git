package com.faceye.core.util.exception;

import org.springframework.dao.DataAccessException;
public class DBException extends DataAccessException {

	public DBException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public DBException(String arg0){
		super(arg0);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
