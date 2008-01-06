var VALIDATION = new VALIDATION_class();
function VALIDATION_class(formID){
/************************************************VALIDATION公用部分************************************/
     /**
     *将text field得光标设置到最后
     *@t textField 必须输入
     */
     this.setSelToEnd = function (t){
 	    var range = t.createTextRange();
  	    range.move("textedit");
  	    range.select();
  	 };    
     var SUB_CHECK_FIELD_ARR = new Array();     
     this.SUB_CHECK_FIELD_ARR = SUB_CHECK_FIELD_ARR;
     
     	
	/**
	*设置哪一个需要提交前校验的form
	*@formID 必须输入
	*/
	this.setSubCheckForm = function(formID){
		undefinedNullThrow(formID,"formID","VALIDATION_class.setSubCheckForm");
		var form = document.getElementById(formID);
		if(form.tagName.toUpperCase() != "FORM" ){
			alert("VALIDATION.setSubCheckForm 的参数必须输入为FORM id");
			return false;
		}
		var oldsub = form.onsubmit;
		form.onsubmit = function(){
			if(VALIDATION.subCheck()){
			//做<form ...中的onsubmit校验
				if(oldsub !== null && oldsub instanceof Function){
					return oldsub();
				}else return true;
			}else return false;		
		}				
	}

    /**
    *提交前的校验
    */
	this.subCheck = function(){
		var index;   
		for(index in SUB_CHECK_FIELD_ARR){
			index = SUB_CHECK_FIELD_ARR[index];
			var item = document.getElementById(index);
			if(item.subCheck == undefined || item.subCheck == null){
			    alertAndThrow("公共函数VALIDATION_class.subCheck处 出错！请联系公共函数管理员！"+item.subCheck);
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
	* @param htmMaxLen 该场的maxlength属性 必须输入
	* @param htmFieldSize 该场得size属性 必须输入
	* @param canEmpty  该场是否允许提交空值 必须输入
	* @param validateFun 子函数(比如:addFloatField)的校验函数 必须输入
	* @param chieldFunName 子函数名 比如：“addFloatField”
	*/
	this.addHtmField = function(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty, validateFun,chieldFunName) {
		undefinedNullThrow(htmFieldID, "htmFieldID", "VALIDATION_class.addHtmField");
		undefinedNullThrow(htmTitle, "htmTitle", "VALIDATION_class.addHtmField");
		undefinedNullThrow(htmMaxLen, "htmMaxLen", "VALIDATION_class.addHtmField");
		undefinedNullThrow(htmFieldSize, "htmFieldSize", "VALIDATION_class.addHtmField");
		undefinedNullThrow(canEmpty, "canEmpty", "VALIDATION_class.addHtmField");
		//undefinedNullThrow(custCheckFun, "custCheckFun", "VALIDATION_class.addHtmField");//可以为null
        VALIDATION.SUB_CHECK_FIELD_ARR[htmFieldID]=htmFieldID;
        
        var htmField = document.getElementById(htmFieldID);
        if(htmField == null){
           alertAndThrow("找不到ID为"+htmFieldID+"的输入域 请处理");
           return false;
        }
        if(!htmField.type.toUpperCase () =="TEXT"){
           alertAndThrow("ID为"+htmFieldID+"的不是输入域 请处理");
           return false;
        }
		htmField.maxLength = htmMaxLen;
        htmField.size = htmFieldSize;
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
				r = r && pageBlur(); 
			} 
            
			//防止不输入时死循环不输入不校验
			if(htmField.value == "")return true;
			//如果该输入场是只读的 则不需要校验
			if(htmField.readOnly === true)return true;
                
			if(r) r = r && htmField.validate();   
			          
			if(r == false) VALIDATION.setSelToEnd(htmField);   
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
			//实际上在输入时限制了一定输入合法内容，所以 subCheck 之需要校验是否为空就好了   
			//if(r) r = r && htmField.validate();             
			if(r == false) VALIDATION.setSelToEnd(htmField);   
			return r;       
        }  	
	};
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
	 * @param maxValStr 该场的最大值 可输入null表示无上限
	 * @param minValStr 该场的最小值 可输入null表示无下限
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
	 */
     this.addFloatField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun){
         //合法校验 该方法输入参数必须为htmField 在VALIDATION.addHtmField中将被调用
         var validate = function(htmField){
         	htmField.value = COMMVAL.strTrim(htmField.value);	
         	if(!COMMVAL.valFloat(htmField.value,intNum,decNum,maxValStr,minValStr)){
                 alert("\""+htmTitle+"\""+"应输入整数部分"+intNum+"位以内小数部分"+decNum+"位以内的浮点数小数，请重新输入！");
                 return false;
            };
            //最大值
         	if (maxValStr !== undefined && maxValStr !== null && parseFloat(htmField.value) > parseFloat(maxValStr)) {
				alert("\""+htmTitle+"\""+"不应大于"+maxValStr+"，请重新输入！");
				return false;
			}
			//最小值
			if (minValStr !== undefined && minValStr !== null && parseFloat(htmField.value) < parseFloat(minValStr)) {
				alert("\""+htmTitle+"\""+"不应小于"+minValStr+"，请重新输入！");
				return false;
			}
         	if(custCheckFun instanceof Function) return custCheckFun(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,intNum,decNum,maxValStr,minValStr,custCheckFun);
            return true;
         }
        //所有的添加校验域的函数必须最后一句这样调用
		 VALIDATION.addHtmField(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty,validate,"addFloatField");
     }
/************************************************VALIDATIONFloat校验部分结束************************************/
/************************************************VALIDATIONGeneral校验部分结束************************************/
	/**
	 * 添加一个需要校验的Float场，该方法写入js同名方法调用到网页以
	 * @param htmFieldID Float场的ID属性
	 * @param htmTitle  提示信息的标题
	 * @param htmMaxLen 该场的maxlength属性
	 * @param htmFieldSize 该场得size属性
	 * @param canEmpty  该场是否允许提交空值
	 * @param regExp 校验该场的正则表达式 可以输入null  表示不校验
	 * @param custCheckFun 需要调用的特殊js校验方法名 在基本校验结束时调用 可以输入“null”表示无特殊教研
	 */
     this.addGeneralField = function(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun){
         //合法校验 该方法输入参数必须为htmField 在VALIDATION.addHtmField中将被调用
         var validate = function(htmField){
         	htmField.value = COMMVAL.strTrim(htmField.value);	
         	if(COMMVAL.strLength(htmField.value) > htmMaxLen){
         		alert("\""+htmTitle+"\""+"超过最大长度："+htmMaxLen+",请重新输入！");
         	}
         	if(!COMMVAL.valGeneral(htmField.value,regExp)){
                 alert("\""+htmTitle+"\""+"输入不合法,请重新输入！");
                 return false;
            };
          	if(custCheckFun instanceof Function) return custCheckFun(htmFieldID,htmTitle,htmMaxLen,htmFieldSize,canEmpty,regExp,custCheckFun);
            return true;
         }
        //所有的添加校验域的函数必须最后一句这样调用
		 VALIDATION.addHtmField(htmFieldID, htmTitle, htmMaxLen, htmFieldSize, canEmpty,validate,"addGeneralField");
     }
/************************************************VALIDATIONGeneral校验部分结束************************************/
if(formID != undefined)this.setSubCheckForm(formID);
}
