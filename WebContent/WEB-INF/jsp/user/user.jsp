<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.userName }--看病吧</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/top.jsp" %>
<h1>${user.userName }的信息</h1>
<hr>
<h3>${user.userName }的声望：${user.reputationCount }</h3>
<table>
	<c:forEach items="${user.reputations }" var="repu">
	<tr>
		<td>${repu.value }</td>
		<td>${repu.reputationType }</td>
		<td><fmt:formatDate value="${repu.createDate }" pattern="MM月dd日 HH:mm"/> </td>
		<td>${repu.sourceTitle }</td>
	</tr>
</c:forEach>
</table>

<hr>
<h3>${user.userName }的标签</h3>
<c:forEach items="${user.userTags }" var="tag">
	${tag.tagName } x ${tag.value }<br>
</c:forEach>

<hr>
<h3>${user.userName }关注的问题</h3>
<c:forEach items="${user.focusQuestions }" var="fq">
	${fq.title }---<fmt:formatDate value="${fq.createDate }" pattern="MM月dd日 HH:mm"/><br>
</c:forEach>
</body>
</html>