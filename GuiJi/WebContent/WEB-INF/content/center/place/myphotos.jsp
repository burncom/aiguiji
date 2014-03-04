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
	<%--用户相册 --%>
	<table width=780 align="center" border="1" >
	<tr>
		<td><a href="upPicture">上传图片</a>
		</td>
	</tr>
	<tr>
		<td>我的相册</td>
	</tr>
	</table>
	<s:iterator value="photos">
	<table id="idmyphotos" width=780 align="center" border="1" >
	<tr>
		<td><a id="idphoto" href="toBigPicture.action?picture=<s:property  value="photo"/>" >
		<img width="80" src="<%=request.getContextPath()%><s:property  value="photo"/>" /></a>
		</td>
		<s:if test="#session.user==userName " >
		<td>
		<a id="iddeletePhoto" href="deletePhoto.action?photoId=<s:property  value="photoId"/>">删除</a>
		</td>
		</s:if>
	</tr>
	</table>
	</s:iterator>
	
</body>
</html>
