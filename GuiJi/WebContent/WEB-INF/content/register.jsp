<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>注册系统---轨迹</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<table width=780 align="center" >
			<tr>
				<td>
					<s:if test="tip!=null">
						<div class="error">
							<s:property value="tip"/>
						</div>
					</s:if>
					<div align="center">
						<s:form action="registerAction">
							<s:textfield name="userInfo.email" label="邮箱" /> 
							<s:textfield name="userInfo.user_name" label="昵称" />
							<s:textfield name="userInfo.user_password" label="设置密码" />
							<s:textfield name="repassword" label="重复密码" />
							<s:textfield name="gender" label="性别" />
							<s:textfield name="userInfo.coordinate" label="坐标"></s:textfield>
							<tr>
								<td>
									<s:submit value="立即注册" theme="simple"/>
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