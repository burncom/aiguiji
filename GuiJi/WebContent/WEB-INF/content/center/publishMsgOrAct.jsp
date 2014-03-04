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
<table  align="center" border="1">
			<tr>
				<td>
					<div align="center">
						<s:form action="publish" enctype="multipart/form-data" method="post">
							<s:textfield name="messageInfo.msg_content" cssStyle="border:1; size:10px; width:400px;height:60px" />
							<s:textfield name="category" label="分类(*)" />
							<tr>
								<td>
								定位(*)：
									<s:textfield name="messageInfo.coordinate" label="坐标" />
									<s:textfield name="place" label="地名" />
								</td>
							</tr>
							<tr>
								<td>
							活动：<s:textfield name="messageInfo.leader_name" label="发起人" /> 
								</td>
								<td>
									活动地点定位:
									<s:textfield name="messageInfo.actcoordinate" />
									<s:textfield name="actplace" label="活动地点" />
								</td>
								<s:textfield name="startTime" label="开始时间"/>
								<s:textfield name="endTime" label="结束时间"/>
								<s:file name="uploadpicture" label="图片" />
							</tr>	
							<tr>
								<td>
								<s:submit value="发布" theme="simple" />
								</td>
							</tr>
						</s:form>
					</div>
				</td>
			</tr>
</table>
</body>
</html>
