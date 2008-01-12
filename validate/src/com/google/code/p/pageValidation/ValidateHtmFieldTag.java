/**
 * 
 */
package com.google.code.p.pageValidation;

import java.io.IOException;

import javax.servlet.jsp.tagext.Tag;

/**
 * @author niebo
 *
 */
public interface ValidateHtmFieldTag extends Tag {
	/**
	 * write javascript for this Tag 
	 * @param jsValidateVarName the javascript class VALIDATION's instance name
	 * @throws IOException 
	 */
	public String getValdationJsString();
}
