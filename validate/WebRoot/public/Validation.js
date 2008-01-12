
/************************************************VALIDATION公用部分************************************/
function VALIDATION(htmFormID){
     var SUB_CHECK_FIELD_ARR = new Array();     
     this.SUB_CHECK_FIELD_ARR = SUB_CHECK_FIELD_ARR;
     if(htmFormID !== undefined && htmFormID !== null){
      this.setSubCheckForm(htmFormID);
     }
};
 /**
 *将text field得光标设置到最后
 *@t textField 必须输入
 */
 VALIDATION.prototype.setSelToEnd = function (htmText){
    undefinedNullThrow(htmText,"htmText","VALIDATION.setSubCheckForm");
    var range = htmText.createTextRange();
    range.move("textedit");
    range.select();
 };    
      	
/**
*设置哪一个需要提交前校验的form
*@formID 必须输入
*/
VALIDATION.prototype.setSubCheckForm = function(formID){
	undefinedNullThrow(formID,"formID","VALIDATION.setSubCheckForm");
	var this_val = this;
	var form = document.getElementById(formID);
	if(form.tagName.toUpperCase() != "FORM" ){
		alert("VALIDATION.setSubCheckForm 的参数必须输入为FORM id");
		return false;
	}
	var oldsub = form.onsubmit;
	form.onsubmit = function(){
		if(this_val.subCheck()){
		//做<form ...中的onsubmit校验
			if(oldsub != null && oldsub instanceof Function){
				var pageres = oldsub();
				if(pageres !== true && pageres !== false){
					alertAndThrow("VALIDATION requare onsubmit mathod return true or false at function: \n"+oldsub.toString());
				}				
				return pageres;
			}else return true;
		}else return false;		
	}				
}

/**
*提交前的校验
*/
VALIDATION.prototype.subCheck = function(){
	var index;   
	for(index in this.SUB_CHECK_FIELD_ARR){
		index = this.SUB_CHECK_FIELD_ARR[index];
		var item = document.getElementById(index);
		if(item.subCheck == undefined || item.subCheck == null){
		    alertAndThrow("公共函数VALIDATION.subCheck处 出错！请联系公共函数管理员！"+item.subCheck);
				return false;
		}
		if(item.subCheck() != true){
		    return false;
		}
	}
	return true;
}


/**
*设置htmField的maxlength，size. 设置onblur,subCheck函数 实现自动校验
*注意：当一个输入场是只读的时候不进行校验 
* @param htmFieldID Float场的ID属性 必须输入
* @param htmTitle  提示信息的标题 必须输入
* @param htmMaxLen 该场的maxlength属性 
* @param htmFieldSize 该场得size属性 
* @param canEmpty  该场是否允许提交空值 必须输入
* @param validateFun 子函数(比如:addFloatField)的校验函数 必须输入
* @param chieldFunName 子函数名 比如：“addFloatField”
*/
VALIDATION.prototype.addHtmField = function(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty, validateFun,chieldFunName) {
    var this_val = this;
	undefinedNullThrow(htmFieldID, "htmFieldID", "VALIDATION.addHtmField");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.addHtmField");
	//undefinedNullThrow(htmMaxLen, "htmMaxLen", "VALIDATION.addHtmField");
	//undefinedNullThrow(htmFieldSize, "htmFieldSize", "VALIDATION.addHtmField");
	undefinedNullThrow(canEmpty, "canEmpty", "VALIDATION.addHtmField");
	//undefinedNullThrow(custCheckFun, "custCheckFun", "VALIDATION.addHtmField");//可以为null
    this.SUB_CHECK_FIELD_ARR[htmFieldID]=htmFieldID;
    
    var htmField = document.getElementById(htmFieldID);
    if(htmField == null){
       alertAndThrow("找不到ID为"+htmFieldID+"的输入域 请处理");
       return false;
    }
    if((!htmField.type.toUpperCase () =="TEXTAREA") ||
    	(!htmField.type.toUpperCase () =="TEXT") ||
     	(!htmField.type.toUpperCase () =="PASSWORD")){
       alertAndThrow("ID为"+htmFieldID+"的不是输入域 请处理");
       return false;
    }
	if(htmMaxLen !== undefined && htmMaxLen !== null){
		htmField.maxLength = htmMaxLen;
	}
    if(htmFieldSize !== undefined && htmFieldSize !== null && !htmField.type.toUpperCase () =="TEXTAREA"){
        htmField.size = htmFieldSize;
    }
    htmField.canEmpty = canEmpty;
	
 	var pageBlur = htmField.onblur;
    
    if(validateFun === undefined || !(validateFun instanceof Function)){
    	alertAndThrow("htmField.validate 未定义，必须在"+chieldFunName+"中实现该方法");
    }
    
    htmField.validate = function() {
    	return validateFun(htmField);
    };
    htmField.onblur = function(){
		var r = true;
		//先做<input ... >中定义的onblur
		if(pageBlur != null && pageBlur instanceof Function){
			var pageres = pageBlur();
			if(pageres !== true && pageres !== false){
				alertAndThrow("VALIDATION requare onblur mathod return true or false at function: \n"+pageBlur.toString());
			}
			r = r && pageres; 
		} 
        
		//防止不输入时死循环不输入不校验
		if(htmField.value == "")return true;
		//如果该输入场是只读的 则不需要校验
		if(htmField.readOnly === true)return true;
            
		if(r) r = r && htmField.validate();   
		          
		if(r == false) this_val.setSelToEnd(htmField);   
		return r;        
    }
    
    htmField.subCheck = function(){
    	//允许为空提交且为空时直接提交 不需要继续校验
    	if(htmField.value == "" && canEmpty)return true;
		 //如果该输入场是只读的 则不需要校验
		if(htmField.readOnly === true)return true;
		
		var r = true;
		if(!canEmpty && htmField.value == ""){
	    	alert("\""+htmTitle+"\""+"不能为空");
	     	r = false;	    
		}    
		
		if(r) r = r && htmField.validate();             
		if(r == false) this_val.setSelToEnd(htmField);   
		return r;       
    }  	
};


//length
/**
*检查输入是否超过最大长度maxLength
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*maxLength 最大长度
*如果不满足给出提示
*/
VALIDATION.prototype.lengthMaxAlert = function(htmField,htmTitle,maxLength){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.lengthMaxAlert");
	undefinedNullThrow(maxLength, "maxLength", "VALIDATION.lengthMaxAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.lengthMaxAlert");
	if(COMMVAL.strLength(htmField.value) > maxLength){
     	alert("\""+htmTitle+"\""+"长度(一个中文/全角字符长度为2)不应大于："+maxLength+",请重新输入！");
     	return false
    }
    return true;
}
/**
*检查输入是否短于最小长度minLength
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*minLength 最小长度
*如果不满足给出提示
*/
VALIDATION.prototype.lengthMinAlert = function(htmField,htmTitle,minLength){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.lengthMinAlert");
	undefinedNullThrow(minLength, "minLength", "VALIDATION.lengthMinAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.lengthMinAlert");
	if(COMMVAL.strLength(htmField.value) < minLength){
     	alert("\""+htmTitle+"\""+"长度(一个中文/全角字符长度为2)不应小于："+minLength+",请重新输入！");
     	return false
    }
    return true;
}
/**
*检查输入长度是否是length
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*length 最小长度
*如果不满足给出提示
*/
VALIDATION.prototype.lengthEqualAlert = function(htmField,htmTitle,length){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.lengthEqualAlert");
	undefinedNullThrow(length, "length", "VALIDATION.addHtmField");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.addHtmField");
	if(COMMVAL.strLength(htmField.value) == length){
     	alert("\""+htmTitle+"\""+"长度(一个中文/全角字符长度为2)应为："+length+",请重新输入！");
     	return false
    }
    return true;		
}
//value	
/**
*检查输入int值是否大于 intMaxValue
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*intMaxValue 最小长度
*如果不满足给出提示
*/	
VALIDATION.prototype.intMaxAlert = function(htmField,htmTitle,intMaxValue){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.intMaxAlert");
	undefinedNullThrow(intMaxValue, "intMaxValue", "VALIDATION.intMaxAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.intMaxAlert");
	if(parseInt(htmField.value,10) > parseInt(intMaxValue,10)){
	    alert("\""+htmTitle+"\""+"应小于等于"+intMaxValue+",请重新输入！");
	    return false;
	}
	return true;
}
/**
*检查输入int值是否小于 intMinValue
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*intMaxValue 最小长度
*如果不满足给出提示
*/	
VALIDATION.prototype.intMinAlert = function(htmField,htmTitle,intMinValue){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.intMinAlert");
	undefinedNullThrow(intMinValue, "intMinValue", "VALIDATION.intMinAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.intMinAlert");
	
    if(parseInt(htmField.value,10) < parseInt(intMaxValue,10)){
	    alert("\""+htmTitle+"\""+"应大于等于"+intMinValue+",请重新输入！");
	    return false;
	}
	return true;

}
/**
*检查输入float值是否大于 floatMaxValue
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*floatMaxValue 最小值
*如果不满足给出提示
*/	
VALIDATION.prototype.floatMaxAlert = function(htmField,htmTitle,floatMaxValue){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.floatMaxAlert");
	undefinedNullThrow(floatMaxValue, "floatMaxValue", "VALIDATION.floatMaxAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.floatMaxAlert");
	if(parseFloat(htmField.value,10) > parseFloat(floatMaxValue,10)){
	    alert("\""+htmTitle+"\""+"应小于等于"+floatMaxValue+",请重新输入！");
	    return false;
	}
	return true;

}
/**
*检查输入float值是否小于 floatMinValue
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*floatMinValue 最小长度
*如果不满足给出提示
*/	
VALIDATION.prototype.floatMinAlert = function(htmField,htmTitle,floatMinValue){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.floatMinAlert");
	undefinedNullThrow(floatMinValue, "floatMinValue", "VALIDATION.floatMinAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.floatMinAlert");
	
	if(parseFloat(htmField.value,10) < parseFloat(floatMinValue,10)){
	    alert("\""+htmTitle+"\""+"应大于等于"+floatMinValue+",请重新输入！");
	    return false;
	}
	return true;

}
/**
*格式化浮点数
*num: 需要格式化得浮点数
*decNum：小数位数
*返回＃＃.### (小数点后decNum位)
*/
VALIDATION.prototype.formatFloat = function(num,decNum){
    undefinedNullThrow(decNum, "decNum", "VALIDATION.formatFloat");
    if(num == null||num == undefined || isNaN(num) || num == "") return num;
    var r = num.toString();
    if(r.indexOf(".") == -1) r = r + ".";
    var arr = r.split("\.");
    if (arr[1].length > decNum) arr[1] = arr[1].substring(0,decNum);
    else {
        while(arr[1].length < decNum){
            arr[1] = arr[1]+"0";
        }
    }
    return arr[0] + (decNum != 0?".":"")+arr[1];
}
//regExp
/**
*检查输入是否满足正则表达式
*htmField 需要检查得htmField
*htmTitle 需要检查得htmField 对应title
*regExp 正则表达式
*msg 提示信息
*如果不满足给出提示
*/	
VALIDATION.prototype.regMatchAlert = function(htmField,htmTitle,regExp,msg){
	undefinedNullThrow(htmField, "htmField", "VALIDATION.regMatchAlert");
	undefinedNullThrow(regExp, "regExp", "VALIDATION.regMatchAlert");
	undefinedNullThrow(msg, "msg", "VALIDATION.regMatchAlert");
	undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION.regMatchAlert");
	
	if(!regExp.test(htmField.value)){
	    alert("\""+htmTitle+"\""+msg);
	    return false;
	}
	return true;
}
//date
VALIDATION.prototype.dateAlert = function(){
}
/************************************************VALIDATION公用部分结束************************************/
/************************************************VALIDATIONFloat校验部分结束************************************/
/**
 * 添加一个需要校验的Float场，该方法写入js同名方法调用到网页以
 * @param htmFieldID Float场的ID属性
 * @param htmTitle  提示信息的标题
 * @param htmMaxLen 该场的maxlength属性
 * @param htmFieldSize 该场得size属性
 * @param canEmpty  该场是否允许提交空值
 * @param intNum 该场的整数部分最大长度
 * @param decNum 该场的小数部分最大长度
 * @param maxValStr 该场的最大值可输入null表示无最大值
 * @param minValStr 该场的最小值可输入null表示最小值
 * @param custCheckFun 需要调用的特殊js校验方法 在基本校验结束时调用 可以输入“null”表示无特殊校验
 */
 VALIDATION.prototype.addFloatField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun){
     this_val = this;
     //合法校验 该方法输入参数必须为htmField 在VALIDATION.addHtmField中将被调用
     var validate = function(htmField){
     	htmField.value = COMMVAL.strTrim(htmField.value);	
     	
     	//reg
     	var reg = new RegExp("^[-\+]?([1-9][\\d]{0," + (intNum - 1) + "}|0)\\.[\\d]{1," + decNum + "}$|^[-\+]?[1-9][\\d]{0," + (intNum - 1) + "}$|^0$");
     	if(!this_val.regMatchAlert(htmField,htmTitle,reg,"输入不合法，请重新输入！"))return false;
     	
        //最大值
        if(maxValStr != null && maxValStr != undefined){
         	if(!this_val.floatMaxAlert(htmField,htmTitle,maxValStr))
         	return false;
        }
     	
		//最小值
		if(minValStr != null && minValStr != undefined){
			if(!this_val.floatMinAlert(htmField,htmTitle,minValStr))
			return false;
		}			
		
		htmField.value = this_val.formatFloat(htmField.value,decNum);
		
     	if(custCheckFun instanceof Function) return custCheckFun(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun);
        return true;
     }
    //所有的添加校验域的函数必须最后一句这样调用
	 this.addHtmField(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty,validate,"addFloatField");
 }
/************************************************VALIDATIONFloat校验部分结束************************************/

/************************************************VALIDATIONInt校验部分结束************************************/
/**
 * 添加一个需要校验的Int场，该方法写入js同名方法调用到网页以
 * @param htmFieldID Int场的ID属性
 * @param htmTitle  提示信息的标题
 * @param htmMaxLen 该场的maxlength属性
 * @param htmFieldSize 该场得size属性
 * @param canEmpty  该场是否允许提交空值
 * @param intNum 该场的整数部分最大长度
 * @param decNum 该场的小数部分最大长度
 * @param maxValStr 该场的最大值 可输入null表示无上限
 * @param minValStr 该场的最小值 可输入null表示无下限
 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊校验
 */
 VALIDATION.prototype.addIntField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,maxValStr,minValStr,custCheckFun){
     this_val = this;
     //合法校验 该方法输入参数必须为htmField 在VALIDATION.addHtmField中将被调用
     var validate = function(htmField){
     	htmField.value = COMMVAL.strTrim(htmField.value);	
     	
     	//reg
     	var reg = new RegExp("^[-\+]?([1-9][\\d]+)|^0$");
     	if(!this_val.regMatchAlert(htmField,htmTitle,reg,"输入不合法，请重新输入！"))return false;
     	
        //最大值
        if(maxValStr != null && maxValStr != undefined){
         	if(!this_val.intMaxAlert(htmField,htmTitle,maxValStr))
         	return false;
        }
     	
		//最小值
		if(minValStr != null && minValStr != undefined){
			if(!this_val.intMinAlert(htmField,htmTitle,minValStr))
			return false;
		}			

     	if(custCheckFun instanceof Function) return custCheckFun(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun);
        return true;
     }
    //所有的添加校验域的函数必须最后一句这样调用
	 this.addHtmField(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty,validate,"addFloatField");
 }
/************************************************VALIDATIONInt校验部分结束************************************/

/************************************************VALIDATIONGeneral校验部分************************************/
/**
 * 添加一个需要校验的Float场，该方法写入js同名方法调用到网页以
 * @param htmFieldID Float场的ID属性
 * @param htmTitle  提示信息的标题
 * @param htmMaxLen 该场的maxlength属性
 * @param htmFieldSize 该场得size属性
 * @param canEmpty  该场是否允许提交空值
 * @param regExp 校验该场的正则表达式 可以输入null  表示不校验
 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
 * @param minLength 输入最小长度
 */
 VALIDATION.prototype.addGeneralField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun,minLength){
      this_val = this;
     //合法校验 该方法输入参数必须为htmField 在VALIDATION.addHtmField中将被调用
     var validate = function(htmField){
     	htmField.value = COMMVAL.strTrim(htmField.value);	
		
		if(htmMaxLen != null && htmMaxLen != undefined){
			if(!this_val.lengthMaxAlert(htmField,htmTitle,htmMaxLen))
         		return false;
		}     
		
		if(minLength != null && minLength != undefined){
			if(!this_val.lengthMinAlert(htmField,htmTitle,minLength))
         		return false;
		} 	

		if(regExp != null && regExp != undefined){
			if(!this_val.regMatchAlert(htmField,htmTitle,regExp,"输入不合法,请重新输入！"))
         		return false;
		} 

      	if(custCheckFun instanceof Function) return custCheckFun(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun);
        return true;
     }
    //所有的添加校验域的函数必须最后一句这样调用
	 this.addHtmField(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty,validate,"addGeneralField");
 }
 /************************************************VALIDATIONGeneral校验部分结束************************************/
 
 
 var VALIDATION = VALIDATION;
/************************************************VALIDATIONGeneral校验部分结束************************************/
