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
	<table width="780" align="center" border="1">
		<tr >
			<th width="150" rowspan="100">
				<%@include file="../homeLeft.jsp" %>
			</th>
		</tr>
		<tr>
			<td colspan="2">
				<%--动态显示 实时天气 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="realWeather">实时天气</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--发布天气 --%>
				<a href="publishWeather">发布天气 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<%--插入动态显示内容--%>
				<s:iterator value="realWeather">
				<table width="780" id="idrealweather" align="center" border="1">
				<tr>
					<th rowspan="3"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
					<td><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>:&nbsp;
					<a id="idwscene"><s:property value="wscene"/></a>&nbsp;&nbsp;
					<a id="idwtemp"><s:property value="wtemp"/></a>&nbsp;&nbsp;
					<a id="idwwind"><s:property value="wwind"/></a></td>
				</tr>
				<tr>
					<td><a id="idtime"><s:property value="time"/></a>&nbsp;&nbsp;
					<a id="idplace"><s:property value="place"/></a></td>
				</tr>
				<tr>
					<td><a id="idapprove" href="approve.action?approve=yes&weatherId=<s:property value="weatherId" />">赞成(<s:property value="agreeAmount"/>)</a>&nbsp;&nbsp;
					<a id="iddisapprove" href="approve.action?approve=no&weatherId=<s:property value="weatherId" />">不赞成(<s:property value="disagreeAmount"/>)</a></td>
				</tr>
				</table>
				</s:iterator>			
		<%@include file="../homeFooter.jsp"%>
</body>
</html>
