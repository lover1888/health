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
	  
    <div class="container-fluid">
    	<form action="">
			<div class="form-group">
		    	<label for="exampleInputEmail1">Email address</label>
		    	<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
		  	</div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputFile">File input</label>
			    <input type="file" id="exampleInputFile">
			    <p class="help-block">Example block-level help text here.</p>
			  </div>
			  <div class="checkbox">
			    <label>
			      <input type="checkbox"> Check me out
			    </label>
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>    	
    	
    	</form>
    </div>
  </div>
  <jsp:include page="/WEB-INF/jsp/common/bottom.jsp"></jsp:include>
  </body>
</html>