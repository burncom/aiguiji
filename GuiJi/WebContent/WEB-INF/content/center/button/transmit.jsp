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
	<%--处理转发 --%>
	<s:form action="transmit">
	<table id="idtransmit" width=780 align="center" border="1">
		
		<s:hidden name="msgId" value='%{msgId}'></s:hidden>
		<s:hidden name="firstMsgId" value='%{firstMsgId}'></s:hidden>
		<s:hidden name="type" value='%{type}'></s:hidden>
		<s:if test='%{type=="1" || type=="2"}'>
			<tr>
				<th rowspan="6"><a href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
				<td >
					<s:if test="%{transmitBean.transmitUserName!=null}">
					<%--对于是转发 的帖子原先也是转发，则要加入原转发的用户名和信息内容 --%>
					<s:textfield name="msgContent" theme="simple" value="%{transmitBean.transmitUserName}:%{transmitBean.transmitUserName}" />
					</s:if>
					<s:else>
					<s:textfield name="msgContent" theme="simple" value="转自%{transmitBean.orignalUserName} "/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td><a href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
				<s:property value="transmitBean.orignalUserName"/></a></td>
			</tr>
			<tr>
				<td><s:property value="transmitBean.orignalMsgContent"/></td>
			</tr>
			<tr>
				<td><a href="toBigPicture.action?picture=<s:property  value="transmitBean.orignalMsgPicture"/>" >
				<img width="80" src="<%=request.getContextPath()%><s:property value="transmitBean.orignalMsgPicture" />" /></a>
				</td>
			</tr>
			<tr>
				<td>
				<s:if test="%{transmitBean.transmitUserName==null}">
					<s:checkbox name="comment"  theme="simple"/>同时评论给<a id="idcomment" href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
					<s:property value="transmitBean.orignalUserName"/></a>
				</s:if>
				<s:else>
					<s:checkbox name="comment"  theme="simple"/>同时评论给<a id="idcomment" href="user.action?userId=<s:property value="transmitBean.transmitUserId"/>" >
					<s:property value="transmitBean.transmitUserName"/></a>
					<p/>
					<s:checkbox name="orignalComment"  theme="simple"/>同时评论给原文作者<a id="idorignalComment" href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
					<s:property value="transmitBean.orignalUserName"/></a>
				</s:else>
				</td>
			</tr>
			<tr>
				<td><s:submit value="转发" theme="simple" /></td>
			</tr>
		</s:if>	
		<s:if test='%{type=="3" || type=="4"}'>
			<tr>
				<th rowspan="8"><a href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
				<td >
					<s:if test="%{transmitBean.transmitUserName!=null}">
					<%--对于是转发 的帖子原先也是转发，则要加入原转发的用户名和信息内容 --%>
					<s:textfield name="msgContent" theme="simple" value="%{transmitBean.transmitUserName}:%{transmitBean.transmitUserName}" />
					</s:if>
					<s:else>
					<s:textfield name="msgContent" theme="simple" value="转自%{transmitBean.orignalUserName} "/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td><a href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
				<s:property value="transmitBean.orignalUserName"/></a></td>
			</tr>
			<tr>
				<td><s:property value="transmitBean.orignalMsgContent"/></td>
			</tr>
			<tr>
				<td><a href="toBigPicture.action?picture=<s:property  value="transmitBean.orignalMsgPicture"/>" >
				<img width="80" src="<%=request.getContextPath()%><s:property value="transmitBean.orignalMsgPicture" />" /></a>
				</td>
			</tr>
			<tr>
				<td>活动起止时间：<s:property value="transmitBean.orignalActStartTime"/>&nbsp;
						     <s:property value="transmitBean.orignalActEndTime"/>&nbsp;&nbsp;&nbsp;
				    地点：<s:property value="transmitBean.orignalActPlace"/>
				</td>
			</tr>
			<tr>
				<td>活动发起人：<s:property value="transmitBean.leaderName"/></td>
			</tr>
			<tr>
				<td>
				<s:if test="%{transmitBean.transmitUserName==null }">
					<s:checkbox name="comment"  theme="simple"/>同时评论给<a id="idcomment" href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
					<s:property value="transmitBean.orignalUserName"/></a>
				</s:if>
				<s:else>
					<s:checkbox name="comment"  theme="simple"/>同时评论给<a id="idcomment" href="user.action?userId=<s:property value="transmitBean.transmitUserId"/>" >
					<s:property value="transmitBean.transmitUserName"/></a>
					<p/>
					<s:checkbox name="orignalComment"  theme="simple"/>同时评论给原文作者<a id="idorignalComment" href="user.action?userId=<s:property value="transmitBean.orignalUserId"/>" >
					<s:property value="transmitBean.orignalUserName"/></a>
				</s:else>
				</td>
			</tr>
			<tr>
				<td><s:submit value="转发" theme="simple" /></td>
			</tr>
		</s:if>
		
	</table>
	</s:form>
</body>
</html>
