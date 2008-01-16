/**
 * 
 */
package com.google.code.p.pageValidation.tag;

import com.google.code.p.pageValidation.basicTag.TextTag;

/**
 * @author niebo
 * 
 */
public class FloatTextTag extends TextTag {
	private int intNum = 15;
	private int decNum = 2;
	private String maxValue, minValue;

	/**
	 * @return the intNum
	 */
	public final int getIntNum() {
		return intNum;
	}

	/**
	 * @param intNum
	 *            the intNum to set
	 */
	public final void setIntNum(int intNum) {
		this.intNum = intNum;
	}

	/**
	 * @return the decNum
	 */
	public final int getDecNum() {
		return decNum;
	}

	/**
	 * @param decNum
	 *            the decNum to set
	 */
	public final void setDecNum(int decNum) {
		this.decNum = decNum;
	}

	/**
	 * @return the maxValue
	 */
	public final String getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public final void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public final String getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public final void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/*
	 * (non-Javadoc)
	 * (htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun)
	 * 
	 * @see com.google.code.p.pageValidation.ValidateHtmFieldTag#getValdationJsString(java.lang.String)
	 */
	public String getValdationJsString() {
		StringBuffer tmpBuf = new StringBuffer("addFloatField(");
		tmpBuf.append("\"" + getId() + "\",");
		tmpBuf.append("\"" + getHintName() + "\",");
		tmpBuf.append(getMaxlength() + ",");
		tmpBuf.append(getSize() + ",");
		tmpBuf.append(isCanEmpty() + ",");
		tmpBuf.append(getIntNum() + ",");
		tmpBuf.append(getDecNum() + ",");
		tmpBuf.append(getMaxValue() + ",");
		tmpBuf.append(getMinValue() + ",");
		tmpBuf.append(getCustJsCheckFun());
		tmpBuf.append(");");
		return tmpBuf.toString();
	}

}
