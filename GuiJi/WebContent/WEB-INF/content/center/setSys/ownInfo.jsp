<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页---轨迹</title>
</head>
<body>
	<%--处理想参加的事情 --%>
		<s:form action="setupOwnInfo">
		<table width=780 align="center" border="1">
		<tr>
			<td>
				<s:textfield name="birthday" label="生日" />
			</td>
		</tr>
		<tr>
			<td>
				<s:textfield name="userInfo.hometown" label="家乡" />
			</td>
		</tr>
		<tr>
			<td>
				居住地<s:textfield name="userInfo.province" label="省" />
				<s:textfield name="userInfo.city" label="市" />
				<s:textfield name="userInfo.district" label="县/区" />
				<s:textfield name="userInfo.other" label="其他" />
			</td>
		</tr>
		<tr>
			<td>
				<s:submit value="保存" theme="simple"/>
			</td>
		</tr>
		</table>
		</s:form>
</body>
</html>
