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
	<s:form action="replyMood" enctype="multipart/form-data" method="post">
	<table width="780" align="center" border="1">
	<s:hidden name="msgId" value='%{msgId}'></s:hidden>
	<s:hidden name="commentUserName" value='%{commentUserName}'></s:hidden>
	<tr>
		<td>
			回复<s:property value="commentUserName"/>：<s:textfield name="messageInfo.msg_content" theme="simple"/>
		</td>
	</tr>
	<tr>
		<td>
			<s:textfield name="messageInfo.coordinate" label="坐标"/>&nbsp;&nbsp;
			<s:file name="picture" label="图片" />
		</td>
	</tr>
	<tr>
		<td>
			<s:submit value="评论" theme="simple" />
		</td>
	</tr>
	</table>
	</s:form>
</body>
</html>
