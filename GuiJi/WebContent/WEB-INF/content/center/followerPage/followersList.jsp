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
	<s:iterator value="followerFansBean">
	<table width="780" align="center" border="1">
	
	<s:if test="%{type==1}">
	<tr>
			<th rowspan="2">
				<a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" />
				</a>
			</th>
			<td>
				<a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName" /></a>&nbsp;&nbsp;&nbsp;
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
	<tr>
		<td>
			<s:if test="%{mood==0}">
				<a id="idmood">还没有发表心情</a>
			</s:if>
			<s:else>
				<a id="idmood">心情指数：<s:property value="mood"/></a>
			</s:else>
		</td>
	</tr>
	</s:if>
	<s:if test="%{type==2}">
		<tr>
			<th rowspan="5">
				<a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" />
				</a>
			</th>
			<td>
				<a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName" /></a>&nbsp;&nbsp;&nbsp;
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
	<tr>
		<td>
			<s:if test="%{mood==0}">
				<a id="idmood">还没有发表心情</a>
			</s:if>
			<s:else>
				<a id="idmood">心情指数：<s:property value="mood"/></a>
			</s:else>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idmsgcontent"><s:property value="msg_content"/></a>
		</td>
	</tr>
	<tr>
		<td><a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="picture"/>" >
			<img width="80" src="<%=request.getContextPath()%><s:property value="picture" />" /></a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idtime"><s:property value="time"/></a>&nbsp;&nbsp;<a id="idplace"><s:property value="place"/></a>&nbsp;&nbsp;
		</td>
	</tr>
	</s:if>	
	<s:if test="%{type==3}">
		<tr>
			<th rowspan="5">
				<a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" />
				</a>
			</th>
			<td>
				<a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName" /></a>&nbsp;&nbsp;&nbsp;
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
	<tr>
		<td>
			<s:if test="%{mood==0}">
				<a id="idmood">还没有发表心情</a>
			</s:if>
			<s:else>
				<a id="idmood">心情指数：<s:property value="mood"/></a>
			</s:else>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idmsgcontent"><s:property value="msg_content"/></a>
		</td>
	</tr>
	<tr>
		<td><a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="picture"/>" >
			<img width="80" src="<%=request.getContextPath()%><s:property value="picture" />" /></a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="idtime">活动起止时间：<s:property value="start_time"/>--<s:property value="end_time"/></a>&nbsp;&nbsp;
			<a id="idplace"><s:property value="place"/></a>&nbsp;&nbsp;
		</td>
	</tr>
	</s:if>
	
	</table>
	</s:iterator>
</body>
</html>
