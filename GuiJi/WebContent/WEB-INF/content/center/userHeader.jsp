<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<table border="1">
	<%--用户账号名，logo，心情指数 --%>
	<tr>
		<s:if test="%{moodBean.moodContent != null && moodBean.moodPic!=null}">
		<th rowspan="5"><img width="80" src="<%=request.getContextPath()%><s:property  value="moodBean.userLogo"/>" /> </th>
		</s:if>
		<s:elseif test="%{moodBean.moodContent != null && moodBean.moodPic==null}">
		<th rowspan="4"><img width="80" src="<%=request.getContextPath()%><s:property  value="moodBean.userLogo"/>" /> </th>
		</s:elseif>
		<s:else>
		<th rowspan="3"><img width="80" src="<%=request.getContextPath()%><s:property  value="moodBean.userLogo"/>" /></th>	
		</s:else>
		<td>
		<s:property  value="moodBean.userName"/>&nbsp;&nbsp;
		<s:if test="%{moodBean.mood== 0 || moodBean==null}">
			 还没有发表心情指数哦
		</s:if>	
		<s:else>
			心情指数为：<s:property value="moodBean.mood"/>
		</s:else>
		</td>
	</tr>
	<s:if test="%{moodBean.moodContent != null}">
	<tr>
		<td>
			<s:property value="moodBean.moodContent"/>
		</td>
	</tr>
	<s:if test="%{moodBean.moodPic!=null}">
	<tr>
		<td>
			<img width="80" src="<%=request.getContextPath()%><s:property value="moodBean.moodPic"/>" /> 
		</td>
	</tr>
	</s:if>
	<tr>
		<td>
			<s:property value="moodBean.time"/>&nbsp;
			<s:property value="moodBean.place"/>&nbsp;&nbsp;&nbsp;
			<a href='pictureset.action?type=5&msgId=<s:property value="moodBean.msgId"/>' >图片集(<s:property value="moodBean.pictureCount"/>)</a>&nbsp;
			<a href="deleteMood.action?msgId=<s:property value="moodBean.msgId"/>">删除</a>&nbsp;&nbsp;
			<a href="commentMood?msgId=<s:property value="moodBean.msgId"/>">评论(<s:property value="moodBean.commentCount"/>)</a>
		</td>
	</tr>
	</s:if>
	<s:else>
		<tr>
			<td>
				还没有发表心情哦，赶紧说下吧
			</td>
		</tr>
	</s:else>
	<tr>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="publishMood.action?userLogo=<s:property  value="moodBean.userLogo"/>">说几句</a>
		</td>
	</tr>
</table>

