package com.google.code.p.pageValidation.tag.special;

import com.google.code.p.pageValidation.tag.FloatTextTag;

public class Money17Tag extends FloatTextTag {

	public Money17Tag() {
		this.setIntNum(15);
		this.setDecNum(2);
		this.setMinValue("0");
	}
}
