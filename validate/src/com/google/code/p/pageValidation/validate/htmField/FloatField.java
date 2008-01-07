/**
 * 
 */
package com.google.code.p.pageValidation.validate.htmField;

import com.google.code.p.pageValidation.validate.ValidateException;



/**
 * @author niebo
 * 定义Float场类实现HtmField
 */
public class FloatField extends ValHtmField {
	/**
	 * @param htmFieldID Float Field's id attribute
	 * @param htmTitle  提示信息的标体
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param intNum 该场的整数部分最大长度
	 * @param decNum 该场的小数部分最大长度
	 * @param maxValStr 该场的最大值可输入null表示无最大值
	 * @param minValStr 该场的最小值可输入null表示最小值
	 * @param custCheckFun 需要调用的特殊js校验方法 在基本校验结束时调用 可以输入“null”表示无特殊校验
	 */
	public FloatField(String htmFieldID, String htmTitle, int htmMaxLen, int htmFieldSize, boolean canEmpty,
			int intNum, int decNum, String maxValStr, String minValStr, String custCheckFun) {
		super(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty, custCheckFun);
		this.setIntNum(intNum);
		this.setDecNum(decNum);
		this.setMaxValStr(maxValStr);
		this.setMinValStr(minValStr);
	}

	private int intNum;
	private int decNum;
	private String maxValStr;
	private String minValStr;

	/**
	 * @return the intNum
	 */
	public final int getIntNum() {
		return intNum;
	}

	/**
	 * @param intNum the intNum to set
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
	 * @param decNum the decNum to set
	 */
	public final void setDecNum(int decNum) {
		this.decNum = decNum;
	}

	/**
	 * @return the maxValStr
	 */
	public final String getMaxValStr() {
		return maxValStr;
	}

	/**
	 * @param maxValStr the maxValStr to set
	 */
	public final void setMaxValStr(String maxValStr) {
		this.maxValStr = maxValStr;
	}

	/**
	 * @return the minValStr
	 */
	public final String getMinValStr() {
		return minValStr;
	}

	/**
	 * @param minValStr the minValStr to set
	 */
	public final void setMinValStr(String minValStr) {
		this.minValStr = minValStr;
	}

	public String getValdationJsString(String jsValidateVarName) throws ValidateException {
		StringBuffer outStr = new StringBuffer(jsValidateVarName + ".addFloatField(");
		outStr.append(getHtmFieldID() + ",");
		outStr.append(getHtmTitle() + ",");
		outStr.append(getHtmMaxLen() + ",");
		outStr.append(getHtmFieldSize() + ",");
		outStr.append(getCanEmpty() + ",");
		outStr.append(getIntNum() + ",");
		outStr.append(getDecNum() + ",");
		outStr.append(getMaxValStr() + ",");
		outStr.append(getMinValStr() + ",");
		outStr.append(getCustCheckFun() + ");");
		return outStr.toString();
	}
}
