<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>看病吧</title>
</head>
<body>
<shiro:guest>
<a href="<c:url value='/login' />">登录</a>
</shiro:guest>
<shiro:user>
	<c:set var="name"><shiro:principal/></c:set>
    <p>用户：<a href='<c:url value="/u/${name }" />'><shiro:principal/></a> &nbsp;
    <a href="<c:url value='/logout' />">退出</a>
</shiro:user>

<h4>你哪里不舒服？<a href="<c:url value='/question/ask'></c:url>">我要咨询 </a></h4>
<h3>问题列表</h3>
<table><tr>
	<td><a href="<c:url value='/question/newest' />">最新的</a></td>
	<td><a href="<c:url value='/question/hottest' />">热门的</a></td>
	<td><a href="<c:url value='/question/unanswered' />">未回答</a></td>
</tr></table>
<table border="1">
<c:forEach items="${pagination.list }" var="q">
	<tr>
		<td> ${q.voteCount} 投票</td>
		<td>${q.answersCount }回答</td>
		<td>${q.viewCount }浏览</td>
		<td>
		<a href="<c:url value='/q/${q.questionId }' /> ">${q.title}</a> 
		--
		<fmt:formatDate value="${q.createDate }" pattern="MM-dd HH:mm"/>
		</td>
		<td>${q.tags }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>