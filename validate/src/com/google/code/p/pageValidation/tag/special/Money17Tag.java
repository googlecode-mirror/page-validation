package com.google.code.p.pageValidation.tag.special;

import com.google.code.p.pageValidation.tag.FloatTextTag;

public class Money17Tag extends FloatTextTag {

	public Money17Tag() {
		this.setIntNum(15);
		this.setDecNum(2);
		this.setMinValue("0");
		this.setTitle("应输入大于等于0.00，整数部分15位以内小数部分两位以内的浮点数！");
	}
}
