/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.google.code.p.pageValidation.ValidateHtmFieldTag;
import com.google.code.p.pageValidation.beans.TagBean;

/**
 * @author nie
 * 
 */
public abstract class TextareaTag extends ValidateHtmFieldTag {
	private Integer rows = null;
	private Integer cols = null;
	private String title = null;
	private String name = null;
	private String id = null;
	private String htmclass = null;
	private String readonly = null;
	private String disabled = null;
	private String accesskey = null;
	private String dir = null;
	private String istyle = null;
	private String lang = null;
	private String onblur = null;
	private String onchange = null;
	private String onclick = null;
	private String ondblclick = null;
	private String onfocus = null;
	private String onhelp = null;
	private String onkeydown = null;
	private String onkeypress = null;
	private String onkeyup = null;
	private String onmousedown = null;
	private String onmousemove = null;
	private String onmouseout = null;
	private String onmouseover = null;
	private String onmouseup = null;
	private String onselect = null;
	private String style = null;
	private String tabindex = null;

	private PageContext pageContext;
	private JspWriter out;
	private FormTag parent;

	public TextareaTag() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 *//*
		 * (non-Javadoc)
		 * 
		 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
		 */
	public final int doEndTag() throws JspException {
		parent.addTagBean(new TagBean(this));
		this.setId(null);
		try {
			out.println("</textarea>");
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doStartTag");
		}
		return Tag.EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public final int doStartTag() throws JspException {
		FormTag formTag = (FormTag) getParent();
		if (formTag == null)
			throw new JspException("the tag " + getName() + "'s parent must be Form Tag! \n");
		/*
		 * notice when user haven't set a id must set a unique id for a tag and
		 * must set it in doStartTag, two or more tag may use the same tag
		 * instance to generat html code
		 */
		this.setId(((HtmBodyTag) formTag.getParent()).autoID("ta"));

		try {
			StringBuffer tmpBuf = new StringBuffer("<textarea ");
			tmpBuf.append(rows != null ? "rows=\"" + rows + "\"" : "");
			tmpBuf.append(cols != null ? "cols=\"" + cols + "\"" : "");
			tmpBuf.append(title != null ? "title=\"" + title + "\"" : "");
			tmpBuf.append(name != null ? "name=\"" + name + "\"" : "");
			tmpBuf.append(id != null ? "id=\"" + id + "\"" : "");
			tmpBuf.append(htmclass != null ? "class=\"" + htmclass + "\"" : "");
			tmpBuf.append(readonly != null ? "readonly=\"" + readonly + "\"" : "");
			tmpBuf.append(disabled != null ? "disabled=\"" + disabled + "\"" : "");
			tmpBuf.append(accesskey != null ? "accesskey=\"" + accesskey + "\"" : "");
			tmpBuf.append(dir != null ? "dir=\"" + dir + "\"" : "");
			tmpBuf.append(istyle != null ? "istyle=\"" + istyle + "\"" : "");
			tmpBuf.append(lang != null ? "lang=\"" + lang + "\"" : "");
			tmpBuf.append(onblur != null ? "onblur=\"" + onblur + "\"" : "");
			tmpBuf.append(onchange != null ? "onchange=\"" + onchange + "\"" : "");
			tmpBuf.append(onclick != null ? "onclick=\"" + onclick + "\"" : "");
			tmpBuf.append(ondblclick != null ? "ondblclick=\"" + ondblclick + "\"" : "");
			tmpBuf.append(onfocus != null ? "onfocus=\"" + onfocus + "\"" : "");
			tmpBuf.append(onhelp != null ? "onhelp=\"" + onhelp + "\"" : "");
			tmpBuf.append(onkeydown != null ? "onkeydown=\"" + onkeydown + "\"" : "");
			tmpBuf.append(onkeypress != null ? "onkeypress=\"" + onkeypress + "\"" : "");
			tmpBuf.append(onkeyup != null ? "onkeyup=\"" + onkeyup + "\"" : "");
			tmpBuf.append(onmousedown != null ? "onmousedown=\"" + onmousedown + "\"" : "");
			tmpBuf.append(onmousemove != null ? "onmousemove=\"" + onmousemove + "\"" : "");
			tmpBuf.append(onmouseout != null ? "onmouseout=\"" + onmouseout + "\"" : "");
			tmpBuf.append(onmouseover != null ? "onmouseover=\"" + onmouseover + "\"" : "");
			tmpBuf.append(onmouseup != null ? "onmouseup=\"" + onmouseup + "\"" : "");
			tmpBuf.append(onselect != null ? "onselect=\"" + onselect + "\"" : "");
			tmpBuf.append(style != null ? "style=\"" + style + "\"" : "");
			tmpBuf.append(tabindex != null ? "tabindex=\"" + tabindex + "\"" : "");
			tmpBuf.append(" >");
			out.println(tmpBuf.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doStartTag");
		}
		return InputTag.EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#getParent()
	 */
	public final Tag getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public final void release() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public final void setPageContext(PageContext pc) {
		pageContext = pc;
		out = pc.getOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setParent(javax.servlet.jsp.tagext.Tag)
	 */
	public final void setParent(Tag t) {
		parent = (FormTag) t;
	}

	/**
	 * @return the rows
	 */
	public final Integer getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public final void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * @return the cols
	 */
	public final Integer getCols() {
		return cols;
	}

	/**
	 * @param cols
	 *            the cols to set
	 */
	public final void setCols(Integer cols) {
		this.cols = cols;
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
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
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
	 * @return the readonly
	 */
	public final String getReadonly() {
		return readonly;
	}

	/**
	 * @param readonly
	 *            the readonly to set
	 */
	public final void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the disabled
	 */
	public final String getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public final void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the accesskey
	 */
	public final String getAccesskey() {
		return accesskey;
	}

	/**
	 * @param accesskey
	 *            the accesskey to set
	 */
	public final void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
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
	 * @return the istyle
	 */
	public final String getIstyle() {
		return istyle;
	}

	/**
	 * @param istyle
	 *            the istyle to set
	 */
	public final void setIstyle(String istyle) {
		this.istyle = istyle;
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
	 * @return the onblur
	 */
	public final String getOnblur() {
		return onblur;
	}

	/**
	 * @param onblur
	 *            the onblur to set
	 */
	public final void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	/**
	 * @return the onchange
	 */
	public final String getOnchange() {
		return onchange;
	}

	/**
	 * @param onchange
	 *            the onchange to set
	 */
	public final void setOnchange(String onchange) {
		this.onchange = onchange;
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
	 * @return the onfocus
	 */
	public final String getOnfocus() {
		return onfocus;
	}

	/**
	 * @param onfocus
	 *            the onfocus to set
	 */
	public final void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
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
	 * @return the onselect
	 */
	public final String getOnselect() {
		return onselect;
	}

	/**
	 * @param onselect
	 *            the onselect to set
	 */
	public final void setOnselect(String onselect) {
		this.onselect = onselect;
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
	 * @return the tabindex
	 */
	public final String getTabindex() {
		return tabindex;
	}

	/**
	 * @param tabindex
	 *            the tabindex to set
	 */
	public final void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	/**
	 * @return the out
	 */
	public final JspWriter getOut() {
		return out;
	}

	/**
	 * @param out
	 *            the out to set
	 */
	public final void setOut(JspWriter out) {
		this.out = out;
	}

	/**
	 * @return the pageContext
	 */
	public final PageContext getPageContext() {
		return pageContext;
	}
}
