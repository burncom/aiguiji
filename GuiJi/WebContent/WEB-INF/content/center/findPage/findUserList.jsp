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
	<%--查找用户 --%>
	<s:form action="findUsers">
	<table width="780" align="center" border="1">
	<tr>
		<td colspan="2"><s:textfield name="findUserName" /> </td>
	</tr>
	<tr>
		<td><s:submit value="查询" theme="simple" /></td>
	</tr>
	</table>
	</s:form>
	
	<s:iterator value="findUsersBean">
	<table width="780" align="center" border="1" id="finduserList_table">
	<tr>
		<th rowspan="3"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
		<img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /></a></th>
		<td>
			<a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>
			<s:if test="%{status==1}">
				<a id="idfans1" href="statusUUR.action?userId=<s:property value="userId"/>&status=1&statusValue=1" >关注</a>
			</s:if>
			<s:elseif test="%{status==2}">
				<a id="idstatuscontent">粉丝</a>&nbsp;&nbsp;
				<a id="idfans1" href="statusUUR.action?userId=<s:property value="userId"/>&status=2&statusValue=2" >关注</a>&nbsp;&nbsp;
				<a id="idfans2" href="statusUUR.action?userId=<s:property value="userId"/>&status=2&statusValue=3" >移除粉丝</a>
			</s:elseif>
			<s:elseif test="%{status==3}">
				<a id="idstatuscontent">已关注</a>&nbsp;&nbsp;
				<a id="idfans1" href="statusUUR.action?userId=<s:property value="userId"/>&status=3&statusValue=4" >取消关注</a>&nbsp;&nbsp;
			</s:elseif>
			<s:elseif test="%{status==4}">
				<a id="idstatuscontent">互相关注</a>&nbsp;&nbsp;
				<a id="idfans1" href="statusUUR.action?userId=<s:property value="userId"/>&status=4&statusValue=5" >取消关注</a>&nbsp;&nbsp;
				<a id="idfans2" href="statusUUR.action?userId=<s:property value="userId"/>&status=4&statusValue=6" >移除粉丝</a>
			</s:elseif>
		</td>
	</tr>
	<tr>
		<td><a id="idplace">居住地：<s:property value="place"/></a></td>
	</tr>
	<tr>
		<td><a id="idhometown">家乡：<s:property value="hometown"/></a></td>
	</tr>
	</table>
	</s:iterator>
	
</body>
</html>
