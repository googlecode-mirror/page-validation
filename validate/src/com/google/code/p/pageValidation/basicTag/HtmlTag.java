/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.google.code.p.pageValidation.beans.BodyBean;
import com.google.code.p.pageValidation.beans.FormBean;

/**
 * @author niebo
 * 
 */
public class HtmlTag implements BodyTag {
	String dir=null;
	String lang=null;
	String version=null;

	public HtmlTag() {
	}

	/**
	 * auto create a id for HtmlTag‘s chieldren
	 * @param prifex
	 * @return the unique id in a page
	 */
	public final String autoID(String prifex) {
		return bodyBean.autoID(prifex);
	}

	private BodyContent bodyContent = null;
	private PageContext pageContext = null;
	private JspWriter out = null;
	//when setPageContext set it; 
	private BodyBean bodyBean = null;

	/**
	 * @param formTag
	 */
	public void addFormBean(FormBean formBean) {
		bodyBean.addFormBean(formBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTag#doInitBody()
	 */
	public void doInitBody() throws JspException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTag#setBodyContent(javax.servlet.jsp.tagext.BodyContent)
	 */
	public void setBodyContent(BodyContent b) {
		bodyContent = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		return HtmlTag.SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		StringBuffer tmpBuf = new StringBuffer();
		tmpBuf.append("<script language = \"javascript\" type=\"text/javascript\">\n");
		tmpBuf.append(bodyBean.getJsString());
		tmpBuf.append("</script>");
		tmpBuf.append("</html>");
		try {
			out.println(tmpBuf);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when HtmlTag.doEndTag");
		}
		// clear last time added validateHtmFieldTags
		bodyBean.clearFormBean();

		return Tag.SKIP_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		
		try {
			StringBuffer tmpBuf = new StringBuffer("<html");
			tmpBuf.append(dir != null ? " dir=" + "\"" + dir + "\"" : "");
			tmpBuf.append(lang != null ? " lang=" + "\"" + lang + "\"" : "");
			tmpBuf.append(version != null ? " version=" + "\"" + version + "\"" : "");
			tmpBuf.append(">");
			out.println(tmpBuf);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doStartTag");
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext(PageContext pc) {
		this.pageContext = pc;
		if(pc.getAttribute(BodyBean.BODY_BEAN_IN_JSP_NAME,PageContext.PAGE_SCOPE) == null){
			pc.setAttribute(BodyBean.BODY_BEAN_IN_JSP_NAME,new BodyBean(),PageContext.PAGE_SCOPE);
		}
		bodyBean = (BodyBean)pc.getAttribute(BodyBean.BODY_BEAN_IN_JSP_NAME,PageContext.PAGE_SCOPE);
		out = pc.getOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setParent(javax.servlet.jsp.tagext.Tag)
	 */
	public void setParent(Tag t) {

	}

	/**
	 * @return the bodyContent
	 */
	public final BodyContent getBodyContent() {
		return bodyContent;
	}
	/**
	 * @return the pageContext
	 */
	public final PageContext getPageContext() {
		return pageContext;
	}

	/**
	 * @return the dir
	 */
	public final String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public final void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return the lang
	 */
	public final String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public final void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the version
	 */
	public final String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public final void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the out
	 */
	public final JspWriter getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public final void setOut(JspWriter out) {
		this.out = out;
	}

	public Tag getParent() {
		return null;
	}

	/**
	 * @return the bodyBean
	 */
	public final BodyBean getBodyBean() {
		return bodyBean;
	}
}
