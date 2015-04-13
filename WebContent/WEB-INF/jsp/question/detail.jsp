<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>看病吧--问答详情</title>
<script src="${baseURI }/js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="${baseURI }/css/ui-dialog.css">
<script src="${baseURI }/js/dialog.js"></script>
</head>
<body>
<a href="<c:url value='/question' />">问答</a> > 问答详情
<script type="text/javascript">
	function test() {
		var d = dialog({
		    title: '欢迎',
		    content: '欢迎使用 artDialog 对话框组件！',
		    okValue: '确定',
		    ok: function() {
		    	this.title('投票中...');
		    	return false;
		    },
			cancelValue: '取消',
			cancel: function() {}
		});
		d.showModal();
	}
</script>
<h2>??<c:if test="${not empty obj }">提示：${obj }</c:if></h2>

<h1>标题：${detailVo.question.title }</h1>
<table>
	<tr>
		<td>头像:${detailVo.imgUrl }</td>
		<td>用户:<a href="<c:url value='/u/${detailVo.userName }'></c:url>">${detailVo.userName }</a></td>
		<td>声望:${detailVo.reputation }</td>
		<td>提问时间:<fmt:formatDate value="${detailVo.question.createDate }" pattern="MM/dd HH:mm"/> </td>
	</tr>
</table>
<a href="#" onclick="test();">测试</a>
<table>
	<tr>
		<td>
			<a href="<c:url value='/q/${detailVo.question.questionId }/vote/1'></c:url>">赞同</a><br>
			${detailVo.question.voteCount }<br>
			<a href="<c:url value='/q/${detailVo.question.questionId }/vote/0'></c:url>">反对</a>
		</td>
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
			回答人:<a href="<c:url value='/u/${ans.userName }'></c:url>">${ans.userName }</a>&nbsp;&nbsp;声望:${ans.reputation }&nbsp;&nbsp;<fmt:formatDate value="${ans.answers.createDate }" pattern="MM/dd HH:mm"/> <br>
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
<hr>
<h3>撰写答案</h3>
<form action="<c:url value='/q/answer'></c:url>" method="post">
<textarea rows="10" cols="50" name="content"></textarea>
<input type="hidden" name="questionId" value="${detailVo.question.questionId }"><br><br>
<input type="submit" value="提交回答">
</form>
</body>
</html>