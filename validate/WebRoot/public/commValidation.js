
/**
*当断定程序正常运行时不会出现的问题出现的时候使用
*/
function alertAndThrow(str){
	window.alert(str);
	throw str; 
}

/**
*检查必输参数如果 必输参数未输入抛出异常
*@parm 需要检验的参数 必须输入
*@name 参数名 必须输入且不能为undefined 或者 null
*@funName 所在函数名 必须输入且不能为undefined 或者 null
*/
function undefinedNullThrow(parm, name,funName) {
	//name 必须输入且不能为undefined 或者 null
	if (name === undefined || name === null) {
		window.alert("in undefinedNullThrow name can not be undefined or null"+"\n"+"exception will throw");
		throw "in undefinedNullThrow name can not be undefined or null";
	}
	//funName 必须输入且不能为undefined 或者 null
	if (funName === undefined || funName === null) {
		window.alert("in undefinedNullThrow funName can not be undefined or null"+"\n"+"exception will throw");
		throw "in undefinedNullThrow funName can not be undefined or null";
	}

	if (parm === undefined || parm === null) {
		window.alert("in "+funName+", "+name+" can not be undefined or null"+"\n"+"exception will throw");
		throw "in "+funName+", "+name+" can not be undefined or null";
	}
}
var COMMVAL = new COMMVAL_class();
/**
*公共基本校验函数放到COMMVAL_class里
*这个文件里面alert 尽量不要用除非程序正常情况不会出现的提示信息
*/
function COMMVAL_class() {
	this.desc = "";
	/**
    *
    *@str 需要验证的字符串或者数字
    *@intNum 浮点数的整数最长位数
    *@decNum 浮点数的小数最长位数
    */
	this.valFloat = function (str, intNum, decNum) {
		undefinedNullThrow(str,"str","COMMVAL_class.valFloat");
		undefinedNullThrow(intNum,"intNum","COMMVAL_class.valFloat");
		undefinedNullThrow(decNum,"decNum","COMMVAL_class.valFloat");
		
		var reg = new RegExp("^[-\+]?([1-9][\\d]{0," + (intNum - 1) + "}|0)\\.[\\d]{1," + decNum + "}$|^[-\+]?[1-9][\\d]{0," + (intNum - 1) + "}$|^0$");
		return COMMVAL.valGeneral(str,reg);
	};
	/**
	*一般校验
	*@str 需要校验的字符串 必须输入
	*@regExp 字符串满足的正则表达式 必须输入
	*/
	this.valGeneral = function (str, regExp) {
		undefinedNullThrow(str,"str","COMMVAL_class.valGeneral");
		undefinedNullThrow(regExp,"regExp","COMMVAL_class.valGeneral");
		return regExp.test(str.toString());
	};
	this.valInt = function () {
		return false;
	};
	/**
	*取得s的长度 (中文字符一个长度算2) s必须输入
	*/
	this.strLength = function (s) {
		undefinedNullThrow(s,"s","COMMVAL_class.strLength");
		var len = 0, i = 0;
		for (i = 0; i < s.length; i += 1) {
			var c = s.substr(i, 1);
			var ts = escape(c);
			if (ts.substring(0, 2) == "%u") {
				len += 2;
			} else {
				len += 1;
			}
		}
		return len;
	};
	//str前后的空格除去
	this.strTrim = function (str) {
		undefinedNullThrow(str,"str","COMMVAL_class.strTrim");
		if (str === "") {
			return "";
		}
		var i = 0;
		for (i = 0; i < str.length; i += 1) {
			if (str.charAt(i) == " ") {
				continue;
			}
			break;
		}
	//str = "" + str;
		str = str.substring(i, str.length);
		if (str === "") {
			return "";
		}
		for (i = str.length - 1; i >= 0; i -= 1) {
			if (str.charAt(i) === " ") {
				continue;
			}
			break;
		}
		return str.substring(0, i + 1);
	};
}

