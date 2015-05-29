<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>关注用户</title>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<div class="media">
			<div class="media-left media-middle">
				<a href="#"> <img src="${baseURI }/image/doctor.png"
					height="60px" width="60px" alt="..." class="img-circle">
				</a>
			</div>
			<div class="media-body media-middle">
				<h2 class="media-heading">李梅梅</h2>
			</div>
			<div class="media-right media-middle">
				<span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span>
		  	</div>
		</div>
		<hr>
	</div>
	<div class="container-fluid">
		<div class="media">
		  <div class="media-left">
		    <a href="#"> <img src="${baseURI }/image/doctor.png"
					height="40px" width="40px" alt="..." class="img-circle">
				</a>
		  </div>
		  <div class="media-body media-middle">
		  	<div class="alert alert-success" role="alert">请问医生最近一直头疼、晕眩。颈椎也特别不舒服。晚上睡觉的时候也睡不好。</div>
		  </div>
		</div>
	
		<div class="media">
			<div class="media-body media-middle">
		   <div class="alert alert-info" role="alert">是不是感冒了？</div>
		  </div>
		  <div class="media-right">
		    <a href="#"> <img src="${baseURI }/image/doctor.png"
					height="40px" width="40px" alt="..." class="img-circle">
				</a>
		  </div>
		</div>
		
		<div class="media">
		  <div class="media-left">
		    <a href="#"> <img src="${baseURI }/image/doctor.png"
					height="40px" width="40px" alt="..." class="img-circle">
				</a>
		  </div>
		  <div class="media-body media-middle">
		  	<div class="alert alert-success" role="alert">我也不知道怎么回事啊，也不发烧。能不能给开点药啊？</div>
		  </div>
		</div>
		<div class="media">
			<div class="media-body media-middle">
		   <div class="alert alert-info" role="alert">不能随便开药，需要先确诊的。建议去医院检查一下吧。</div>
		  </div>
		  <div class="media-right">
		    <a href="#"> <img src="${baseURI }/image/doctor.png"
					height="40px" width="40px" alt="..." class="img-circle">
				</a>
		  </div>
		</div>
	</div>
	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="media">
				  <div class="media-left">
				  	<button type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
					</button>
				  </div>
				  <div class="media-body">
				    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter message">
				  </div>
				  <div class="media-right">
				  	<button type="button" class="btn btn-default">
							发送
					</button>
				  </div>
			</div>
	 	 </div>
 	</nav>
</body>
</html>