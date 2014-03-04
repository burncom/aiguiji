<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>首页---轨迹</title>
</head>
<body>
	<%--排行榜 --%>
	<s:form action="publishMood" enctype="multipart/form-data" method="post">
	<table width="780" align="center" border="1">
		<tr>
			<th rowspan="10"><img width="80" src="<%=request.getContextPath()%><s:property  value="userLogo"/>" /></th>
			<td><s:textfield name="messageInfo.msg_content"/></td>
		</tr>
		<tr>
			<td>
				<s:textfield name="mood" label="心情指数"  />
			</td>
		</tr>
		<tr>
			<td>定位：
				<s:textfield name="messageInfo.coordinate" label="坐标"  />
				<s:textfield name="place" label="地名"  />
			</td>
		</tr>
		<tr>
			<td>
				<s:file name="moodPic" label="图片" />
			</td>
		</tr>
		<tr>
			<td>
				<s:submit value="发布心情" theme="simple" />
			</td>
		</tr>
	</table>
	</s:form>
</body>
</html>
