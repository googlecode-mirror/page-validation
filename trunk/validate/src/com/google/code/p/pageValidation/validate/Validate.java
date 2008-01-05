/*
 * 创建日期 2007-12-14
 * js验证函数的java接口
 * */
package com.google.code.p.pageValidation.validate;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.google.code.p.pageValidation.validate.htmField.ValHtmField;


/**
 * @author kfzx-niebo js验证函数的java接口
 */
public class Validate {

	public final static String JS_COMM = "commValidation.js";
	private final List htmFieldList = new LinkedList();
	private String subCheckFormId = null;

	/**
	 * @param htmField
	 *            htmField的子类。
	 */
	public void addHtmField(ValHtmField htmField) {
		htmFieldList.add(htmField);
	}

	/**
	 * @param htmFields
	 *            htmField的数组
	 */
	public void addHtmFields(ValHtmField[] htmFields) {
		for (int i = 0; i < htmFields.length; i++)
			htmFieldList.add(htmFields[i]);
	}

	/**
	 * @param out
	 * @param formName
	 * @throws IOException
	 * @throws ValidateException
	 */
	public void writeJs(JspWriter out, String formId) throws IOException,
			ValidateException {
		setSubCheckFormId(formId);
		if (htmFieldList.size() > 0) {
			out.println("<script language = \"javascript\" >");
			Iterator iter = this.htmFieldList.iterator();
			while (iter.hasNext()) {
				ValHtmField valHtmField = (ValHtmField) iter.next();
				valHtmField.writeJs(out);
			}
			if (getSubCheckFormId() == null) {
				throw new ValidateException(
						"you must call Validate.setSubCheckFormId first before call Validate.writeJs");
			}
			out.println("VALIDATION.setSubCheckForm(\"" + getSubCheckFormId()
					+ "\");");
			out.println("</script>");
		}
	}

	/**
	 * @return 返回 subCheckFormId。
	 */
	public String getSubCheckFormId() {
		return subCheckFormId;
	}

	/**
	 * @param subCheckFormId
	 *            要设置的 subCheckFormId。
	 */
	private void setSubCheckFormId(String subCheckFormId) {
		this.subCheckFormId = subCheckFormId;
	}
}
