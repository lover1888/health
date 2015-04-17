<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h2><c:if test="${not empty errmsg }">出错啦：${errmsg }</c:if></h2>
<table width="800">
	<tr>
		<td width="400">
			<h3>创建新帐号</h3>
			<form action="<c:url value='/register' />" method="post">
				<table width="400">
					<tr>
						<td>用户名：<br><input type="text" name="userName"></td>
					</tr>
					<tr>
						<td>
						Email邮箱地址：<br>
						<input type="text" name="email"></td>
					</tr>
					<tr>
						<td>
						密码：<br>
						<input type="password" name="password"></td>
					</tr>
					<tr>
						<td>同意并接受《服务条款》
							<input type="submit" value="注册">
						</td>
					</tr>
				</table>
			</form>
		</td>
		<td>
			<form action="<c:url value='/loginAct' />" method="post">
				<h3>用户登录</h3>
				<table width="400">
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
							<input type="submit" value="登 录">
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>

</table>



</body>
</html>