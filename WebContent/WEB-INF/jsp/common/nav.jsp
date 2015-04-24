<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#kbb-navbar">
        <span class="sr-only">看病问答社区</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value='/question'></c:url>" style="font-size: 30px;">看病吧</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="kbb-navbar">
      <ul class="nav navbar-nav" id="kbb-nav-item">
        <li><a href="<c:url value='/question' />"><strong>问答</strong></a></li>
        <li><a href="#"> <strong>文章</strong> </a></li>
        <li><a href="#"> <strong>活动</strong> </a></li>
        <li><a href="#"> <strong>标签</strong> </a></li>
        <li><a href="#"> <strong>榜单</strong> </a></li>
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
      <script type="text/javascript">
     	 $('#kbb-nav-item li').hover(function (e) {
     		 $('#kbb-nav-item li').removeClass('active');
     		 $(this).addClass('active');
    	})
      </script>
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
	        <button class="btn btn-default navbar-btn" type="button">
  				<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> <span class="badge">1</span>
			</button>&nbsp;
        </li>
        <li><button type="button" class="btn btn-primary navbar-btn"  data-toggle="modal" data-target="#loginModal">立即登录</button></li>
        <jsp:include page="/WEB-INF/bsplugin/loginmodal.jsp"></jsp:include>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

