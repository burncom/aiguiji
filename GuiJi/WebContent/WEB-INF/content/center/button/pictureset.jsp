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
	<%--动态显示所有评论的图片 --%>
	<s:iterator value="pictureset">
	<table  id="idmyphotos" width="780" align="center" border="1">
	
		<tr>
			<td><a id="idphoto" href="toBigPicture.action?picture=<s:property  value="picture"/>" >
			<img width="80" src="<%=request.getContextPath()%><s:property value="picture" />" /></a>
			</td>
		</tr>
		<tr>
			<td><s:property value="time" />&nbsp;<s:property value="place" />&nbsp;&nbsp;&nbsp;<a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName" /></a></td>
		</tr>
	</table>
	</s:iterator>
	
</body>
</html>
