package com.google.code.p.pageValidation.tag.special;

import com.google.code.p.pageValidation.tag.GeneralTextTag;
/**
 * 姓名场 最大长度30
 * 不允许输入'"()@$%^*<>&?
 * @author nie
 */
public class NameTag extends GeneralTextTag {
	public NameTag(){
		this.setMaxlength(new Integer(30));
		this.setRegExp("new RegExp(\"^[^'\\\"()@$%^*<>&?]+$\")");
		this.setTitle("应为不包含&prime;&prime;()@$%^*<>&?的字符串！");
	}
}
