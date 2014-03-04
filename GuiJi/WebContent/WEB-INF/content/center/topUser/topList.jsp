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
	</table>
	<%--排行榜 --%>
	<table width="780" align="center" border="1">
	<tr>
		<td>
			<a href="topList">分类排行榜</a>&nbsp;&nbsp;&nbsp;&nbsp;
			
		</td>
	</tr>
	<s:iterator value="topListBean">
		<tr>
			<td>
				<a id="idtoplist" href="classUserList.action?type=<s:property value="type"/>" >
				<s:property value="typeName"/> (排行值<s:property value="count"/>)
				</a>
			</td>
		</tr>
	</s:iterator>
	</table>
</body>
</html>
