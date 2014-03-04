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
		<s:form action="join">
			<s:textfield name="joinV" theme="simple" />
			<s:hidden name="msgId" value='%{msgId}'></s:hidden>
			<s:submit value="想参加" theme="simple" />
		</s:form>
</body>
</html>
