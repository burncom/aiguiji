<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>选择感兴趣的地点</title>
	</head>
	<body>
		<%@include file="header.jsp" %>
		<table width=780 align="center" >
			<tr>
				<td>
					<s:form action="editInterestAction">
					<s:checkboxlist list="#{'店铺':'1','旅游':'2','超市':'3','酒店':'4','街道':'5',
	'电影院':'6','展览演出':'7','运动健身':'8','洗浴按摩':'9','KTV/酒吧':'10','丽人':'11','写真摄影':'12',
	'医疗保健':'13','汽车服务':'14','招聘会':'15','会议':'16','环保':'17','节庆':'18','派对':'19','讲座':'20','培训':'21',
	'其他':'22'}" label="感兴趣的地点" name="userInfo.category" listKey="value" listValue="key"/>
					<tr>
						<td>
							<s:submit value="进入首页" theme="simple"/>
						</td>
					</tr>
					</s:form>
				</td>
			</tr>
		</table>
		<%@include file="footer.jsp" %>
	</body>
</html>