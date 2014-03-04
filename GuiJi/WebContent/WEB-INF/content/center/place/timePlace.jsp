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
	<table  width="780" align="center" border="1">
		<tr >
			<th width="150" rowspan="2">
				<%@include file="../homeLeft.jsp" %>
			</th>
			<td width="600" >
				<a href="publishMsgOrAct">发布新鲜地点</a>
			</td>
		</tr>
		<tr>
			<td >
				<%--插入新鲜地点~实时 --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="timePlace">实时</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--插入新鲜地点~热度 --%>
				<a href="hotPlace">热度</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<%--插入新鲜地点~推荐 --%>
				<a href="recommendPlace">推荐</a>
			</td>
		</tr>
	</table>
	
		<s:iterator value="message">
		<table id="tdmsgoract" width="780" align="center" border="1">
				<%--插入动态显示内容--%>
				<%--信息原创  --%>
					<s:if test='%{type=="1"}' >
						<tr>
							<th rowspan="3"><a id="iduserlink" href="user.action?userId=<s:property value="userId"/>" >
							<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td ><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>:
							<a id="idmsgcontent"><s:property value="msgContent"/></a></td>
						</tr>
						<s:if test="%{msgPicture!=null}">
						<tr>
							<td>
								<a  id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="msgPicture"/>" >
								<img  width="80" src="<%=request.getContextPath()%><s:property value="msgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								<a id="ptime" ><s:property value="time"/></a>&nbsp;
								<a id="pplace" ><s:property value="place"/></a>&nbsp;
								<a id="alinkpictureset" href="pictureset.action?type=1&msgId=<s:property value="msgId"/>" >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<a id="idcategory"><s:property value="category"/></a>&nbsp;&nbsp;
								<s:if test="#session.user==userName " >
									<a id="alinkdelete" href="deleteMsgOrAct.action?type=1&msgId=<s:property value="msgId"/>" >删除</a>
								</s:if>
								<a id="alinkrecommend" href="recommend.action?msgId=<s:property value="msgId"/>" >推荐(<s:property value="recommendNum"/>)</a>
								<a id="alinktransmit" href="transmit.action?type=1&msgId=<s:property value="msgId"/>" >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a id="alinkcomment" href="comment.action?type=1&msgId=<s:property value="msgId"/>" >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--信息转发--%>
					<s:if test='%{type=="2"}'>
						<tr>
							<th rowspan="6"><a id="iduserlink" href="user.action?userId=<s:property value="userId"/>" >
							<img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td ><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>:
							<a id="idmsgcontent"><s:property value="msgContent"/></a></td>
						</tr>
						<tr>
							<td><a id="idfirstusername" href="user.action?userId=<s:property value="firstUserId"/>" ><s:property value="firstUserName"/></a>&nbsp;&nbsp;
							<a id="idcategory" ><s:property value="category"/></a></td>
						</tr>
						<tr>
							<td ><a id="idfirstcontent"><s:property value="firstMsgContent"/></a></td>
						</tr>
						<s:if test="%{firstMsgPicture!=null}">
						<tr>
							<td><a id="idfirstpic" href="toBigPicture.action?picture=<s:property  value="firstMsgPicture"/>" >
							<img  width="80" src="<%=request.getContextPath()%><s:property value="firstMsgPicture" />" /></a></td>
						</tr>
						</s:if>
						<tr>
							<td>
							<a id="pfirsttime" ><s:property value="firstTime"/></a>&nbsp;
							<a id="pfirstplace" ><s:property value="firstPlace"/></a>&nbsp;
							<a id="afisrtlinkpictureset" href="pictureset.action?type=1&msgId=<s:property value="firstMsgId"/>" >图片集(<s:property value="firstPictureSetNum"/>)</a>&nbsp;
							<a id="afisrtlinkrecommend" href="recommend.action?msgId=<s:property value="firstMsgId"/>">推荐(<s:property value="firstRecommendNum"/>)</a>&nbsp;
							<a id="afisrtlinktransmit" href="transmit.action?type=1&msgId=<s:property value="firstMsgId"/>" >转发(<s:property value="firstTransmitNum"/>)</a>&nbsp;
							<a id="afisrtlinkcomment" href="comment.action?type=1&msgId=<s:property value="firstMsgId"/>" >评论(<s:property value="firstCommentNum"/>)</a>
							</td>
						</tr>
						<tr>
							<td>
								<a id="ptime" ><s:property value="time"/></a>&nbsp;
								<a id="pplace" ><s:property value="place"/></a>&nbsp;
								<a id="alinkpictureset" href="pictureset.action?type=1&msgId=<s:property value="msgId"/>" >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a id="alinkdelete" href="deleteMsgOrAct.action?type=2&msgId=<s:property value="msgId"/>" >删除</a>&nbsp;
								</s:if>
								<a id="alinkrecommend" href="recommend.action?msgId=<s:property value="msgId"/>" >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a id="alinktransmit" href="transmit.action?type=2&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>" >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a id="alinkcomment" href="comment.action?type=2&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>" >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--活动原创--%>
					<s:if test='%{type=="3"}'>
						<tr>
							<th rowspan="5"><a id="iduserlink" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>发起了活动:
							<a id="idmsgcontent"><s:property value="msgContent"/></a></td>
						</tr>
						<s:if test="%{msgPicture!=null}">
						<tr>
							<td>
								<a id="idmsgpic" href="toBigPicture.action?picture=<s:property  value="msgPicture"/>" >
								<img  width="80" src="<%=request.getContextPath()%><s:property value="msgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								<a id="idacttime">活动时间：<s:property value="startTime"/> 至 <s:property value="endTime"/></a>&nbsp;&nbsp;
								<a id="idactplace">活动地点:<s:property value="activityPlace"/></a>
							</td>
						</tr>
						<tr>
							<td>
								发起单位/个人：<a id="idleadername"><s:property value="leaderName"/></a>&nbsp;&nbsp;&nbsp;
								分类:<a id="idcategory"><s:property value="category"/></a>
							</td>
						</tr>
						<tr>
							<td>
								<a id="ptime" ><s:property value="time"/></a>&nbsp;
								<a id="pplace" ><s:property value="place"/></a>&nbsp;
								<a id="alinkpictureset" href="pictureset.action?type=3&msgId=<s:property value="msgId"/>" >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a id="alinkdelete" href="deleteMsgOrAct.action?type=3&msgId=<s:property value="msgId"/>" >删除</a>&nbsp;
								</s:if>
								<a id="alinkjoin" href="join.action?msgId=<s:property value="msgId"/>" >想参加(<s:property value="joinNum"/>)</a>&nbsp;
								<a id="alinkrecommend" href="recommend.action?msgId=<s:property value="msgId"/>" >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a id="alinktransmit" href="transmit.action?type=3&msgId=<s:property value="msgId"/>" >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a id="alinkcomment" href="comment.action?type=3&msgId=<s:property value="msgId"/>" >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
					<%--活动转发--%>
					<s:if test='%{type=="4"}'>
						<tr>
							<th rowspan="8"><a id="iduserlink" href="user.action?userId=<s:property value="userId"/>" ><img width="50" src="<%=request.getContextPath()%><s:property value="userLogo" />" /></a></th>
							<td><a id="idusername" href="user.action?userId=<s:property value="userId"/>" ><s:property value="userName"/></a>发起了活动:
							<a id="idmsgcontent"><s:property value="msgContent"/></a></td>
						</tr>
						<tr>
							<td><a id="idfirstusername" href="user.action?userId=<s:property value="firstUserId"/>" ><s:property value="firstUserName"/></a></td>
						</tr>
						<tr>
							<td >
								<a id="idfirstcontent"><s:property value="firstMsgContent"/></a>
							</td>
						</tr>
						<s:if test="%{firstMsgPicture!=null}">
						<tr>
							<td>
								<a id="idfirstpic" href="toBigPicture.action?picture=<s:property  value="photo"/>" >
								<img  width="80" src="<%=request.getContextPath()%><s:property value="firstMsgPicture" />" /></a>
							</td>
						</tr>
						</s:if>
						<tr>
							<td>
								<a id="idacttime">活动时间：<s:property value="startTime"/> 至 <s:property value="endTime"/></a>
								<a id="idactplace">活动地点:<s:property value="activityPlace"/></a>
							</td>
						</tr>
						<tr>
							<td>
								发起单位/个人：<a id="idleadername"><s:property value="leaderName"/></a>&nbsp;&nbsp;&nbsp;
								分类:<a id="idcategory" ><s:property value="category"/></a>
							</td>
						</tr>
						<tr>
							<td>
								<a id="pfirsttime" ><s:property value="firstTime"/></a>&nbsp;
								<a id="pfirstplace" ><s:property value="firstPlace"/></a>&nbsp;
								<a id="afisrtlinkpictureset" href="pictureset.action?type=3&msgId=<s:property value="firstMsgId"/>">图片集(<s:property value="firstPictureSetNum"/>)</a>&nbsp;
								<a id="afisrtlinkjoin" href="join.action?msgId=<s:property value="firstMsgId"/>" >想参加(<s:property value="firstJoinNum"/>)</a>&nbsp;
								<a id="afisrtlinkrecommend" href="recommend.action?msgId=<s:property value="firstMsgId"/>" >推荐(<s:property value="firstRecommendNum"/>)</a>&nbsp;
								<a id="afisrtlinktransmit" href="transmit.action?type=3&msgId=<s:property value="firstMsgId"/>" >转发(<s:property value="firstTransmitNum"/>)</a>&nbsp;
								<a id="afisrtlinkcomment" href="comment.action?type=3&msgId=<s:property value="firstMsgId"/>" >评论(<s:property value="firstCommentNum"/>)</a>
							</td>
						</tr>
						<tr>
							<td>
								<a id="ptime" ><s:property value="time"/></a>&nbsp;
								<a id="pplace" ><s:property value="place"/></a>&nbsp;
								<a id="alinkpictureset" href="pictureset.action?type=3&msgId=<s:property value="msgId"/>" >图片集(<s:property value="pictureSetNum"/>)</a>&nbsp;
								<s:if test="#session.user==userName " >
									<a id="alinkdelete" href="deleteMsgOrAct.action?type=4&msgId=<s:property value="msgId"/>" >删除</a>&nbsp;
								</s:if>
								<a id="alinkjoin" href="join.action?msgId=<s:property value="msgId"/>" >想参加(<s:property value="joinNum"/>)</a>&nbsp;
								<a id="alinkrecommend" href="recommend.action?msgId=<s:property value="msgId"/>" >推荐(<s:property value="recommendNum"/>)</a>&nbsp;
								<a id="alinktransmit" href="transmit.action?type=4&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>" >转发(<s:property value="transmitNum"/>)</a>&nbsp;
								<a id="alinkcomment" href="comment.action?type=4&firstMsgId=<s:property value="firstMsgId"/>&msgId=<s:property value="msgId"/>" >评论(<s:property value="commentNum"/>)</a>
							</td>
						</tr>
					</s:if>
	</table>
			</s:iterator>
	
		<%@include file="../homeFooter.jsp"%>
</body>
</html>
