<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要咨询</title>
</head>
<body>
<h1>提问题</h1>

<form action="<c:url value='/question/askAct'></c:url>" method="post">
	标题：<input type="text" name="title"><br><br>
	标签：<input type="text" name="tags"><br><br>
	内容：<textarea rows="10" cols="50" name="content"></textarea><br><br>
	<input type="submit" value="提交问题">
</form>
</body>
</html>