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
<%@ include file="/WEB-INF/jsp/top.jsp" %>

<script type="text/javascript">
	function vote(urlStr,contentStr){
		var vdialog = dialog({
			title: '提示',
			content: contentStr,
			okVal: '确定',
			ok: function() {
				this.content('投票中，请稍等...');
				$.ajax({
					url:urlStr,
					success:function(data){
						var td = dialog({
							title:'提示',
							content: data
						});
						td.show();
						setTimeout(function(){
							td.close().remove();
						},2000);
						//window.location.reload();
					}
				});
			},
			cancelVal: '取消',
			cancel: true
		}); 
		vdialog.showModal();
	}
	
	function focusq(urlStr) {
		$.ajax({
			url:urlStr,
			success:function(data){
				var td = dialog({
					title:'提示',
					content: data
				});
				td.show();
				setTimeout(function(){
					td.close().remove();
				},2000);
				window.location.reload();
			}
			
		});
	}
	
	function adoptq(urlStr, contentStr) {
		var vdialog = dialog({
			title: '提示',
			content: contentStr,
			okVal: '确定',
			ok: function() {
				this.content('采纳处理中，请稍等...');
				$.ajax({
					url:urlStr,
					success:function(data){
						var td = dialog({
							title:'提示',
							content: data
						});
						td.show();
						setTimeout(function(){
							td.close().remove();
						},2000);
						window.location.reload();
					}
				});
			},
			cancelVal: '取消',
			cancel: true
		}); 
		vdialog.showModal();
	}
	
</script>
<h2>标题：${detailVo.question.title }</h2>
<c:choose>
	<c:when test="${detailVo.isFocus }">
	&nbsp;&nbsp;<a onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/0')" href="#">已关注</a>&nbsp;&nbsp;${detailVo.question.focusCount }关注<br>
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;<a onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/1')" href="#">关注</a>&nbsp;&nbsp;${detailVo.question.focusCount }关注<br>
	</c:otherwise>
</c:choose>
&nbsp;&nbsp;<a href="">收藏</a>&nbsp;&nbsp;${detailVo.question.favoriteCount }收藏，${detailVo.question.viewCount }浏览

<table>
	<tr height="40">
		<td>头像：${detailVo.imgUrl }</td>
		<td>&nbsp;&nbsp;用户：<a href="<c:url value='/u/${detailVo.userName }'></c:url>">${detailVo.userName }</a></td>
		<td>&nbsp;&nbsp;声望：${detailVo.reputation }</td>
		<td>&nbsp;&nbsp;提问时间：<fmt:formatDate value="${detailVo.question.createDate }" pattern="MM/dd HH:mm"/> </td>
	</tr>
</table>
<table>
	<tr>
		<td>
			<a href="#" onclick="vote('${baseURI}/q/${detailVo.question.questionId }/vote/1','确认要为它投赞同票吗？');">赞同</a><br>
			${detailVo.question.voteCount }<br>
			<a href="#" onclick="vote('${baseURI}/q/${detailVo.question.questionId }/vote/0','确认要为它投反对票吗？');">反对</a>
		</td>
		<td>内容:${detailVo.question.content }</td>
	</tr>
	<tr height="40">
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
			<td>
			<a href="#" onclick="vote('${baseURI}/answer/${ans.answers.answersId }/vote/1','确认要为它投赞同票吗？');">赞同</a><br>
			${ans.answers.voteCount }<br>
			<a href="#" onclick="vote('${baseURI}/answer/${ans.answers.answersId }/vote/0','确认要为它投反对票吗？');">反对</a>
			</td>
			<td>
			回答人:<a href="<c:url value='/u/${ans.userName }'></c:url>">${ans.userName }</a>&nbsp;&nbsp;声望:${ans.reputation }&nbsp;&nbsp;<fmt:formatDate value="${ans.answers.createDate }" pattern="MM/dd HH:mm"/> <br>
			回答内容:${ ans.answers.content}
			</td>
			<td>头像:${ans.imgUrl }</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<c:if test="${currUserId==detailVo.question.userId }">
					<c:choose>
						<c:when test="${detailVo.question.questionStatus==1}">
							<a href="#" onclick="adoptq('${baseURI}/answer/${ans.answers.answersId }/adopt/1','确认要采纳该答案吗？');" >采纳答案</a>
						</c:when>
						<c:when test="${ans.answers.isAdoption }">
							<a href="#" onclick="adoptq('${baseURI}/answer/${ans.answers.answersId }/adopt/0','确认要取消采纳该答案吗？');" >取消采纳</a>
						</c:when>
					</c:choose>
				</c:if>
				&nbsp;&nbsp;<a href="#">评论</a> &nbsp;&nbsp;  <a href="#">举报</a>
			</td>
			<td>
			</td>
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