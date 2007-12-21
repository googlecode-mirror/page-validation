/*
 * 创建日期 2007-12-14
 * 
 * TODO 要更改此生成的文件的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package validate;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import validate.htmField.FloatField;
import validate.htmField.GeneralField;
import validate.htmField.ValHtmField;

/**
 * @author kfzx-niebo
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
/**
 * @author niebo
 *
 */
public class Validate {

	public final static String JS_COMM = "commValidation.js";
	private final List htmFieldList = new LinkedList();
	private String subCheckFormId = null;

	/**
	 * 添加一个需要校验的Float场，该方法写入js同名方法调用到网页以
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
	public void addFloatField(String htmFieldID, String htmTitle, int htmMaxLen, int htmFieldSize, boolean canEmpty,
			int intNum, int decNum, String maxValStr, String minValStr, String custCheckFun) {
		FloatField floatField = new FloatField(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun);
		htmFieldList.add(floatField);
	}

	/**
	 * @see Validate#addFloatField(String, String, int, int, boolean, int, int, String, String, String)
	 * @param htmFieldIDArr  Float场的ID属性数组
	 * @param htmTitleArr 提示信息的标题数组
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param intNum 该场的整数部分最大长度
	 * @param decNum 该场的小数部分最大长度
	 * @param maxValStr 该场的最大值 可输入null表示无上限
	 * @param minValStr 该场的最小值 可输入null表示无下限
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
	 * @throws ValidateException 
	 */
	public void addFloatField(String[] htmFieldIDArr, String[] htmTitleArr, int htmMaxLen, int htmFieldSize,
			boolean canEmpty, int intNum, int decNum, String maxValStr, String minValStr, String custCheckFun) throws ValidateException {
		if(htmFieldIDArr.length != htmTitleArr.length) throw new ValidateException("length of htmFieldIDArr and htmTitleArr must equal!");
		for (int i = 0; i < htmFieldIDArr.length; i++) {
			addFloatField(htmFieldIDArr[i], htmTitleArr[i], htmMaxLen, htmFieldSize, canEmpty, intNum, decNum, maxValStr,
					minValStr, custCheckFun);
		}
	}

	/**
	 * @param htmFieldID Float场的ID属性
	 * @param htmTitle  提示信息的标题
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param regExp 该场满足的js正则表达式
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
	 */
	public void addGeneralField(String htmFieldID,String htmTitle,int htmMaxLen,int htmFieldSize,boolean canEmpty,String regExp,String custCheckFun){
		htmFieldList.add(new GeneralField(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun));
	}

	/**
	 * @param htmFieldIDArr Float场的ID属性 数组
	 * @param htmTitleArr  提示信息的标题 数组
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param regExp 该场满足的js正则表达式
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
	 * @throws ValidateException 
	 */
	public void addGeneralField(String[] htmFieldIDArr,String[] htmTitleArr,int htmMaxLen,int htmFieldSize,boolean canEmpty,String regExp,String custCheckFun) throws ValidateException{
		if(htmFieldIDArr.length != htmTitleArr.length) throw new ValidateException("length of htmFieldIDArr and htmTitleArr must equal!");
		for(int i =0;i < htmFieldIDArr.length;i++)
			htmFieldList.add(new GeneralField(htmFieldIDArr[i],htmTitleArr[i],htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun));
	}

	
	/**
	 * @param out
	 * @param formName
	 * @throws IOException
	 * @throws ValidateException
	 */
	public void writeJs(JspWriter out,String formId) throws IOException, ValidateException {
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
			out.println("VALIDATION.setSubCheckForm(\"" + getSubCheckFormId() + "\");");
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
