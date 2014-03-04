<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>填写您的个人信息---轨迹</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<table width=780 align="center" >
			<tr>
				<td>
					<s:form action="editProfileAction" enctype="multipart/form-data" method="post">
						<s:file name="myAvatar" label="设置个人头像" />
						<tr>
							<td>
								<s:submit value="保存头像" theme="simple"/>
							</td>
						</tr>
					</s:form>
				</td>
			</tr>
		</table>
		<%@include file="footer.jsp"%>
	</body>
</html> 