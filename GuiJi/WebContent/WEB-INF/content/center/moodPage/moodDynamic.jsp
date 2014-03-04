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
				<%--插入新鲜地点~实时 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="myHome">动态</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--心情 --%>
				<a href="moodDynamic">心情</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--相册 --%>
				<a href="photos">相册</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<%--插入心情动态 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="moodDynamic.action?moodType=1">关注者</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--插入心情动态 --%>
				<a href="moodDynamic.action?moodType=2">同城</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
		<%--插入动态 心情 显示内容--%>
		<s:iterator value="moodBean">
		<table id="idmooddynamic" width="780" align="center" border="1">
			<tr>
			<s:if test="%{moodPic!=null && mood!= 0}">
			<th rowspan="4" ><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a></th>
			</s:if>
			<s:elseif test="%{moodPic==null && mood== 0}">
			<th rowspan="2"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a> </th>
			</s:elseif>
			<s:else>
			<th rowspan="3"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a></th>	
			</s:else>
			<td><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property  value="userName"/></a>:
			<a id="idmsgcontent"><s:property value="moodContent"/></a></td>
			</tr>
		<s:if test="%{mood!= 0 }">
		<tr>
			<td>
				<a id="idmood">心情指数为：<s:property value="mood"/></a>
			</td>
		</tr>
		</s:if>
		<s:else>
		<tr>
			<td>
				<a id="idmood">还没发表心情</a>
			</td>
		</tr>
		</s:else>
		<s:if test="%{moodPic!=null}">
		<tr>
			<td><a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="moodPic"/>" >
				<img width="80" src="<%=request.getContextPath()%><s:property value="moodPic"/>" /> </a>
			</td>
		</tr>
		</s:if>
		<tr>
			<td>
				<a id="idtime"><s:property value="time"/></a>&nbsp;
				<a id="idplace"><s:property value="place"/></a>&nbsp;&nbsp;&nbsp;
				<a id="idpictureset" href="pictureset.action?type=5&msgId=<s:property value="msgId"/>" >图片集(<s:property value="pictureCount"/>)</a>&nbsp;
				<s:if test="#session.user==userName " >
				<a id="iddeleteMood" href="deleteMood.action?msgId=<s:property value="msgId"/>">删除</a>&nbsp;&nbsp;
				</s:if>
				<a id="idcommentMood" href="commentMood?msgId=<s:property value="msgId"/>">评论(<s:property value="commentCount"/>)</a>
			</td>
		</tr>
		</table>
		</s:iterator>
	
		<%@include file="../homeFooter.jsp"%>
</body>
</html>
