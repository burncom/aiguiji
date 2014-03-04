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
				<td>
					<s:form action="publishWeatherAction">
						<s:textfield name="weatherInfo.weatherscene" label="天气气象" />
						<s:textfield name="weatherInfo.temperature" label="气温" />
						<s:textfield name="weatherInfo.wind" label="风向风力" />
						<s:textfield name="weatherInfo.coordinate" label="坐标" />
						<s:textfield name="place" label="地名" />
						<tr>
							<td>
								<s:submit value="发布天气" theme="simple" />
							</td>
						</tr>
					</s:form>
				</td>
			</tr>
		</table>
	</body>
</html>
