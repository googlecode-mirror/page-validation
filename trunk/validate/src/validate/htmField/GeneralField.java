package validate.htmField;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import validate.ValidateException;

public class GeneralField extends ValHtmField{
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

	/* (non-Javadoc)
	 * @see validate.htmField.ValHtmField#writeJs(javax.servlet.jsp.JspWriter)
	 */

	public void writeJs(JspWriter out) throws IOException,ValidateException {
		StringBuffer outStr = new StringBuffer("VALIDATION.addGeneralField(");
		outStr.append(getHtmFieldID() + ",");
		outStr.append(getHtmTitle() + ",");
		outStr.append(getHtmMaxLen() + ",");
		outStr.append(getHtmFieldSize() + ",");
		outStr.append(getCanEmpty() + ",");
		outStr.append(getRegExp() + ",");
		outStr.append(getCustCheckFun() + ");");
		out.println(outStr.toString());
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
	
}