<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>登录系统---轨迹</title>
	</head>
	<body>
		<%@include file="header.jsp"%>
		<table width=780 align="center" background="${pageContext.request.contextPath}/images/bodybg.jpg">
			<tr>
				<td>
					<div align="center">
						<s:form action="loginAction">
							<s:textfield name="userInfo.email" label="账号"/>
							<s:textfield name="userInfo.user_password" label="密码"/>
							<s:textfield name="userInfo.nowcoordinate" label="坐标" />
							<tr>
								<td colspan="2">
									<s:submit value="登录" theme="simple"/>
									<s:reset theme="simple" value="重填"/>
								</td>
							</tr>
						</s:form>
					</div>
				</td>
			</tr>
		</table>
		<%@include file="footer.jsp"%>
	</body>
</html>