/**
 * 
 */
package com.google.code.p.pageValidation.beans;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author nie
 *
 */
public class BodyBean extends tagValBean {
	public static final String BODY_BEAN_IN_JSP_NAME = "bodyBean_Name_in_PageContext";
	private final List formBeanList = new LinkedList();
	private int autoIdIndex = 1;
	
	/**
	 * auto create a id for HtmlTag‘s chieldren
	 * @param prifex
	 * @return the unique id in a page
	 */
	public final String autoID(String prifex) {
		return prifex + "_" + Integer.toHexString(autoIdIndex ++);
	}
	/* (non-Javadoc)
	 * @see com.google.code.p.pageValidation.beans.tagValBean#getJsString()
	 */
	public String getJsString() {
		StringBuffer tmpBuf = new StringBuffer();
		Iterator ator = this.formBeanList.iterator();
		while (ator.hasNext()) {
			FormBean formBean = (FormBean) ator.next();
			tmpBuf.append(formBean.getJsString());
			tmpBuf.append("\n");
		}		
		return tmpBuf.toString();
	}
	/**
	 * clear all formBeans
	 */
	public void clearFormBean(){
		autoIdIndex = 1;
		formBeanList.clear();
	}
	
	/**
	 * @param formBean
	 */
	public void addFormBean(FormBean formBean){
		getFormBeanList().add(formBean);
	}
	/**
	 * @return the formBeanList
	 */
	public final List getFormBeanList() {
		return formBeanList;
	}

}
