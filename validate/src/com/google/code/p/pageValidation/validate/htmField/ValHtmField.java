/**
 * 定义html场校验接�?
 */
package com.google.code.p.pageValidation.validate.htmField;

import java.io.IOException;

import com.google.code.p.pageValidation.validate.ValidateException;


/**
 * @author niebo 定义html场校验接�?
 */
public abstract class ValHtmField {
	private String htmFieldID;
	private String htmTitle;
	private int htmMaxLen;
	private int htmFieldSize;
	private boolean canEmpty;
	private String custCheckFun;

	/**
	 * @param htmFieldID html input场的ID属�?
	 * @param htmTitle  提示信息的标�?
	 * @param htmMaxLen 该场的maxlength属�?
	 * @param htmFieldSize 该场得size属�?
	 * @param canEmpty  该场是否允许提交空�?true表示可以提交空�?
	 * @param custCheckFun 需要调用的特殊js校验方法�?在基本校验结束时调用 可以输入“null”表示无特殊教研
	 */
	public ValHtmField(String htmFieldID, String htmTitle, int htmMaxLen, int htmFieldSize, boolean canEmpty,
			 String custCheckFun) {
		this.setCanEmpty(canEmpty);
		this.setCustCheckFun(custCheckFun);
		this.setHtmFieldID("\""+htmFieldID+"\"");
		this.setHtmFieldSize(htmFieldSize);
		this.setHtmMaxLen(htmMaxLen);
		this.setHtmTitle("\""+htmTitle+"\"");
	}

	/**
	 * @param out jsp's out
	 * @throws IOException
	 * @throws ValidateException
	 */
	abstract public String getValdationJsString(String jsValidateVarName) throws ValidateException;

	/**
	 * @return the htmFieldID
	 */
	public final String getHtmFieldID() {
		return htmFieldID;
	}

	/**
	 * @param htmFieldID
	 *            the htmFieldID to set
	 */
	public final void setHtmFieldID(String htmFieldID) {
		this.htmFieldID = htmFieldID;
	}

	/**
	 * @return the htmTitle
	 */
	public final String getHtmTitle() {
		return htmTitle;
	}

	/**
	 * @param htmTitle
	 *            the htmTitle to set
	 */
	public final void setHtmTitle(String htmTitle) {
		this.htmTitle = htmTitle;
	}

	/**
	 * @return the htmMaxLen
	 */
	public final int getHtmMaxLen() {
		return htmMaxLen;
	}

	/**
	 * @param htmMaxLen
	 *            the htmMaxLen to set
	 */
	public final void setHtmMaxLen(int htmMaxLen) {
		this.htmMaxLen = htmMaxLen;
	}

	/**
	 * @return the htmFieldSize
	 */
	public final int getHtmFieldSize() {
		return htmFieldSize;
	}

	/**
	 * @param htmFieldSize
	 *            the htmFieldSize to set
	 */
	public final void setHtmFieldSize(int htmFieldSize) {
		this.htmFieldSize = htmFieldSize;
	}

	/**
	 * @return the canEmpty
	 */
	public final boolean getCanEmpty() {
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
	 * @return the custCheckFun
	 */
	public final String getCustCheckFun() {
		return custCheckFun;
	}

	/**
	 * @param custCheckFun
	 *            the custCheckFun to set
	 */
	public final void setCustCheckFun(String custCheckFun) {
		this.custCheckFun = custCheckFun;
	}
}
