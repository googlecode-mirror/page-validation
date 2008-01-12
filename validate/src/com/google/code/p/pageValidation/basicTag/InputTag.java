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
 * @author niebo
 * 
 */
abstract class InputTag implements ValidateHtmFieldTag {

	private PageContext pageContext;
	private JspWriter out;
	private FormTag parent;

	// html input attributes
	private String name = null, title = null, value = null, width = null, height = null, htmclass = null,
					readonly = null, disabled = null, align = null, accept = null, accesskey = null, alt = null,
					border = null, dir = null, id = null, ismap = null, istyle = null, lang = null, checked = null,
					onblur = null, onchange = null, onclick = null, ondblclick = null, onfocus = null, onhelp = null,
					onkeydown = null, onkeypress = null, onkeyup = null, onmousedown = null, onmousemove = null,
					onmouseout = null, onmouseover = null, onmouseup = null, onselect = null, src = null, style = null,
					tabindex = null, usemap = null, type = null;
	private Integer maxlength = null, size = null;
	private boolean canEmpty = false;// defalt do not allow empty

	/**
	 * @return the canEmpty
	 */
	public final boolean isCanEmpty() {
		return canEmpty;
	}

	/**
	 * @param canEmpty
	 *            the canEmpty to set
	 */
	public final void setCanEmpty(boolean canEmpty) {
		this.canEmpty = canEmpty;
	}

	public InputTag(String type) {
		this.setType(type);
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
		if (this.getId() == null)
			this.setId(((HtmBodyTag) formTag.getParent()).autoID(getType()));

		try {
			StringBuffer tmpBuf = new StringBuffer("<input type=\"" + getType() + "\" ");
			tmpBuf.append(name != null ? " name=\"" + name + "\"" : "");
			tmpBuf.append(title != null ? " title=\"" + title + "\"" : "");
			tmpBuf.append(value != null ? " value=\"" + value + "\"" : "");
			tmpBuf.append(width != null ? " width=\"" + width + "\"" : "");
			tmpBuf.append(height != null ? " height=\"" + height + "\"" : "");
			tmpBuf.append(maxlength != null ? " maxlength=\"" + maxlength + "\"" : "");
			tmpBuf.append(htmclass != null ? " class=\"" + htmclass + "\"" : "");
			tmpBuf.append(readonly != null ? " readonly=\"" + readonly + "\"" : "");
			tmpBuf.append(disabled != null ? " disabled=\"" + disabled + "\"" : "");
			tmpBuf.append(align != null ? " align=\"" + align + "\"" : "");
			tmpBuf.append(accept != null ? " accept=\"" + accept + "\"" : "");
			tmpBuf.append(accesskey != null ? " accesskey=\"" + accesskey + "\"" : "");
			tmpBuf.append(alt != null ? " alt=\"" + alt + "\"" : "");
			tmpBuf.append(border != null ? " border=\"" + border + "\"" : "");
			tmpBuf.append(dir != null ? " dir=\"" + dir + "\"" : "");
			tmpBuf.append(id != null ? " id=\"" + id + "\"" : "");
			tmpBuf.append(ismap != null ? " ismap=\"" + ismap + "\"" : "");
			tmpBuf.append(istyle != null ? " istyle=\"" + istyle + "\"" : "");
			tmpBuf.append(lang != null ? " lang=\"" + lang + "\"" : "");
			tmpBuf.append(checked != null ? " checked=\"" + checked + "\"" : "");
			tmpBuf.append(onblur != null ? " onblur=\"" + onblur + "\"" : "");
			tmpBuf.append(onchange != null ? " onchange=\"" + onchange + "\"" : "");
			tmpBuf.append(onclick != null ? " onclick=\"" + onclick + "\"" : "");
			tmpBuf.append(ondblclick != null ? " ondblclick=\"" + ondblclick + "\"" : "");
			tmpBuf.append(onfocus != null ? " onfocus=\"" + onfocus + "\"" : "");
			tmpBuf.append(onhelp != null ? " onhelp=\"" + onhelp + "\"" : "");
			tmpBuf.append(onkeydown != null ? " onkeydown=\"" + onkeydown + "\"" : "");
			tmpBuf.append(onkeypress != null ? " onkeypress=\"" + onkeypress + "\"" : "");
			tmpBuf.append(onkeyup != null ? " onkeyup=\"" + onkeyup + "\"" : "");
			tmpBuf.append(onmousedown != null ? " onmousedown=\"" + onmousedown + "\"" : "");
			tmpBuf.append(onmousemove != null ? " onmousemove=\"" + onmousemove + "\"" : "");
			tmpBuf.append(onmouseout != null ? " onmouseout=\"" + onmouseout + "\"" : "");
			tmpBuf.append(onmouseover != null ? " onmouseover=\"" + onmouseover + "\"" : "");
			tmpBuf.append(onmouseup != null ? " onmouseup=\"" + onmouseup + "\"" : "");
			tmpBuf.append(onselect != null ? " onselect=\"" + onselect + "\"" : "");
			tmpBuf.append(size != null ? " size=\"" + size + "\"" : "");
			tmpBuf.append(src != null ? " src=\"" + src + "\"" : "");
			tmpBuf.append(style != null ? " style=\"" + style + "\"" : "");
			tmpBuf.append(tabindex != null ? " tabindex=\"" + tabindex + "\"" : "");
			tmpBuf.append(usemap != null ? " usemap=\"" + usemap + "\"" : "");
			tmpBuf.append(" />");
			out.println(tmpBuf.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when FormTag.doStartTag");
		}
		return InputTag.SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public final int doEndTag() throws JspException {
		parent.addTagBean(new TagBean(this));
		this.setId(null);
		return Tag.EVAL_PAGE;
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
	public void release() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext(PageContext pc) {
		pageContext = pc;
		out = pc.getOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#setParent(javax.servlet.jsp.tagext.Tag)
	 */
	public void setParent(Tag t) {
		parent = (FormTag) t;
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
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the width
	 */
	public final String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public final void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public final String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public final void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the maxlength
	 */
	public final Integer getMaxlength() {
		return maxlength;
	}

	/**
	 * @param maxlength
	 *            the maxlength to set
	 */
	public final void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * @return the htmclass
	 */
	public final String getHtmClass() {
		return this.htmclass;
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
	 * @return the align
	 */
	public final String getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public final void setAlign(String align) {
		this.align = align;
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
	 * @return the alt
	 */
	public final String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public final void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the border
	 */
	public final String getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            the border to set
	 */
	public final void setBorder(String border) {
		this.border = border;
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
	 * @return the ismap
	 */
	public final String getIsmap() {
		return ismap;
	}

	/**
	 * @param ismap
	 *            the ismap to set
	 */
	public final void setIsmap(String ismap) {
		this.ismap = ismap;
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
	 * @return the checked
	 */
	public final String getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public final void setChecked(String checked) {
		this.checked = checked;
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
	 * @return the size
	 */
	public final Integer getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public final void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return the src
	 */
	public final String getSrc() {
		return src;
	}

	/**
	 * @param src
	 *            the src to set
	 */
	public final void setSrc(String src) {
		this.src = src;
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
	 * @return the usemap
	 */
	public final String getUsemap() {
		return usemap;
	}

	/**
	 * @param usemap
	 *            the usemap to set
	 */
	public final void setUsemap(String usemap) {
		this.usemap = usemap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
