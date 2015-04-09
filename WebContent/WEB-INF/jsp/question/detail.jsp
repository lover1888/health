<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>看病吧--问答详情</title>
</head>
<body>
<a href="<c:url value='/question' />">问答</a> > 问答详情
<h1>${detailVo.question.title }</h1>
<table>
	<tr>
		<td>头像:${detailVo.imgUrl }</td>
		<td>用户名:${detailVo.userName }</td>
		<td>声望:${detailVo.reputation }</td>
		<td>提问时间:<fmt:formatDate value="${detailVo.question.createDate }" pattern="MM/dd HH:mm"/> </td>
	</tr>
</table>
<table>
	<tr>
		<td>投票:${detailVo.question.voteCount }</td>
		<td>内容:${detailVo.question.content }</td>
	</tr>
	<tr>
		<td></td>
		<td>标签:${detailVo.question.tags}</td>
	</tr>
	<tr>
		<td></td>
		<td><a href="#">评论</a> &nbsp;&nbsp;  <a href="#">举报</a></td>
	</tr>
</table>
<br>
${detailVo.question.answersCount }个回答
<hr>
<table border="1">
	<c:forEach items="${ansPg.list }" var="ans">
		<tr>
			<td>投票:${ans.answers.voteCount }</td>
			<td>
			回答人:${ans.userName }&nbsp;&nbsp;声望:${ans.reputation }&nbsp;&nbsp;<fmt:formatDate value="${ans.answers.createDate }" pattern="MM/dd HH:mm"/> <br>
			回答内容:${ ans.answers.content}
			</td>
			<td>头像:${ans.imgUrl }</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="#">评论</a> &nbsp;&nbsp;  <a href="#">举报</a></td>
			<td></td>
		</tr>
		
	</c:forEach>
	
</table>

</body>
</html>