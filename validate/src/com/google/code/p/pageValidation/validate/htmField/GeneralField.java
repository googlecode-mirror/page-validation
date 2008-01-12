package com.google.code.p.pageValidation.validate.htmField;

import com.google.code.p.pageValidation.validate.ValidateException;

public class GeneralField extends ValHtmField {
	private String regExp;

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
	 * @param htmFieldID
	 * @param htmTitle
	 * @param htmMaxLen
	 * @param htmFieldSize
	 * @param canEmpty
	 * @param regExp
	 * @param custCheckFun
	 */
	public GeneralField(String htmFieldID,String htmTitle,int htmMaxLen,int htmFieldSize,boolean canEmpty,String regExp,String custCheckFun){
		super(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty, custCheckFun);
		this.setRegExp(regExp);
	}
	
	public String getValdationJsString(String jsValidateVarName) throws ValidateException {
		StringBuffer outStr = new StringBuffer(jsValidateVarName + ".addGeneralField(");
		outStr.append(getHtmFieldID() + ",");
		outStr.append(getHtmTitle() + ",");
		outStr.append(getHtmMaxLen() + ",");
		outStr.append(getHtmFieldSize() + ",");
		outStr.append(getCanEmpty() + ",");
		outStr.append(getRegExp() + ",");
		outStr.append(getCustCheckFun() + ");");
		return outStr.toString();
	}

}