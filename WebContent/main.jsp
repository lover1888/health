<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>看病吧-最专业的看病问答社区</title>
    <link rel="shortcut icon" href="favicon.ico" type=”image/x-icon” />
    <!-- Bootstrap -->
    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>
  </head>
  <body>

  <div class="container">
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">看病问答社区</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#" style="font-size: 30px"> 看病吧 </a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#"> 问答 <span class="sr-only">(current)</span></a></li>
	        <li><a href="#"> 文章 </a></li>
	        <li><a href="#"> 活动 </a></li>
	        <li><a href="#"> 标签 </a></li>
	        <li><a href="#"> 榜单 </a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">更多... <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="#">子站</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="navbar-form navbar-left" role="search">
	      	<div class="input-group">
		      <input type="text" class="form-control" placeholder="输入关键字搜索">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit">Go!</button>
		      </span>
		    </div>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	        	
		        <button class="btn btn-link navbar-btn btn-sm" type="button">
				  消息<span class="badge">1</span>
				</button>
	        </li>
	        <li><button type="button" class="btn btn-primary navbar-btn"  data-toggle="modal" data-target="#loginModal">立即登录</button></li>
	        
	        <jsp:include page="/WEB-INF/bsplugin/loginmodal.jsp"></jsp:include>
	        
	        
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	  
    <div class="row">
    	<div class="col-md-9">
    		<div class="container-fluid">
    		 <h4>亲，您或您的家人和朋友哪儿不舒服呢？<button type="button" class="btn btn-primary navbar-btn">立即提问</button></h4>
		    <div role="tabpanel">
			  <!-- Nav tabs -->
			  <ul class="nav nav-tabs" role="tablist" id="myTab">
			    <li role="presentation" class="active"><a href="#newest" aria-controls="newest" role="tab" data-toggle="tab">最新的</a></li>
			    <li role="presentation"><a href="#hottest" aria-controls="hottest" role="tab" data-toggle="tab">热门的</a></li>
			    <li role="presentation"><a href="#unanswered" aria-controls="unanswered" role="tab" data-toggle="tab">未回答</a></li>
			  </ul>
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="newest">
					<table class="table table-striped">
						<tr>
						  <td class="kbb-item">0<br>投票</td>
						  <td class="kbb-item">2<br>回答</td>
						  <td class="kbb-item">43<br>浏览</td>
						  <td class="kbb-itemcontent">JavaEE 收费附件下载设计思路是什么 <span class="label label-success">内科</span>  <span class="label label-success">神经内科</span></td>
						</tr>
						<tr>
						  <td class="kbb-item">0<br>投票</td>
						  <td class="kbb-item">2<br>回答</td>
						  <td class="kbb-item">43<br>浏览</td>
						  <td class="kbb-itemcontent">JavaEE 收费附件下载设计思路是什么 <span class="label label-success">内科</span>  <span class="label label-success">神经内科</span></td>
						</tr>
						<tr>
						  <td class="kbb-item">0<br>投票</td>
						  <td class="kbb-item">2<br>回答</td>
						  <td class="kbb-item">43<br>浏览</td>
						  <td class="kbb-itemcontent">JavaEE 收费附件下载设计思路是什么 <span class="label label-success">内科</span></td>
						</tr>
					</table>
					<div class="alert alert-warning alert-dismissible" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>Warning!</strong> Better check yourself, you're not looking too good.
					</div>
				
					<nav style="text-align: center;">
					  <ul class="pagination ">
					    <li class="disabled">
					      <a href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <li class="active"><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#">...</a></li>
					    <li>
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>
				</div>
			    <div role="tabpanel" class="tab-pane" id="hottest">
					<ul class="list-group">
					    <a href="#" class="list-group-item">我的草稿<span class="badge">2</span></a>
					    <a href="#" class="list-group-item">我的收藏</a>
					    <a href="#" class="list-group-item">我关注的问题</a>
					    <a href="#" class="list-group-item">邀请我回答的</a>
					    <a href="#" class="list-group-item">邀请朋友加入</a>
					</ul>

				</div>
			    <div role="tabpanel" class="tab-pane" id="unanswered">未回答的</div>
			  </div>
			  <script type="text/javascript">
			 	 $('#myTab a').click(function (e) {
				  //e.preventDefault();
				  //$(this).tab('show');
				});
			  </script>
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
			
			<div class="center-block">居中显示 吗？</div>
			</div>
    	</div>
    </div>
  </div>

  <div class="container">
  	<hr class="kbb-hr">
  	<p></p>
  	<p class="text-muted  text-center">
  		Copyright © 2015 看病吧.<br>
		浙ICP备15001234号
	</p>
  </div>
  </body>
</html>