/**
 * 
 */
package com.google.code.p.pageValidation;

import java.io.IOException;

import javax.servlet.jsp.tagext.Tag;

/**
 * @author niebo
 * 
 */
public abstract class ValidateHtmFieldTag implements Tag {
	/**
	 * write javascript for this Tag
	 * 
	 * @param jsValidateVarName
	 *            the javascript class VALIDATION's instance name
	 * @throws IOException
	 */
	public abstract String getValdationJsString();

	private boolean canEmpty = false;
	private String custJsCheckFun = null;
	private Integer maxLength = null;
	private Integer minLength = null;

	/**
	 * @return the canEmpty
	 */
	public final boolean isCanEmpty() {
		return canEmpty;
	}

	/**
	 * @param canEmpty
	 *            the canEmpty to set
	 */
	public final void setCanEmpty(boolean canEmpty) {
		this.canEmpty = canEmpty;
	}

	/**
	 * @return the custJsCheckFun
	 */
	public final String getCustJsCheckFun() {
		return custJsCheckFun;
	}

	/**
	 * @param custJsCheckFun
	 *            the custJsCheckFun to set
	 */
	public final void setCustJsCheckFun(String custJsCheckFun) {
		this.custJsCheckFun = custJsCheckFun;
	}

	/**
	 * @return the maxLength
	 */
	public final Integer getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	public final void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the minLength
	 */
	public final Integer getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public final void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
}
