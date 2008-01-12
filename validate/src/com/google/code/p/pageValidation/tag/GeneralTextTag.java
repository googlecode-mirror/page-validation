/**
 * 
 */
package com.google.code.p.pageValidation.tag;

import com.google.code.p.pageValidation.basicTag.TextTag;

/**
 * @author nie
 *
 */
public class GeneralTextTag extends TextTag {
	private String regExp,custJsCheckFun;
	private Integer minLength;
	/* (non-Javadoc)
	 * @see com.google.code.p.pageValidation.ValidateHtmFieldTag#getValdationJsString(java.lang.String)
	 */
	public String getValdationJsString() {
		//function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun,minLen){
		StringBuffer tmpBuf = new StringBuffer("addGeneralField(");
		tmpBuf.append("\"" + getId() + "\",");
		tmpBuf.append("\"" + getTitle() + "\",");
		tmpBuf.append(getMaxlength() + ",");
		tmpBuf.append(getSize() + ",");
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
