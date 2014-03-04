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
	<table width="780" align="center" border="1">
	<tr>
		<td align="center">
			<img src="${pageContext.request.contextPath}/images/guiji.gif" width="200" height="66">
		</td>
	</tr>
	<tr>
		<td>
			<a href="setup">设置</a>
		</td>
	</tr>
	</table>
	<%--处理想参加的事情 --%>
	<table width=780 align="center" border="1">
	<tr>
		<td><a href="setAvator">头像</a></td>
	</tr>
	<tr>
		<td><a href="setInterest">感兴趣地点</a></td>
	</tr>
	<tr>
		<td><a href="setEmail">注册邮箱</a></td>
	</tr>
	<tr>
		<td><a href="setOwnInfo">个人信息</a></td>
	</tr>
	<tr>
		<td><a href="setFeedback">意见反馈</a></td>
	</tr>
	<tr>
		<td><a href="logout">退出</a></td>
	</tr>
	<s:if test="tip!=null">
	<tr>
		<td><s:property  value="tip"/></td>
	</tr>
	</s:if>
	</table>
</body>
</html>
