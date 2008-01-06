/**
 * 
 */
package com.google.code.p.pageValidation.validate.htmField;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;



/**
 * @author niebo
 * 定义Float场类实现HtmField
 */
public class FloatField extends ValHtmField {
	/**
	 * @param htmFieldID Float场的ID属性
	 * @param htmTitle  提示信息的标题
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param intNum 该场的整数部分最大长度
	 * @param decNum 该场的小数部分最大长度
	 * @param maxValStr 该场的最大值 可输入null表示无上限
	 * @param minValStr 该场的最小值 可输入null表示无下限
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
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
	/*
	 * （非 Javadoc）
	 * 
	 * @see validate.Validate.htmField#writeJs()
	 */
	public void writeJs(JspWriter out) throws IOException {
		StringBuffer outStr = new StringBuffer("VALIDATION.addFloatField(");
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
		out.println(outStr.toString());
	}

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
}

