<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<title>I am hello ^_^</title>
</head>
<body>
pageNo=${pgs.pageNo }<br>
pageSize=${pgs.pageSize }<br>
totalCount=${pgs.totalCount }<br>
<c:forEach items="${pgs.list }" var="q">
	${ q.questionId}---${q.title }---${q.content }<br>
</c:forEach>

</body>
</html>