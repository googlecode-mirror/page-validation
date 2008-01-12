/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;

import javax.servlet.jsp.tagext.Tag;


/**
 * create html Element id auto 
 * @author niebo
 *
 */
public class AutoID {
	private static int index = 10000;
	public final static String newID(String prifex,Tag tag){
		if(index < 99999)index++;
		else index = 10000;
		return prifex+"_"+index;
	}
//	public final static String newID(String prifex,Tag tag){
//		return prifex+"_"+Integer.toHexString(tag.hashCode());
//	}
}
