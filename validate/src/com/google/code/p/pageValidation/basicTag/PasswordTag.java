/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;

/**
 * @author nie password Tags' parent
 */
public abstract class PasswordTag extends InputTag {

	/**
	 * password Tags' parent
	 */
	public PasswordTag() {
		super("password");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.p.pageValidation.ValidateHtmFieldTag#getValdationJsString(java.lang.String)
	 */
	public String getValdationJsString() {
		return null;
		// TODO
	}
}
