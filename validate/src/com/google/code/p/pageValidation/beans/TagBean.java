/**
 * 
 */
package com.google.code.p.pageValidation.beans;

import com.google.code.p.pageValidation.ValidateHtmFieldTag;

/**
 * @author nie
 * 为页面上的每一个标签建立一个 bean 
 * 在标签处理程序中 应该在doStart 里面创建这个bean 在 doEnd 里释放连接
 * doStart(){
 * bean = new TagBean();
 * ....
 * }
 * doEnd(){
 * ....
 * bean = null;
 * }
 */
public class TagBean extends tagValBean {
	private final String jsString;
	
	public TagBean(ValidateHtmFieldTag tag){
		this.jsString = tag.getValdationJsString();
	}
	/* (non-Javadoc)
	 * @see com.google.code.p.pageValidation.beans.tagValBean#getJsString()
	 */
	public String getJsString() {
		return jsString;
	}
}
