/**
 * 
 */
package com.google.code.p.pageValidation.tag;

import com.google.code.p.pageValidation.basicTag.TextareaTag;

/**
 * @author niebo
 * 
 */
public class AreaTag extends TextareaTag {

	private String regExp = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.p.pageValidation.ValidateHtmFieldTag#getValdationJsString()
	 */
	public String getValdationJsString() {
		// function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun,minLength){
		StringBuffer tmpBuf = new StringBuffer("addGeneralField(");
		tmpBuf.append("\"" + getId() + "\",");
		tmpBuf.append("\"" + getHintName() + "\",");
		tmpBuf.append(getMaxLength() + ",");
		tmpBuf.append("null,");// textarea do not have this attribute
		tmpBuf.append(isCanEmpty() + ",");
		tmpBuf.append(getRegExp() + ",");
		tmpBuf.append(getCustJsCheckFun() + ",");
		tmpBuf.append(getMinLength());
		tmpBuf.append(");");
		return tmpBuf.toString();
	}

	/**
	 * @return the regExp
	 */
	public final String getRegExp() {
		return regExp;
	}

	/**
	 * @param regExp
	 *            the regExp to set
	 */
	public final void setRegExp(String regExp) {
		this.regExp = regExp;
	}
}
