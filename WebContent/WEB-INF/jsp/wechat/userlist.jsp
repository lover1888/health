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
	<div class="container" style="text-align: center;">
		<div class="row" style="border-bottom: 1px solid #f0f0f0">
			<div class="col-xs-6">
				<p style="font-size: 32px;">2</p>
				<p style="font-size: 16px;">正在关注</p>
			</div>
			<div class="col-xs-6" style="background-color: #428bca;">
				<p style="font-size: 32px;">0</p>
				<p style="font-size: 16px;">请求关注</p>
			</div>
		</div>
	</div>
	<div class="container" style="height: 10px;"></div>
	<div class="container">
		<div class="media">
			<div class="media-left media-middle">
				<a href="#"> <img src="${baseURI }/image/doctor.png"
					height="80px" width="80px" alt="..." class="img-circle">
				</a>
			</div>
			<div class="media-body media-middle">
				<a href="${baseURI }/wx/user/info">
					<h5 class="media-heading"><strong>李梅</strong> <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>上海</h5>
					<p>女，28岁</p>
				</a>
			</div>
			<div class="media-right media-middle">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
			</div>
		</div>
		<hr>
		<div class="media">
			<div class="media-left media-middle">
				<a href="#"> <img src="${baseURI }/image/doctor.png"
					height="80px" width="80px" alt="..." class="img-circle">
				</a>
			</div>
			<div class="media-body media-middle">
				<h5 class="media-heading"><strong>Shiva</strong> <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>上海</h5>
				<p>女，28岁</p>
			</div>
			<div class="media-right media-middle">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
			</div>
		</div>
		<hr>
	</div>
</body>
</html>