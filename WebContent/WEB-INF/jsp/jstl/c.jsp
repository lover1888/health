<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<title>JSTL核心标签库 C</title>
</head>
<body>

<h1>
c:forEach 标签
</h1>
<ul>
	<li>遍历 List列表</li>
	<c:forEach items="${obj}" var="o">
		${o.questionId}---${o.title}<br>
		
	</c:forEach>
	
	<li>遍历Map</li>
	<c:forEach items="${map}" var="map">
		${map.key}--${map.value}<br>
		<c:if test=""></c:if>
		
	</c:forEach>
</ul>

<h1>
c:forTokens 标签
</h1>
<c:forTokens items="${token}" delims="," var="tmp">
	${tmp }--
</c:forTokens>

<h1>
c:out 标签
</h1>
<c:out value="${varstr }">varstr是没有值的</c:out>

<h1>c:set 标签</h1>
<c:set value="page-value" var="setPage" scope="page"></c:set>
<c:set value="req-value" var="setRequset" scope="request"></c:set>
<c:set value="session-value" var="setSession" scope="session"></c:set>
<c:set value="app-value" var="setApplication" scope="application"></c:set>

<h1>c:if 标签</h1>
<c:if test="${not empty setPage}">
	${setPage }<br>
</c:if>

<c:if test="${empty abc }">
	abc为空<br>
</c:if>
<c:choose>
	<c:when test="${!empty ccc }">${ccc }</c:when>
	<c:otherwise>ccc为空</c:otherwise>
</c:choose>
<h1>c:import 标签</h1>
<c:import url="http://www.baidu.com" var="data"></c:import>
<c:out value="${data }"></c:out>

</body>
</html>