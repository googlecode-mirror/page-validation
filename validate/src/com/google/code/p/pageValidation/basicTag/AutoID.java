/**
 * 
 */
package com.google.code.p.pageValidation.basicTag;


/**
 * create html Element id auto 
 * @author niebo
 *
 */
public class AutoID {
	private static int index = 10000;
	public final static String newID(String prifex){
		if(index < 99999)index++;
		else index = 10000;
		return prifex+"_"+index;
	}
}
