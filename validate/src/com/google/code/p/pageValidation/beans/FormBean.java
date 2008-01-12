/**
 * 
 */
package com.google.code.p.pageValidation.beans;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.code.p.pageValidation.basicTag.FormTag;

/**
 * @author nie
 * 为页面上的每一个标签建立一个 bean 
 * 在标签处理程序中 应该在doStart 里面创建这个bean 在 doEnd 里释放连接
 * doStart(){
 * bean = new FormBean();
 * ....
 * }
 * doEnd(){
 * ....
 * bean = null;
 * }
 */
public class FormBean extends tagValBean {
	private final List tagBeanList = new LinkedList();
	private final String id;
	
	public FormBean(FormTag formTag){
		this.id = formTag.getId();
	}
	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}
	/**
	 * add a tag bean to this form bean.
	 * @param bean 
	 */
	public void addTagBean(TagBean bean){
		getTagBeanList().add(bean);
	}
	/**
	 * @return the tagBeanList
	 */
	public final List getTagBeanList() {
		return tagBeanList;
	} 
	
	/* (non-Javadoc)
	 * @see com.google.code.p.pageValidation.beans.tagValBean#getJsString()
	 */
	public String getJsString() {
		String jsValName = "VAL_" + this.getId();
		StringBuffer tmpBuf = new StringBuffer();
		tmpBuf.append("var " + jsValName + " =new VALIDATION(\"" + this.id + "\");\n");
		Iterator ator = this.getTagBeanList().iterator();
		while (ator.hasNext()) {
			TagBean tagBean = (TagBean) ator.next();
			tmpBuf.append(jsValName+"."+tagBean.getJsString());
			tmpBuf.append("\n");
		}
		return tmpBuf.toString();	}
}
