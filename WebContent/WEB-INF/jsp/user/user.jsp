<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${userName }--看病吧</title>
</head>
<body>
<h1>${userName }的信息</h1>
<hr>
<h3>${userName }的标签</h3>
<c:forEach items="${user.tags }" var="tag">
	${tag.tagName }<br>

</c:forEach>


</body>
</html>