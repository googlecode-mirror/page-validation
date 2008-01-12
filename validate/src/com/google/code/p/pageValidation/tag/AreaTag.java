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

	private Integer maxLength = null;
	private Integer minLength = null;
	private String regExp = null;
	private String custJsCheckFun = null; 
	/**
	 * @return the custJsCheckFun
	 */
	public final String getCustJsCheckFun() {
		return custJsCheckFun;
	}
	/**
	 * @param custJsCheckFun the custJsCheckFun to set
	 */
	public final void setCustJsCheckFun(String custJsCheckFun) {
		this.custJsCheckFun = custJsCheckFun;
	}
	/* (non-Javadoc)
	 * @see com.google.code.p.pageValidation.ValidateHtmFieldTag#getValdationJsString()
	 */
	public String getValdationJsString() {
		//function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun,minLength){
		StringBuffer tmpBuf = new StringBuffer("addGeneralField(");
		tmpBuf.append("\"" + getId() + "\",");
		tmpBuf.append("\"" + getTitle() + "\",");
		tmpBuf.append(getMaxLength() + ",");
		tmpBuf.append("null,");//textarea do not have this attribute
		tmpBuf.append(isCanEmpty() + ",");
		tmpBuf.append(getRegExp()+",");
		tmpBuf.append(getCustJsCheckFun()+",");
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
	 * @param regExp the regExp to set
	 */
	public final void setRegExp(String regExp) {
		this.regExp = regExp;
	}
	/**
	 * @return the maxLength
	 */
	public final Integer getMaxLength() {
		return maxLength;
	}
	/**
	 * @param maxLength the maxLength to set
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
	 * @param minLength the minLength to set
	 */
	public final void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

}
