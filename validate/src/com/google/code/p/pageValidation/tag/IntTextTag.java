/**
 * 
 */
package com.google.code.p.pageValidation.tag;

import com.google.code.p.pageValidation.basicTag.TextTag;


/**
 * @author nie
 *
 */
public class IntTextTag extends TextTag {
	String maxValue = null;
	String minValue = null;
	/**
	 * @return the maxValue
	 */
	public final String getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue the maxValue to set
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
	 * @param minValue the minValue to set
	 */
	public final void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getValdationJsString() {
		//VALIDATION.prototype.addIntField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,maxValStr,minValStr,custCheckFun){
		StringBuffer stringBuffer = new StringBuffer("addIntField(");
		stringBuffer.append("\""+ getId()+"\",");
		stringBuffer.append("\""+ getHintName()+"\",");
		stringBuffer.append(getMaxlength()+",");
		stringBuffer.append(getSize()+",");
		stringBuffer.append(isCanEmpty()+",");
		stringBuffer.append(getMaxValue()+",");
		stringBuffer.append(getMinValue()+","+this.getCustJsCheckFun()+");\n");
		return stringBuffer.toString();
	}
}
