
<%@page import="validate.Validate"%>
<%@page import="validate.htmField.*"%>
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<META http-equiv="Content-Type" content="text/html; charset=GB18030">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>index.jsp</TITLE>
</HEAD>
<BODY>
<FORM id="subForm">
���<INPUT type="text" id="money" name = "money">addFloatField("money","���",18,19,false,15,2,"100.09","0.25","null")<br>
���1<INPUT type="text" id="money1" name = "money">addFloatField("money1","���1",8,9,true,5,2,"10.09","0.25","null");<br>
email<INPUT type="text" id="email" name = "email">addGeneralField("email","email",100,40,true,"/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/","null");
<br>
<input type="submit" value ="�ύ">
</FORM>
<P>�ڴ˴��������ݡ�</P>
</BODY>
<script language="javascript" type="text/javascript" src="public/commValidation.js"></script>
<script language="javascript" type="text/javascript" src="public/Validation.js"></script>
<%
Validate val = new Validate();
//String[] moneys = {"money","money1"};
//String[] titles = {"���","���1"};
//Val.addMoneyField(moneys,titles,18,19,false,15,2,"100.09","0.25","null");
val.addHtmField(new FloatField("money","���",18,19,false,15,2,"100.09","0.25","null"));
val.addHtmField(new FloatField("money1","���1",8,9,true,5,2,"10.09","0.25","null"));
val.addHtmField(new GeneralField("email","email",100,40,true,"/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/","null"));
val.writeJs(out,"subForm");
 %>
</HTML>
