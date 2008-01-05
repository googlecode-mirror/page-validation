/*
 * 创建日期 2007-12-14
 *
 * 用于描述validate产生的异常
 */
package com.google.code.p.pageValidation.validate;


/**
 * @author kfzx-niebo
 *用于描述validate产生的异常
 */
public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8220695458773277968L;

	/**
	 * 
	 */
	public ValidateException() {
		super();
	}

	/**
	 * @param message
	 */
	public ValidateException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ValidateException(Throwable cause) {
		super(cause);
	}

}
