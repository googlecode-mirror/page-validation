/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.Tag;

import com.google.code.p.pageValidation.beans.FormBean;

/**
 * @author niebo
 * 
 */
public class HtmBodyTag implements javax.servlet.jsp.tagext.BodyTag {
	private String alink, background, bgcolor, bottommargin, htmclass, dir, id, lang, leftmargin, link, marginheight,
					marginwidth, onclick, ondblclick, onhelp, onkeydown, onkeypress, onkeyup, onload, onmousedown,
					onmousemove, onmouseout, onmouseover, onmouseup, onunload, rightmargin, style, text, title,
					topmargin, vlink;

	public HtmBodyTag() {
	}

	private BodyContent bodyContent = null;
	private PageContext pageContext = null;
	private JspWriter out = null;
	private HtmlTag parent;

	/**
	 * @param formTag
	 * @throws Exception
	 */
	public void addFormBean(FormBean formBean) throws JspException {
		if (parent == null)
			throw new JspException("The Form tag's parent must be HtmBodyTag ");
		parent.addFormBean(formBean);
	}

	/**
	 * auto create a id for HtmlTag‘s chieldren
	 * @param prifex
	 * @return the unique id in a page
	 * @throws JspException when the parent is null throw new JspException("The Form tag's parent must be HtmBodyTag ");
	 */
	public final String autoID(String prifex) throws JspException {
		if (parent == null)
			throw new JspException("The Form tag's parent must be HtmBodyTag ");
		return parent.autoID(prifex);
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
		return HtmBodyTag.SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {
			out.println("</body>");
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when BodyTag.doEndTag");
		}
		return Tag.EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		/*notice when user haven't set a id must set a unique id for a tag
		 * and must set it in doStartTag, two or more tag may use the same tag instance
		 * to generat html code*/
		if(this.getId() == null)this.setId(this.autoID("body"));

		try {
			StringBuffer tmpBuf = new StringBuffer();
			tmpBuf.append("<script language=\"javascript\" type=\"text/javascript\" src=\"/validate/public/commValidation.js\"></script>");
			tmpBuf.append("<script language=\"javascript\" type=\"text/javascript\" src=\"/validate/public/Validation.js\"></script>");
			tmpBuf.append("<body ");
			tmpBuf.append(alink != null ? "alink=\"" + alink + "\"" : "");
			tmpBuf.append(background != null ? "background=\"" + background + "\"" : "");
			tmpBuf.append(bgcolor != null ? "bgcolor=\"" + bgcolor + "\"" : "");
			tmpBuf.append(bottommargin != null ? "bottommargin=\"" + bottommargin + "\"" : "");
			tmpBuf.append(htmclass != null ? "class=\"" + htmclass + "\"" : "");
			tmpBuf.append(dir != null ? "dir=\"" + dir + "\"" : "");
			tmpBuf.append(id != null ? "id=\"" + id + "\"" : "");
			tmpBuf.append(lang != null ? "lang=\"" + lang + "\"" : "");
			tmpBuf.append(leftmargin != null ? "leftmargin=\"" + leftmargin + "\"" : "");
			tmpBuf.append(link != null ? "link=\"" + link + "\"" : "");
			tmpBuf.append(marginheight != null ? "marginheight=\"" + marginheight + "\"" : "");
			tmpBuf.append(marginwidth != null ? "marginwidth=\"" + marginwidth + "\"" : "");
			tmpBuf.append(onclick != null ? "onclick=\"" + onclick + "\"" : "");
			tmpBuf.append(ondblclick != null ? "ondblclick=\"" + ondblclick + "\"" : "");
			tmpBuf.append(onhelp != null ? "onhelp=\"" + onhelp + "\"" : "");
			tmpBuf.append(onkeydown != null ? "onkeydown=\"" + onkeydown + "\"" : "");
			tmpBuf.append(onkeypress != null ? "onkeypress=\"" + onkeypress + "\"" : "");
			tmpBuf.append(onkeyup != null ? "onkeyup=\"" + onkeyup + "\"" : "");
			tmpBuf.append(onload != null ? "onload=\"" + onload + "\"" : "");
			tmpBuf.append(onmousedown != null ? "onmousedown=\"" + onmousedown + "\"" : "");
			tmpBuf.append(onmousemove != null ? "onmousemove=\"" + onmousemove + "\"" : "");
			tmpBuf.append(onmouseout != null ? "onmouseout=\"" + onmouseout + "\"" : "");
			tmpBuf.append(onmouseover != null ? "onmouseover=\"" + onmouseover + "\"" : "");
			tmpBuf.append(onmouseup != null ? "onmouseup=\"" + onmouseup + "\"" : "");
			tmpBuf.append(onunload != null ? "onunload=\"" + onunload + "\"" : "");
			tmpBuf.append(rightmargin != null ? "rightmargin=\"" + rightmargin + "\"" : "");
			tmpBuf.append(style != null ? "style=\"" + style + "\"" : "");
			tmpBuf.append(text != null ? "text=\"" + text + "\"" : "");
			tmpBuf.append(title != null ? "title=\"" + title + "\"" : "");
			tmpBuf.append(topmargin != null ? "topmargin=\"" + topmargin + "\"" : "");
			tmpBuf.append(vlink != null ? "vlink=\"" + vlink + "\"" : "");
			tmpBuf.append(">");
			out.println(tmpBuf.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("IOException when HtmBodyTag.doStartTag");
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	public Tag getParent() {
		return this.parent;
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
		this.parent = (HtmlTag) t;
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
	 * @return the alink
	 */
	public final String getAlink() {
		return alink;
	}

	/**
	 * @param alink
	 *            the alink to set
	 */
	public final void setAlink(String alink) {
		this.alink = alink;
	}

	/**
	 * @return the background
	 */
	public final String getBackground() {
		return background;
	}

	/**
	 * @param background
	 *            the background to set
	 */
	public final void setBackground(String background) {
		this.background = background;
	}

	/**
	 * @return the bgcolor
	 */
	public final String getBgcolor() {
		return bgcolor;
	}

	/**
	 * @param bgcolor
	 *            the bgcolor to set
	 */
	public final void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	/**
	 * @return the bottommargin
	 */
	public final String getBottommargin() {
		return bottommargin;
	}

	/**
	 * @param bottommargin
	 *            the bottommargin to set
	 */
	public final void setBottommargin(String bottommargin) {
		this.bottommargin = bottommargin;
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
	 * @return the leftmargin
	 */
	public final String getLeftmargin() {
		return leftmargin;
	}

	/**
	 * @param leftmargin
	 *            the leftmargin to set
	 */
	public final void setLeftmargin(String leftmargin) {
		this.leftmargin = leftmargin;
	}

	/**
	 * @return the link
	 */
	public final String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public final void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the marginheight
	 */
	public final String getMarginheight() {
		return marginheight;
	}

	/**
	 * @param marginheight
	 *            the marginheight to set
	 */
	public final void setMarginheight(String marginheight) {
		this.marginheight = marginheight;
	}

	/**
	 * @return the marginwidth
	 */
	public final String getMarginwidth() {
		return marginwidth;
	}

	/**
	 * @param marginwidth
	 *            the marginwidth to set
	 */
	public final void setMarginwidth(String marginwidth) {
		this.marginwidth = marginwidth;
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
	 * @return the onload
	 */
	public final String getOnload() {
		return onload;
	}

	/**
	 * @param onload
	 *            the onload to set
	 */
	public final void setOnload(String onload) {
		this.onload = onload;
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
	 * @return the onunload
	 */
	public final String getOnunload() {
		return onunload;
	}

	/**
	 * @param onunload
	 *            the onunload to set
	 */
	public final void setOnunload(String onunload) {
		this.onunload = onunload;
	}

	/**
	 * @return the rightmargin
	 */
	public final String getRightmargin() {
		return rightmargin;
	}

	/**
	 * @param rightmargin
	 *            the rightmargin to set
	 */
	public final void setRightmargin(String rightmargin) {
		this.rightmargin = rightmargin;
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
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public final void setText(String text) {
		this.text = text;
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
	 * @return the topmargin
	 */
	public final String getTopmargin() {
		return topmargin;
	}

	/**
	 * @param topmargin
	 *            the topmargin to set
	 */
	public final void setTopmargin(String topmargin) {
		this.topmargin = topmargin;
	}

	/**
	 * @return the vlink
	 */
	public final String getVlink() {
		return vlink;
	}

	/**
	 * @param vlink
	 *            the vlink to set
	 */
	public final void setVlink(String vlink) {
		this.vlink = vlink;
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
	 * @param parent
	 *            the parent to set
	 */
	public final void setParent(HtmlTag parent) {
		this.parent = parent;
	}

}
