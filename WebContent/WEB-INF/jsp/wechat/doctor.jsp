<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>  
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>医生管理</title>
    <jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
  </head>
  <body>
  <div class="container" style="background-color: gray;text-align: center;">
  	<div class="row">
  		<div class="col-xs-12" >
			<img src="${baseURI }/image/doctor.png" height="120px" width="120px" alt="..." class="img-circle">
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-xs-12" >
			<h4>李一铭</h4>
  		</div>
  	</div>
  </div>
   <div class="container" style="text-align: center;">
  	<div class="row">
  		<div class="col-xs-4" style="border-right: 1px solid #f0f0f0;">
  			<a href="<c:url value='/wx/users' />"><h4>关注患者</h4></a>
  			<p style="font-size: 24px;"><a href="<c:url value='/wx/users' />">23</a></p>
  		</div>
  		<div class="col-xs-4" style="border-right: 1px solid #f0f0f0;">
  			<h4>未读消息</h4>
  			<p style="font-size: 24px;">3</p>
  		</div>
  		<div class="col-xs-4">
  			<h4>已完成会诊</h4>
  			<p style="font-size: 24px;">14</p>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-xs-4" style="height: 5px;background-color: #428bca;" >
  		</div>
  		<div class="col-xs-4" style="height: 5px;background-color: #d9534f;" >
  		</div>
  		<div class="col-xs-4" style="height: 5px;background-color: #5cb85c;" >
  		</div>
  	</div>
  </div>
  <div class="container">
  	<div class="row">
  		<div class="col-xs-4">
  			<h2><small>最新消息</small></h2>
  		</div>
  	</div>
	<div class="row" style="text-align: center;">
		<div class="container-fluid">
		</div>
	</div>  	
  </div>
  <div class="container style="text-align: center;">
  	<div class="row">
  		<div class="col-xs-3">
  			<img src="${baseURI }/image/doctor.png" height="80px" width="80px" alt="..." class="img-circle">
  		</div>
  		<div class="col-xs-9">
  			<p><strong>小敏</strong> <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>上海 女，28岁 </p>
  			<a href="<c:url value='/wx/user/chat' />"><p><strong>问：</strong>请问医生最近一直头疼、晕眩。颈椎也特别不舒服。晚上睡觉的时候也睡不好。</p></a>
  			<p>2014/05/21</p>
  			<hr>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-xs-3">
  			<img src="${baseURI }/image/doctor.png" height="80px" width="80px" alt="..." class="img-circle">
  		</div>
  		<div class="col-xs-9">
  			<p><strong>小敏</strong> <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>上海 女，28岁 </p>
  			<p><strong>问：</strong>请问医生最近一直头疼、晕眩。颈椎也特别不舒服。晚上睡觉的时候也睡不好。</p>
  			<p>2014/05/21</p>
  			<hr>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-xs-3">
  			<img src="${baseURI }/image/doctor.png" height="80px" width="80px" alt="..." class="img-circle">
  		</div>
  		<div class="col-xs-9">
  			<p><strong>小敏</strong> <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>上海 女，28岁 </p>
  			<p><strong>问：</strong>请问医生最近一直头疼、晕眩。颈椎也特别不舒服。晚上睡觉的时候也睡不好。</p>
  			<p>2014/05/21</p>
  			<hr>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-xs-12">
  			<button class="btn btn-info btn-lg btn-block" type="button">查看全部问题</button>
  		</div>
  	</div>
  </div>
  </body>
</html>