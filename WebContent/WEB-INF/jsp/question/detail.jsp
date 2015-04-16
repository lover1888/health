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
	
	var isCommentqShow = false;
	function commentq(urlStr){
		$.ajax({
			url:urlStr,
			success:function(data){
				$('#qcommentDetail').html("<hr>");
				$.each($.parseJSON(data),function(idx, obj){
					$('#qcommentDetail').append(obj.questionComments.content+" >>> "+obj.userName).append("<p>");
				}); 
			}
		});
		
		isCommentqShow = !isCommentqShow;
		if(isCommentqShow){
			$('#qcomments').show();
		} else {
			$('#qcomments').hide();
		}
	}
	var isCommentaShow = false;
	function commenta(urlStr,id){
		$.ajax({
			url:urlStr,
			success:function(data){
				$('#comment'+id+'Detail').html("<hr>");
				$.each($.parseJSON(data),function(idx, obj){
					$('#comment'+id+'Detail').append(obj.answerComment.content+" >>> "+obj.userName).append("<p>");
				}); 
			}
		});
		
		isCommentaShow = !isCommentaShow;
		if(isCommentaShow){
			$('#comment'+id).show();
		} else {
			$('#comment'+id).hide();
		}
	}
	
	function postComment(id){
		$.ajax({
		    type: 'post',
		    url: '${baseURI}/answer/comment',
		    data: $("#comment"+id+"Form").serialize(),
		    success: function(data) {
		    	$('#comment'+id+'Detail').html("<hr>");
				$.each($.parseJSON(data),function(idx, obj){
					$('#comment'+id+'Detail').append(obj.answerComment.content+" >>> "+obj.userName).append("<p>");
				}); 
		    }
		});
	}
	
</script>
<h2>标题：${detailVo.question.title }</h2>
<c:choose>
	<c:when test="${detailVo.isFocus }">
		<a onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/0')" href="#">已关注</a>&nbsp;&nbsp;${detailVo.question.focusCount }关注 <p>
	</c:when>
	<c:otherwise>
			<a onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/1')" href="#">关注</a>&nbsp;&nbsp;${detailVo.question.focusCount }关注 <p>
	</c:otherwise>
</c:choose>
	<a href="">收藏</a>&nbsp;&nbsp;${detailVo.question.favoriteCount }收藏，${detailVo.question.viewCount }浏览 <p>

<table>
	<tr height="40">
		<td>提问人：<a href="<c:url value='/u/${detailVo.userName }'></c:url>">${detailVo.userName }</a></td>
		<td>&nbsp;&nbsp;声望：${detailVo.reputation }</td>
		<td>&nbsp;&nbsp;提问时间：<fmt:formatDate value="${detailVo.question.createDate }" pattern="MM/dd HH:mm"/> </td>
		<td>&nbsp;</td>
	</tr>
</table>
<table>
	<tr>
		<td>
			<a href="#" onclick="vote('${baseURI}/q/${detailVo.question.questionId }/vote/1','确认要为它投赞同票吗？');">赞同</a><br>
			${detailVo.question.voteCount }<br>
			<a href="#" onclick="vote('${baseURI}/q/${detailVo.question.questionId }/vote/0','确认要为它投反对票吗？');">反对</a>
		</td>
		<td width="600" style="word-break:break-all; WORD-WRAP: break-word">内容:${detailVo.question.content }</td>
	</tr>
	<tr height="40">
		<td></td>
		<td>标签:${detailVo.question.tags}</td>
	</tr>
	<tr>
		<td></td>
		<td><a href="#" onclick="commentq('${baseURI}/q/${detailVo.question.questionId }/comment/list');">${detailVo.question.commentCount} 评论</a> &nbsp;&nbsp;  <a href="#">举报</a>
		<div id="qcomments" style="display: none">
			<div id="qcommentDetail">
			</div>
			<hr>
			<form action="<c:url value='/q/comment'></c:url>" method="post">
				<textarea rows="5" cols="50" name="content"></textarea><br>
				<input type="submit" value="提交评论">
				
				<input type="hidden" name="questionId" value="${detailVo.question.questionId }">
			</form>
		</div>
		</td>
	</tr>
</table>
<br>
${detailVo.question.answersCount }个回答
<hr>
<table border="1">
	<c:set var="k" value="1"></c:set>
	<c:forEach items="${ansPg.list }" var="ans" varStatus="status">
		<tr>
			<td width="60" align="center">
			<a href="#" onclick="vote('${baseURI}/answer/${ans.answer.answerId }/vote/1','确认要为它投赞同票吗？');">赞同</a><br>
			${ans.answer.voteCount }<br>
			<a href="#" onclick="vote('${baseURI}/answer/${ans.answer.answerId }/vote/0','确认要为它投反对票吗？');">反对</a>
			</td>
			<td width="460">
			${ ans.answer.content}<p>
			回答人:<a href="<c:url value='/u/${ans.userName }'></c:url>">${ans.userName }</a>&nbsp;&nbsp;声望:${ans.reputation }&nbsp;&nbsp;<fmt:formatDate value="${ans.answer.createDate }" pattern="MM/dd HH:mm"/> 
			</td>
			<td width="80">头像:${ans.imgUrl }</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<c:choose>
					<c:when test="${currUserId==detailVo.question.userId }">
						<c:choose>
							<c:when test="${detailVo.question.questionStatus==0}">
								<a href="#" onclick="adoptq('${baseURI}/answer/${ans.answer.answerId }/adopt/1','确认要采纳该答案吗？');" >采纳答案</a>
							</c:when>
							<c:when test="${ans.answer.isAdoption }">
								<a href="#" onclick="adoptq('${baseURI}/answer/${ans.answer.answerId }/adopt/0','确认要取消采纳该答案吗？');" >取消采纳</a>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${ans.answer.isAdoption }">
						已采纳
					</c:when>
				</c:choose>
				
				&nbsp;&nbsp;<a href="#" onclick="commenta('${baseURI}/answer/${ans.answer.answerId  }/comment/list',${status.count});">${ans.answer.commentCount } 评论</a> &nbsp;&nbsp;  <a href="#">举报</a>
				<div id="comment${status.count}" style="display: none">
					<div id="comment${status.count}Detail">
					</div>
					<hr>
					<form id="comment${status.count}Form">
						<textarea rows="5" cols="50" name="content"></textarea><br>
						<input type="hidden" name="answerId" value="${ans.answer.answerId }">
					</form>
					<input type="button" value="提交按钮" onclick="postComment(${status.count});">
				</div>
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