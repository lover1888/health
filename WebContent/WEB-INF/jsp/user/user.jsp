<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.userName }--看病吧</title>
</head>
<body>
<a href="<c:url value='/question'></c:url>">问题</a>

<h1>${user.userName }的信息</h1>
<hr>
<h3>${user.userName }的标签</h3>
<c:forEach items="${user.tags }" var="tag">
	${tag.tagName }<br>
</c:forEach>
</body>
</html>