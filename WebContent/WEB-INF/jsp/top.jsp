<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<shiro:guest>
<a href="<c:url value='/login' />">登录</a>
</shiro:guest>
<shiro:user>
	<c:set var="name"><shiro:principal/></c:set>
    <p>用户：<a href='<c:url value="/u/${name }" />'><shiro:principal/></a> &nbsp;
    <a href="<c:url value='/logout' />">退出</a>
</shiro:user>&nbsp;&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;
<a href="<c:url value='/question'></c:url>">问题</a>
<hr>
<p>
