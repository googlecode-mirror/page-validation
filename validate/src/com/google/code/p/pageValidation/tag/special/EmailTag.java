/**
 * 
 */
package com.google.code.p.pageValidation.tag.special;

import com.google.code.p.pageValidation.tag.GeneralTextTag;

/**
 * @author nie
 *
 */
public class EmailTag extends GeneralTextTag {

	public EmailTag(){//"new RegExp(\\\"\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\\\")"
		this.setRegExp("/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/");
	}
}
