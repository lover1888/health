<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h2><c:if test="${not empty errmsg }">出错啦：${errmsg }</c:if></h2>
<form action="<c:url value='/loginAct' />" method="post">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="userName"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="提交">
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>