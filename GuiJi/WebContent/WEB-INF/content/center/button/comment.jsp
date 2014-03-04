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
	<s:form action="comment" enctype="multipart/form-data" method="post">
	<table width=780 align="center" border="1">
		<s:hidden name="msgId" value='%{msgId}'></s:hidden>
		<s:hidden name="firstMsgId" value='%{firstMsgId}'></s:hidden>
		<s:hidden name="type" value='%{type}'></s:hidden>
		<tr>
			<td colspan="2"><s:textfield name="messageContent" theme="simple"/></td>
		</tr>
		<tr>
			<td colspan="2">
			<s:textfield name="coordinate" label="坐标" />
			<p/>
			<s:file name="picture" label="上传图片" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<s:if test="%{orignalUserName!=null}" >
				<s:checkbox name="comment"  theme="simple"/>同时评论给<s:property value="orignalUserName"/>
			</s:if>
			<s:checkbox name="transmit"  theme="simple"/>同时转发给自己
			</td>
		</tr>
		<tr>
			<td colspan="2"><s:submit value="评论" theme="simple" /></td>
		</tr>
		
	</table>
	</s:form>
	
	<s:iterator value="commentBean">
	<table id="idcomment" width=780 align="center" border="1">
			<tr>
			<s:if test="%{msgPicture!=null}">
				<th rowspan="3"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
			</s:if>
			<s:else>
				<th rowspan="2"><a id="iduserlogo" href="user.action?userId=<s:property value="userId"/>" >
				<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
			</s:else>
				<td >
					<s:if test="referencedUserName == null" >
						<a id="idmsgcontent"><s:property value="msgContent" /></a>
					</s:if>
					<s:else>
						<a id="idmsgcontent">回复<s:property value="referencedUserName" />:<s:property value="msgContent" /></a>
					</s:else>
				</td>
			</tr>
			<s:if test="%{msgPicture!=null}">
				<tr>
					<td><a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="msgPicture"/>" >
						<img width="80" src="<%=request.getContextPath()%><s:property value="msgPicture" />" /></a>
					</td>
				</tr>
			</s:if>
			<tr>
				<td>
					<a id="idtime"><s:property value="time" /></a>&nbsp;&nbsp;<a id="idplace"><s:property value="place"/></a>&nbsp;&nbsp;&nbsp;
					<s:if test="#session.user==userName " >
						<a id="iddeleteReply" href="deleteReply.action?msgId=<s:property value="msgId"/>&commentMsgId=<s:property value="commentMsgId"/>" >删除</a>&nbsp;
					</s:if> 
					<a id="idreply" href="reply.action?commentUserName=<s:property value="commentUserName"/>&msgId=<s:property value="msgId"/>">回复</a>
				</td>
			</tr>
	</table>
	</s:iterator>
</body>
</html>
