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
  <c:if test="${not empty msg }">
  	<div class="alert alert-warning alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>Warning!</strong> ${msg }
	</div>
  </c:if>
	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>
	  
    <div class="row">
    	<div class="col-md-9">
    		<div class="container-fluid">
    		 <h4>亲，您或您的家人和朋友哪儿不舒服呢？<button type="button" class="btn btn-primary navbar-btn">立即提问</button></h4>
		    <div role="tabpanel">
			  <!-- Nav tabs -->
			  <ul class="nav nav-tabs" role="tablist" id="myTab">
			    <li role="presentation" <c:if test="${type=='newest' }">class="active"</c:if> > <a href="#newest" onclick="changeTab('newest');" aria-controls="newest" role="tab" data-toggle="tab"><strong>最新的</strong></a></li>
			    <li role="presentation" <c:if test="${type=='hottest' }">class="active"</c:if> ><a href="#hottest" onclick="changeTab('hottest');" aria-controls="hottest" role="tab" data-toggle="tab"><strong>热门的</strong></a></li>
			    <li role="presentation" <c:if test="${type=='unanswered' }">class="active"</c:if> > <a href="#unanswered" onclick="changeTab('unanswered');" aria-controls="unanswered" role="tab" data-toggle="tab"><strong>未回答</strong></a></li>
			  </ul>
			  <script type="text/javascript">
			  	function changeTab(type){
			  		window.location.href="${baseURI}/question/"+type;			  		
			  	}
			  </script>
			 <span id="tableContent" style="display: none">
						<table class="table table-striped">
							<c:forEach items="${pagination.list }" var="q">
								<tr>
								  <td class="kbb-item">
								  	${q.voteCount}<br>投票
								  </td>
								  <td class="kbb-item">
									  	<c:choose>
									  		<c:when test="${q.questionStatus==1}">
									  			<div class="kbb-item-answered">
										 		 ${q.answersCount }<br>
									  			解决
									  			</div>
									  		</c:when>
									  		<c:when test="${q.answersCount>0&&q.questionStatus!=1}">
									  			<div class="kbb-item-answer">
										 		 ${q.answersCount }<br>
									  			回答
									  			</div>
									  		</c:when>
									  		<c:otherwise>
										  		<div class="kbb-item-unanswer">
										 		 ${q.answersCount }<br>
									  			回答
									  			</div>
									  		</c:otherwise>
									  	</c:choose>
									 </div>
								  </td>
								  <td class="kbb-item">${q.viewCount }<br>浏览</td>
								  <td class="kbb-itemcontent">							  		
								  		<h5><small>lover 2天前回答</small></h5>
										<strong><a href="<c:url value='/q/${q.questionId }'/>">${q.title}</a></strong> -- <fmt:formatDate value="${q.createDate }" pattern="MM-dd HH:mm"/>
										<c:forEach items="${fn:split(q.tags,',') }" var="tag">
											<span class="label label-success">${tag }</span>
										</c:forEach>
								  </td>
								</tr>
							</c:forEach>
						</table>
						
						<!-- c:if test="${pagination.totalPage>1}"-->
							<nav style="text-align: center;">
							  <ul class="pagination ">
							    <li <c:if test="${pagination.pageNo==1 }"> class="disabled"</c:if>>
							      <a href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    <c:forEach var="k" begin="1" end="${pagination.totalPage}">
								    <li <c:if test="${pagination.pageNo==k}"> class="active"</c:if>><a href="#">${k }</a></li>
							    </c:forEach>
							    <li <c:if test="${pagination.pageNo>=pagination.totalPage }"> class="disabled"</c:if>>
							      <a href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</nav>
						<!-- /c:if -->
					</span>
			  
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane <c:if test="${type=='newest' }">active</c:if>" id="newest">
			      	<c:if test="${type=='newest' }">
						<script type="text/javascript">
							$('#newest').html($('#tableContent').html());
						</script>
					</c:if>
				</div>
			    <div role="tabpanel" class="tab-pane <c:if test="${type=='hottest' }">active</c:if>" id="hottest">
					<c:if test="${type=='hottest' }">
						<script type="text/javascript">
							$('#hottest').html($('#tableContent').html());
						</script>
					</c:if>
				</div>
			    <div role="tabpanel" class="tab-pane <c:if test="${type=='unanswered' }">active</c:if>" id="unanswered">
					<c:if test="${type=='unanswered' }">
						<script type="text/javascript">
							$('#unanswered').html($('#tableContent').html());
						</script>
					</c:if>
				</div>
			  </div>
			</div>
    	</div>
    	</div>
    	<div class="col-md-3">
    	
   		<div class="container-fluid">
    		<div class="well">
    			<h4>最可信的医生声望榜</h4>
    			<p>最直接的病情咨询</p>
    			<p>最真诚的病友交流</p>
    		</div>
    	
   		  <ul class="list-group">
		    <a href="#" class="list-group-item">我的草稿<span class="badge">2</span></a>
		    <a href="#" class="list-group-item">我的收藏</a>
		    <a href="#" class="list-group-item">我关注的问题</a>
		    <a href="#" class="list-group-item">邀请我回答的</a>
		    <a href="#" class="list-group-item">邀请朋友加入</a>
		  </ul>

		  <div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">关注 3 个标签</h3>
			  </div>
			  <div class="panel-body">
			  	<h4>
			  	<a href=""><span class="label label-success">内科</span></a>
			  	<a href=""><span class="label label-success">外科</span></a>
			  	<span class="label label-success">神经内科</span></h4>
		  	  </div>
		  </div>
			<div class="panel panel-default">
			  <div class="panel-heading">
			  	<h3 class="panel-title">热门标签</h3>
			  </div>
			  <div class="panel-body">
			  	<h4><span class="label label-success">内科</span>
			  	<span class="label label-success">外科</span>
			  	<span class="label label-success">神经内科</span>
			  	</h4>
		  	  </div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">最近热门的</h3>
				</div>
				<div class="panel-body">
					<p><a herf="#">微信:用户点击链接或者图文，会推送给服务端一个事件么？如果有怎么捕获？</a><small>3 回答</small></p>
					<p><a herf="#">微信支付 不允许跨号支付问题</a> <small>6 回答</small></p>
				</div>
			</div>
    	</div>
    </div>
  </div>

<jsp:include page="/WEB-INF/jsp/common/bottom.jsp"></jsp:include>
  </body>
</html>