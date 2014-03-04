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
	<%--排行榜 --%>
	<table width="780" align="center" border="1">
	<tr>
		<td colspan="2">
			<s:property value="typeName"/> Top20用户排行榜
		</td>
	</tr>
	</table>
	<s:iterator value="topUserBean">
	<table id="idusertoplist" width="780" align="center" border="1">
	<tr>
		<s:if test="%{mood!=0}">
		<th  rowspan="6"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>"><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /></a></th>
		</s:if>
		<s:else>
		<th  rowspan="2"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>"><img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /></a></th>
		</s:else>
		<td><a id="idusername" href="user.action?userId=<s:property value="userId"/>"><s:property value="userName"/></a>&nbsp;&nbsp;
		<a id="idhuoyuevalue"><s:property value="count"/></a>
			<s:if test="#session.user!=userName " >
			
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
			
			</s:if>
		</td>
	</tr>
	<s:if test="%{mood!=0}">
	<tr>
		<td>
			<a id="idmood">心情指数:<s:property value="mood"/></a>
		</td>
	</tr>
	<tr>
		<td><a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="moodPic"/>" >
			<img width="80" src="<%=request.getContextPath()%><s:property value="moodPic"/>" /></a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idmsgcontent"><s:property value="moodContent"/></a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idtime"><s:property value="time"/></a>&nbsp;&nbsp;<a id="idplace"><s:property value="place"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<a id="idcomment" href="commentMood?msgId=<s:property value="msgId"/>">评论(<s:property value="commentCount"/>)</a>
		</td>
	</tr>
	</s:if>
	<s:else>
	<tr>
		<td>
			<a id="idmood">还没有发表心情</a>
		</td>
	</tr>
	</s:else>
	</table>
	</s:iterator>
	
</body>
</html>
