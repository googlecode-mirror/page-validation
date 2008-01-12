<%@ taglib uri="http://code.google.com/p/page-validation" prefix="v"%>

<v:Html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=GB18030"
			pageEncoding="GB18030"%>
		<META http-equiv="Content-Type" content="text/html; charset=GB18030">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE>index.jsp</TITLE>
	</HEAD>
	<%
		String test = "345";
	%>

	<v:Body jsPath="public/">
		<v:Form action="index.jsp" id="abcd">
			<table>
				<tr>
					<td>
						金额
						<v:Money17 width="50%" name="money17" title="money17"
							maxlength="10" value="<%=test%>"></v:Money17>
						&lt; v:Money17 name="money17" title="money17" maxlength="10"
						value="<%=test%>"&gt;
					</td>
				</tr>
				<tr>
					<td>
						金额1
						<v:Money17 name="money1" title="金额1" minValue="null"></v:Money17>
						&lt;v:Money17 name="money1" title="金额1" &gt;
					</td>
				</tr>
				<tr>
					<td>
						Email
						<v:Email name="Email" title="Email"></v:Email>
						<v:Email name="Email" title="Email"></v:Email>
					</td>
				</tr>

				<tr>
					<td>
						Name
						<v:Name name="name" title="Name"></v:Name>
						&lt;v:Name name="name" title="Name"&gt;
					</td>
				</tr>

				<tr>
					<td>
						<input type="submit" value="提交">
					</td>
				</tr>
			</table>
		</v:Form>
		<P>
			12345
		</P>
	</v:Body>
</v:Html>
fldskfkdjshfkdsjh fjldkshfkdsjhf
