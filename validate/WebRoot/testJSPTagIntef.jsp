<%@ taglib uri="http://code.google.com/p/page-validation" prefix="val"%>

<HTML>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
		<META http-equiv="Content-Type" content="text/html; charset=GB18030">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE>index.jsp</TITLE>
		<script language="javascript" type="text/javascript" src="public/commValidation.js"></script>
		<script language="javascript" type="text/javascript" src="public/Validation.js"></script>

	</HEAD>
	<BODY>
		<val:Form action="index.jsp">
			<% 
			String test = "345";
		%>
			
			${test }
			���${System.out }
			<val:FloatText id="money1" decNum="2" intNum="3" maxValue="1000" minValue="<%=test %>" name="testFloat"
				title="���" maxlength="19" size="20" />
				 val:FloatText id="money1" decNum="2" intNum="3" maxValue="1000" minValue="<%=test %>" name="testFloat"
				title="���" maxlength="19" size="20" />
			<br>
			���1
			<val:FloatText decNum="2" intNum="3" maxValue="1000" minValue="<%=test %>" name="testFloat" title="���1"
				maxlength="19" size="20" />
				 val:FloatText decNum="2" intNum="3" maxValue="1000" minValue="<%=test %>" name="testFloat" title="���1"
				maxlength="19" size="20" />
			<br>
			
			<br>
			<input type="submit" value="�ύ">
		</val:Form>
		<P>
			�ڴ˴��������ݡ�
		</P>
	</BODY>
</HTML>
