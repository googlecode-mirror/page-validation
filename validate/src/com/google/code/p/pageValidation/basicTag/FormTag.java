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

import com.google.code.p.pageValidation.beans.FormBean;
import com.google.code.p.pageValidation.beans.TagBean;

/**
 * @author niebo
 * 
 */
public class FormTag implements BodyTag {
	private String id;
	private String method;
	private String action;
	private String accept;
	private String acceptcharset; // html form's accept-charset
	private String htmclass; // html form's class
	private String dir;
	private String enctype;
	private String lang;
	private String onclick;
	private String ondblclick;
	private String onhelp;
	private String onkeydown;
	private String onkeypress;
	private String onkeyup;
	private String onmousedown;
	private String onmousemove;
	private String onmouseout;
	private String onmouseover;
	private String onmouseup;
	private String onreset;
	private String onsubmit;
	private String style;
	private String target;
	private String title;

	public FormTag() {
		this.setMethod("post");		// default "post"
	}

	private BodyContent bodyContent = null;
	private PageContext pageContext = null;
	private JspWriter out = null;
	private HtmBodyTag parent;
	//the formBean must create in doStart and set to null in doEnd
	private FormBean formBean = null;

	/**
	 * @param valHtmFieldTag
	 */
	public void addTagBean(TagBean bean) {
		formBean.addTagBean(bean);
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
		return FormTag.SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		if(parent == null) throw new JspException("The Form tag's parent must be HtmBodyTag ");
		
		//release formBean and id as it's live scope only between doStartTag and doEndTag
		formBean = null;
		this.setId(null);
		
		try {
			out.println("</form>");
			
			//this part change to do in HtmlTag
//			String jsValName = "VAL_" + this.getId();
//			StringBuffer tmpBuf = new StringBuffer();
//			tmpBuf.append("<script language = \"javascript\" type=\"text/javascript\">\n");
//			tmpBuf.append("var " + jsValName + " =new VALIDATION(\"" + this.id + "\")\n");
//			Iterator ator = this.validateHtmFieldTagList.iterator();
//			while (ator.hasNext()) {
//				ValidateHtmFieldTag tag = (ValidateHtmFieldTag) ator.next();
//				tmpBuf.append(tag.getValdationJsString(jsValName));
//				tmpBuf.append("\n");
//			}
//			tmpBuf.append("</script>");
//			out.println(tmpBuf);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doEndTag");
		}
		return Tag.EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		if(parent == null) throw new JspException("The Form tag's parent must be HtmBodyTag ");
		/*notice when user haven't set a id must set a unique id for a tag
		 * and must set it in doStartTag, two or more tag may use the same tag instance
		 * to generat html code*/
		if(this.getId() == null)this.setId(parent.autoID("form"));
		this.formBean = new FormBean(this);
		parent.addFormBean(formBean);
		
		try {
			StringBuffer tmpBuf = new StringBuffer("<form ");
			tmpBuf.append(id != null ? " id=" + "\"" + id + "\"" : "");
			tmpBuf.append(method != null ? " method=" + "\"" + method + "\"" : "");
			tmpBuf.append(action != null ? " action=" + "\"" + action + "\"" : "");
			tmpBuf.append(accept != null ? " accept=" + "\"" + accept + "\"" : "");
			tmpBuf.append(acceptcharset != null ? " accept-charset=" + "\"" + acceptcharset + "\"" : "");
			tmpBuf.append(htmclass != null ? " class=" + "\"" + htmclass + "\"" : "");
			tmpBuf.append(dir != null ? " dir=" + "\"" + dir + "\"" : "");
			tmpBuf.append(enctype != null ? " enctype=" + "\"" + enctype + "\"" : "");
			tmpBuf.append(lang != null ? " lang=" + "\"" + lang + "\"" : "");
			tmpBuf.append(onclick != null ? " onclick=" + "\"" + onclick + "\"" : "");
			tmpBuf.append(ondblclick != null ? " ondblclick=" + "\"" + ondblclick + "\"" : "");
			tmpBuf.append(onhelp != null ? " onhelp=" + "\"" + onhelp + "\"" : "");
			tmpBuf.append(onkeydown != null ? " onkeydown=" + "\"" + onkeydown + "\"" : "");
			tmpBuf.append(onkeypress != null ? " onkeypress=" + "\"" + onkeypress + "\"" : "");
			tmpBuf.append(onkeyup != null ? " onkeyup=" + "\"" + onkeyup + "\"" : "");
			tmpBuf.append(onmousedown != null ? " onmousedown=" + "\"" + onmousedown + "\"" : "");
			tmpBuf.append(onmousemove != null ? " onmousemove=" + "\"" + onmousemove + "\"" : "");
			tmpBuf.append(onmouseout != null ? " onmouseout=" + "\"" + onmouseout + "\"" : "");
			tmpBuf.append(onmouseover != null ? " onmouseover=" + "\"" + onmouseover + "\"" : "");
			tmpBuf.append(onmouseup != null ? " onmouseup=" + "\"" + onmouseup + "\"" : "");
			tmpBuf.append(onreset != null ? " onreset=" + "\"" + onreset + "\"" : "");
			tmpBuf.append(onsubmit != null ? " onsubmit=" + "\"" + onsubmit + "\"" : "");
			tmpBuf.append(style != null ? " style=" + "\"" + style + "\"" : "");
			tmpBuf.append(target != null ? " target=" + "\"" + target + "\"" : "");
			tmpBuf.append(title != null ? " title=" + "\"" + title + "\"" : "");
			tmpBuf.append(" >");
			out.println(tmpBuf.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doStartTag");
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#getParent()
	 */
	public Tag getParent() {
		return parent;
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
		out = pc.getOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setParent(javax.servlet.jsp.tagext.Tag)
	 */
	public void setParent(Tag t) {
		this.parent = (HtmBodyTag)t;
	}

	/**
	 * @return the bodyContent
	 */
	public final BodyContent getBodyContent() {
		return bodyContent;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the method
	 */
	public final String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public final void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the action
	 */
	public final String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public final void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the accept
	 */
	public final String getAccept() {
		return accept;
	}

	/**
	 * @param accept
	 *            the accept to set
	 */
	public final void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the acceptcharset
	 */
	public final String getAcceptcharset() {
		return acceptcharset;
	}

	/**
	 * @param acceptcharset
	 *            the acceptcharset to set
	 */
	public final void setAcceptcharset(String acceptcharset) {
		this.acceptcharset = acceptcharset;
	}

	/**
	 * @return the htmclass
	 */
	public final String getHtmclass() {
		return htmclass;
	}

	/**
	 * @param htmclass
	 *            the htmclass to set
	 */
	public final void setClass(String htmclass) {
		this.htmclass = htmclass;
	}

	/**
	 * @return the dir
	 */
	public final String getDir() {
		return dir;
	}

	/**
	 * @param dir
	 *            the dir to set
	 */
	public final void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return the enctype
	 */
	public final String getEnctype() {
		return enctype;
	}

	/**
	 * @param enctype
	 *            the enctype to set
	 */
	public final void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	/**
	 * @return the lang
	 */
	public final String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public final void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the onclick
	 */
	public final String getOnclick() {
		return onclick;
	}

	/**
	 * @param onclick
	 *            the onclick to set
	 */
	public final void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @return the ondblclick
	 */
	public final String getOndblclick() {
		return ondblclick;
	}

	/**
	 * @param ondblclick
	 *            the ondblclick to set
	 */
	public final void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	/**
	 * @return the onhelp
	 */
	public final String getOnhelp() {
		return onhelp;
	}

	/**
	 * @param onhelp
	 *            the onhelp to set
	 */
	public final void setOnhelp(String onhelp) {
		this.onhelp = onhelp;
	}

	/**
	 * @return the onkeydown
	 */
	public final String getOnkeydown() {
		return onkeydown;
	}

	/**
	 * @param onkeydown
	 *            the onkeydown to set
	 */
	public final void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	/**
	 * @return the onkeypress
	 */
	public final String getOnkeypress() {
		return onkeypress;
	}

	/**
	 * @param onkeypress
	 *            the onkeypress to set
	 */
	public final void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	/**
	 * @return the onkeyup
	 */
	public final String getOnkeyup() {
		return onkeyup;
	}

	/**
	 * @param onkeyup
	 *            the onkeyup to set
	 */
	public final void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	/**
	 * @return the onmousedown
	 */
	public final String getOnmousedown() {
		return onmousedown;
	}

	/**
	 * @param onmousedown
	 *            the onmousedown to set
	 */
	public final void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	/**
	 * @return the onmousemove
	 */
	public final String getOnmousemove() {
		return onmousemove;
	}

	/**
	 * @param onmousemove
	 *            the onmousemove to set
	 */
	public final void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	/**
	 * @return the onmouseout
	 */
	public final String getOnmouseout() {
		return onmouseout;
	}

	/**
	 * @param onmouseout
	 *            the onmouseout to set
	 */
	public final void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	/**
	 * @return the onmouseover
	 */
	public final String getOnmouseover() {
		return onmouseover;
	}

	/**
	 * @param onmouseover
	 *            the onmouseover to set
	 */
	public final void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	/**
	 * @return the onmouseup
	 */
	public final String getOnmouseup() {
		return onmouseup;
	}

	/**
	 * @param onmouseup
	 *            the onmouseup to set
	 */
	public final void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	/**
	 * @return the onreset
	 */
	public final String getOnreset() {
		return onreset;
	}

	/**
	 * @param onreset
	 *            the onreset to set
	 */
	public final void setOnreset(String onreset) {
		this.onreset = onreset;
	}

	/**
	 * @return the onsubmit
	 */
	public final String getOnsubmit() {
		return onsubmit;
	}

	/**
	 * @param onsubmit
	 *            the onsubmit to set
	 */
	public final void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	/**
	 * @return the style
	 */
	public final String getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public final void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the target
	 */
	public final String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public final void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pageContext
	 */
	public final PageContext getPageContext() {
		return pageContext;
	}

}
