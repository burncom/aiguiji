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
	<%--用户个人主页开头 --%>
	<table width="780" align="center" border="1">
	<s:iterator value="findUsersBean">
	<tr>
		<th rowspan="3"><a href="user.action?userId=<s:property value="userId"/>" >
		<img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /></a></th>
		<td>
			<a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>
			<s:if test="%{status==1}">
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=1&statusValue=1" >关注</a>
			</s:if>
			<s:elseif test="%{status==2}">
				粉丝&nbsp;&nbsp;
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=2&statusValue=2" >关注</a>&nbsp;&nbsp;
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=2&statusValue=3" >移除粉丝</a>
			</s:elseif>
			<s:elseif test="%{status==3}">
				已关注&nbsp;&nbsp;
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=3&statusValue=4" >取消关注</a>&nbsp;&nbsp;
			</s:elseif>
			<s:elseif test="%{status==4}">
				互相关注&nbsp;&nbsp;
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=4&statusValue=5" >取消关注</a>&nbsp;&nbsp;
				<a href="statusUUR.action?userId=<s:property value="userId"/>&status=4&statusValue=6" >移除粉丝</a>
			</s:elseif>
		</td>
	</tr>
	<tr>
		<td>居住地：<s:property value="place"/></td>
	</tr>
	<tr>
		<td>家乡：<s:property value="hometown"/></td>
	</tr>
	</s:iterator>
	</table>
	
	<%--用户相册 --%>
	<table width=780 align="center" border="1" >
	<tr>
		<td>相册
		</td>
	</tr>
	<s:iterator value="photosBean">
	<tr>
		<td>
		<a href="toBigPicture.action?picture=<s:property  value="photo"/>" >
		<img width="80" src="<%=request.getContextPath()%><s:property  value="photo"/>" /></a>
		</td>
		<%-- 
		<s:if test="#session.user==userName " >
		<td>
		<a href="deletePhoto.action?photoId=<s:property  value="photoId"/>">删除</a>
		</td>
		</s:if>
		 --%>
	</tr>
	</s:iterator>
	</table>
	
	<%--用户新鲜地点和心情 --%>
	<table width=780 align="center" border="1" >
		<tr>
			<td>
				<a href="user.action?userId=<s:property value="findUsersBean.userId"/>">新鲜地点</a>
				<a href="userMood.action?userId=<s:property value="findUsersBean.userId"/>">心情</a>
			</td>
		</tr>
	</table>
	<%--用户心情 --%>
	<table width=780 align="center" border="1" >
	<%--插入动态 心情 显示内容--%>
		<s:iterator value="moodBean">
			<tr>
			<s:if test="%{moodPic!=null && mood!= 0}">
			<th rowspan="4" ><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a></th>
			</s:if>
			<s:elseif test="%{moodPic==null && mood== 0}">
			<th rowspan="2"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a> </th>
			</s:elseif>
			<s:else>
			<th rowspan="3"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /> </a></th>	
			</s:else>
			<td><a href="user.action?userId=<s:property value="userId"/>" ><s:property  value="userName"/></a>:<s:property value="moodContent"/></td>
			</tr>
		<s:if test="%{mood!= 0 }">
		<tr>
			<td>
				心情指数为：<s:property value="mood"/>
			</td>
		</tr>
		</s:if>
		<s:if test="%{moodPic!=null}">
		<tr>
			<td><a href="toBigPicture.action?picture=<s:property  value="moodPic"/>" >
				<img width="80" src="<%=request.getContextPath()%><s:property value="moodPic"/>" /> </a>
			</td>
		</tr>
		</s:if>
		<tr>
			<td>
				<s:property value="time"/>&nbsp;
				<s:property value="place"/>&nbsp;&nbsp;&nbsp;
				<a href='pictureset.action?type=5&msgId=<s:property value="msgId"/>' >图片集(<s:property value="pictureCount"/>)</a>&nbsp;
				<s:if test="#session.user==userName " >
				<a href="deleteMood.action?msgId=<s:property value="msgId"/>">删除</a>&nbsp;&nbsp;
				</s:if>
				<a href="commentMood?msgId=<s:property value="msgId"/>">评论(<s:property value="commentCount"/>)</a>
			</td>
		</tr>
		</s:iterator>
	</table>
</body>
</html>
