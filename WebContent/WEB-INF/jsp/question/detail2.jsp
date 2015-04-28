<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>  
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>看病吧-最专业的看病问答社区</title>
    <jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
  </head>
  <body>

  <div class="container">
	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>
	
    <div class="row">
    	<div class="col-md-9">
    		<div class="container-fluid">
	    		<p class="lead"><strong>${detailVo.question.title }</strong></p>
	    		<h4>
		    		<img src="${baseURI }/image/default.jpg" alt="${detailVo.userName }" class="img-circle"> 
		    		<a href="<c:url value='/u/${detailVo.userName }'></c:url>"><strong>${detailVo.userName }</strong></a>
		    		<strong>${detailVo.reputation }</strong>&nbsp;
		    		<small><fmt:formatDate value="${detailVo.question.createDate }" pattern="yyyy/MM/dd HH:mm"/> 提问</small>
	    		</h4>
	    		<div class="row">
	    			<div class="col-md-1" >
	    				<p class="text-center">
			    			<button type="button" class="btn btn-default btn-xs">
			    				<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
			    			</button>
			    			<br><strong>${detailVo.question.voteCount }</strong><br>
			    			<button type="button" class="btn btn-default btn-xs">
			    				<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
			    			</button>
		    			</p>
	    			</div>
	    			<div class="col-md-11" style="vertical-align: center;">
	    				<p class="text-left"><strong>${detailVo.question.content }</strong></p>
	    				<c:forEach items="${fn:split(detailVo.question.tags,',') }" var="tag">
							<span class="label label-success">${tag }</span>
						</c:forEach>
						<br><br>
						<div class="btn-group btn-group-xs" role="group" aria-label="...">
						  <button type="button" class="btn btn-default" onclick="showComment('#commentContent','${baseURI}/q/${detailVo.question.questionId }/comment/list');">${detailVo.question.commentCount} 评论</button>
						  <div class="btn-group btn-group-xs" role="group">
						    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						      更多
						      <span class="caret"></span>
						    </button>
						    <ul class="dropdown-menu" role="menu">
						      <li><a href="#">举报</a></li>
						      <li><a href="#">编辑</a></li>
						      <li><a href="#">删除</a></li>
						    </ul>
						  </div>
						</div>
						<script type="text/javascript">
							var isCommentShow = false;
							function showComment(viewId, urlStr){
								isCommentShow = !isCommentShow;
								if(isCommentShow){
									$.ajax({
										url:urlStr,
										success:function(data){
											$('#commentDetail').html("");
											$.each($.parseJSON(data),function(idx, obj){
												$('#commentDetail').append(obj.comment.content+'<br>');
												$('#commentDetail').append(obj.userName+'&nbsp;');
												$('#commentDetail').append(obj.reputation+'&nbsp;');
												$('#commentDetail').append('<small>'+obj.comment.createDate+'</small>');
												$('#commentDetail').append('&nbsp;回复<hr>');
											}); 
											$(viewId).html("");
											$(viewId).html($('#commentContentTmp').html());
											$(viewId).show();
										}
									});
								} else {
									$(viewId).hide();
								}
							}
						</script>
						<span id="commentContent"></span>
						<span id="commentContentTmp" style="display: none">
							<div class="panel panel-default">
							  <div class="panel-body">
							  	<span id="commentDetail"></span>
							  	<form>
  										<textarea class="form-control" rows="3" cols="50" placeholder="添加评论"></textarea>
  										<button class="btn btn-primary btn-sm" type="submit">提交评论</button>
								</form>
							  </div>
							</div>
						</span>
	    			</div>
	    		</div>
				<p><br></p>
	    		<h4>${detailVo.question.answersCount }个回答</h4>
	    		<hr>
	    		
	    		<c:forEach items="${ansPg.list }" var="ans" varStatus="status">
					<div class="row">
						<div class="col-md-1" >
							<p class="text-center">
				    			<button type="button" class="btn btn-default btn-xs">
				    				<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				    			</button>
				    			<br><strong>${ans.answer.voteCount }</strong><br>
				    			<button type="button" class="btn btn-default btn-xs">
				    				<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
				    			</button>
			    			</p>
						</div>
						<div class="col-md-11">
							<h4>
								<img src="${baseURI }/image/default.jpg" alt="${detailVo.userName }" class="img-circle">
								<a href="<c:url value='/u/${ans.userName }'></c:url>">${ans.userName }</a>
								<strong>${ans.reputation }</strong>&nbsp;
								<small><fmt:formatDate value="${ans.answer.createDate }" pattern="yyyy/MM/dd HH:mm"/>回答</small> 
							</h4>
							<p>
								${ans.answer.content}
							</p>
							<br>
							<div class="btn-group btn-group-xs" role="group" aria-label="...">
							  <button type="button" class="btn btn-default" onclick="showComment('#ansComment${status.count}','${baseURI}/answer/${ans.answer.answerId  }/comment/list');">${ans.answer.commentCount } 评论</button>
							  <div class="btn-group btn-group-xs" role="group">
							    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							      更多
							      <span class="caret"></span>
							    </button>
							    <ul class="dropdown-menu" role="menu">
							      <li><a href="#">举报</a></li>
							      <li><a href="#">编辑</a></li>
							      <li><a href="#">删除</a></li>
							    </ul>
							  </div>
							</div>
							<span id="ansComment${status.count}" style="display: none">
							</span>
						</div>
					</div>
					<hr>
				</c:forEach>
				<h4>撰写答案</h4>
				<form action="<c:url value='/q/answer'></c:url>" method="post">
					<textarea class="form-control xheditor" rows="8" cols="100" name="content" placeholder="撰写答案..."></textarea>
					<input type="hidden" name="questionId" value="${detailVo.question.questionId }"><br><br>
					<button type="submit" class="btn btn-primary">提交回答</button>
				</form>
    		</div>
    		
    		
    		
    	</div>
    	<div class="col-md-3">
    		<div class="container-fluid">
    			<p>
	    		<c:choose>
					<c:when test="${detailVo.isFocus }">
						<button type="button" class="btn btn-primary"  onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/0')">已关注</button>&nbsp;&nbsp;<strong>${detailVo.question.focusCount }关注</strong>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary"  onClick="focusq('${baseURI}/q/${detailVo.question.questionId }/focus/1')">关注</button>&nbsp;&nbsp;<strong>${detailVo.question.focusCount }关注</strong>
					</c:otherwise>
				</c:choose>
				</p>
				<p>
				<button type="button" class="btn btn-default">收藏</button>&nbsp;&nbsp;<strong>${detailVo.question.favoriteCount }收藏，${detailVo.question.viewCount }浏览</strong>
				</p> 
    		</div>
    	</div>
    </div>
  </div>
  <jsp:include page="/WEB-INF/jsp/common/bottom.jsp"></jsp:include>
  </body>
</html>