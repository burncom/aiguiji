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
	
	<%--用户相册 --%>
	<table width=780 align="center" border="1" >
	<tr>
		<td>相册
		</td>
	</tr>
	</table>
	<s:iterator value="photosBean">
	<table id="idmyphotos"  width=780 align="center" border="1" >
	<tr>
		<td>
		<a id="idphoto" href="toBigPicture.action?picture=<s:property  value="photo"/>" ><img width="80" src="<%=request.getContextPath()%><s:property  value="photo"/>" /></a>
		</td>
	</tr>
	</table>
	</s:iterator>
	
	
	<%--用户新鲜地点和心情 --%>
	<table width=780 align="center" border="1" >
		<tr>
			<td>
				<a href="user.action?userId=<s:property value="findUsersBean.userId"/>">新鲜地点</a>
				<a href="userMood.action?userId=<s:property value="findUsersBean.userId"/>">心情</a>
			</td>
		</tr>
	</table>
	<%--用户新鲜地点 --%>
	<table width=780 align="center" border="1" >
	<tr>
		<s:iterator value="messageBean">
			<td>
				<%--插入动态显示内容--%>
				<%--信息原创  --%>
					<s:if test='%{type=="1"}' >
						<tr>
							<th rowspan="3"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>:<s:property value="msgContent"/></td>
						</tr>
						<s:if test="%{msgPicture!=null}">
						<tr>
							<td><a href="toBigPicture.action?picture=<s:property  value="msgPicture"/>" >
								<img width="80" src="<%=request.getContextPath()%><s:property value="msgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								<s:property value="time"/>&nbsp;
								<s:property value="place"/>&nbsp;
								<a href='pictureset.action?type=1&msgId=<s:property value="msgId"/>' >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								分类:<s:property value="category"/>&nbsp;&nbsp;
								<s:if test="#session.user==userName " >
									<a href='deleteMsgOrAct.action?type=1&msgId=<s:property value="msgId"/>' >删除</a>
								</s:if>
								<a href='recommend.action?msgId=<s:property value="msgId"/>' >推荐(<s:property value="recommendNum"/>)</a>
								<a href='transmit.action?type=1&msgId=<s:property value="msgId"/>' >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a href='comment.action?type=1&msgId=<s:property value="msgId"/>' >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--信息转发--%>
					<s:if test='%{type=="2"}'>
						<tr>
							<th rowspan="6"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>:<s:property value="msgContent"/></td>
						</tr>
						<tr>
							<td><a href="user.action?userId=<s:property value="firstUserId"/>" ><s:property value="firstUserName"/></a>&nbsp;&nbsp;分类：<s:property value="category"/></td>
						</tr>
						<tr>
							<td><s:property value="firstMsgContent"/></td>
						</tr>
						<s:if test="%{firstMsgPicture!=null}">
						<tr>
							<td><a href="toBigPicture.action?picture=<s:property  value="firstMsgPicture"/>" >
							<img width="80" src="<%=request.getContextPath()%><s:property value="firstMsgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td><s:property value="firstTime"/>&nbsp;<s:property value="firstPlace"/>&nbsp;
							<a href='pictureset.action?type=1&msgId=<s:property value="firstMsgId"/>' >图片集(<s:property value="firstPictureSetNum"/>)</a>&nbsp;
							<a href='recommend.action?msgId=<s:property value="firstMsgId"/>' >推荐(<s:property value="firstRecommendNum"/>)</a>&nbsp;
							<a href='transmit.action?type=1&msgId=<s:property value="firstMsgId"/>' >转发(<s:property value="firstTransmitNum"/>)</a>&nbsp;
							<a href='comment.action?type=1&msgId=<s:property value="firstMsgId"/>' >评论(<s:property value="firstCommentNum"/>)</a>
							</td>
						</tr>
						<tr>
							<td>
								<s:property value="time"/>&nbsp;
								<s:property value="place"/>&nbsp;
								<a href='pictureset.action?type=1&msgId=<s:property value="msgId"/>' >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a href='deleteMsgOrAct.action?type=2&msgId=<s:property value="msgId"/>' >删除</a>&nbsp;
								</s:if>
								<a href='recommend.action?msgId=<s:property value="msgId"/>' >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a href='transmit.action?type=2&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>' >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a href='comment.action?type=2&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>' >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--活动原创--%>
					<s:if test='%{type=="3"}'>
						<tr>
							<th rowspan="5"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>发起了活动:<s:property value="msgContent"/></td>
						</tr>
						<s:if test="%{msgPicture!=null}">
						<tr>
							<td><a href="toBigPicture.action?picture=<s:property  value="msgPicture"/>" >
								<img width="80" src="<%=request.getContextPath()%><s:property value="msgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								活动时间：<s:property value="startTime"/>&nbsp;至&nbsp;<s:property value="endTime"/>&nbsp;&nbsp;
								活动地点:<s:property value="activityPlace"/>
							</td>
						</tr>
						<tr>
							<td>
								发起单位/个人：<s:property value="leaderName"/>&nbsp;&nbsp;&nbsp;分类:<s:property value="category"/>
							</td>
						</tr>
						<tr>
							<td>
								<s:property value="time"/>&nbsp;
								<s:property value="place"/>&nbsp;
								<a href='pictureset.action?type=3&msgId=<s:property value="msgId"/>' >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a href='deleteMsgOrAct.action?type=3&msgId=<s:property value="msgId"/>' >删除</a>&nbsp;
								</s:if>
								<a href='join.action?msgId=<s:property value="msgId"/>' >想参加(<s:property value="joinNum"/>)</a>&nbsp;
								<a href='recommend.action?msgId=<s:property value="msgId"/>' >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a href='transmit.action?type=3&msgId=<s:property value="msgId"/>' >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a href='comment.action?type=3&msgId=<s:property value="msgId"/>' >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--活动转发--%>
					<s:if test='%{type=="4"}'>
						<tr>
							<th rowspan="8"><a href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>发起了活动:<s:property value="msgContent"/></td>
						</tr>
						<tr>
							<td><a href="user.action?userId=<s:property value="firstUserId"/>" ><s:property value="firstUserName"/></a></td>
						</tr>
						<tr>
							<td>
								<s:property value="firstMsgContent"/>
							</td>
						</tr>
						<s:if test="%{firstMsgPicture!=null}">
						<tr>
							<td><a href="toBigPicture.action?picture=<s:property  value="firstMsgPicture"/>" >
								<img width="80" src="<%=request.getContextPath()%><s:property value="firstMsgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								活动时间：<s:property value="startTime"/>&nbsp;至&nbsp;<s:property value="endTime"/>
								活动地点:<s:property value="activityPlace"/>
							</td>
						</tr>
						<tr>
							<td>
								发起单位/个人：<s:property value="leaderName"/>&nbsp;&nbsp;&nbsp;分类:<s:property value="category"/>
							</td>
						</tr>
						<tr>
							<td>
								<s:property value="firstTime"/>&nbsp;
								<s:property value="firstPlace"/>&nbsp;
								<a href='pictureset.action?type=3&msgId=<s:property value="firstMsgId"/>' >图片集(<s:property value="firstPictureSetNum"/>)</a>&nbsp;
								<a href='join.action?msgId=<s:property value="firstMsgId"/>' >想参加(<s:property value="firstJoinNum"/>)</a>&nbsp;
								<a href='recommend.action?msgId=<s:property value="firstMsgId"/>' >推荐(<s:property value="firstRecommendNum"/>)</a>&nbsp;
								<a href='transmit.action?type=3&msgId=<s:property value="firstMsgId"/>' >转发(<s:property value="firstTransmitNum"/>)</a>&nbsp;
								<a href='comment.action?type=3&msgId=<s:property value="firstMsgId"/>' >评论(<s:property value="firstCommentNum"/>)</a>
							</td>
						</tr>
						<tr>
							<td>
								<s:property value="time"/>&nbsp;
								<s:property value="place"/>&nbsp;
								<a href='pictureset.action?type=3&msgId=<s:property value="msgId"/>' >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a href='deleteMsgOrAct.action?type=4&msgId=<s:property value="msgId"/>' >删除</a>&nbsp;
								</s:if>
								<a href='join.action?msgId=<s:property value="msgId"/>' >想参加(<s:property value="joinNum"/>)</a>&nbsp;
								<a href='recommend.action?msgId=<s:property value="msgId"/>' >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a href='transmit.action?type=4&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>' >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a href='comment.action?type=4&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>' >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
			</td>
			</s:iterator>
		</tr>
	</table>
</body>
</html>
