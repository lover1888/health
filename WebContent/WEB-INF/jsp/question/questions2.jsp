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
	<div class="container-fluid">
		<h3>提问题</h3>
		<form action="" class="">
		
		
		</form>
		
		
	</div>  
   
  </div>

<jsp:include page="/WEB-INF/jsp/common/bottom.jsp"></jsp:include>
  </body>
</html>