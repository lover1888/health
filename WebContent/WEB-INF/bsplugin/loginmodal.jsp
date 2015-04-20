<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<!-- loginModal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="loginModalLabel"><strong>登录</strong></h4>
      </div>
      <div class="modal-body">
			<div class="row container-fluid">
				<div class="col-md-6">
					<div class="container-fluid">
						<h1><small><strong>创建新帐号</strong></small></h1>
						<form>
							<div class="form-group">
								<label for="userName">用户名</label>
								<input type="text" class="form-control" id="userName" placeholder="字母、数字等，用户名唯一">
							</div>
							<div class="form-group">
								<label for="email">Email</label>
								<input type="email" class="form-control" id="email" placeholder="hi@kanbingba.cn">
							</div>
							<div class="form-group">
								<label for="password">密码</label>
								<input type="password" class="form-control" id="password" placeholder="不少于6位">
							</div>
							<label>同意并接受<a href="">《服务条款》</a></label>
							<button type="submit" class="btn btn-primary">注册</button>
						</form>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="container-fluid">
						<h1><small><strong>用户登录</strong></small></h1>
						<form>
							<div class="form-group">
								<label for="email">Email</label>
								<input type="email" class="form-control" id="email" placeholder="hi@kanbingba.cn">
							</div>
							<div class="form-group">
								<label for="password">密码</label>
								<input type="password" class="form-control" id="password" placeholder="不少于6位">
							</div>
							 <div class="checkbox">
							    <label>
							      <input type="checkbox">记住登录状态
							    </label>
							  </div>
							<button type="submit" class="btn btn-primary">登录</button>
						</form>
					</div>
				</div>
			</div>			      	
      	
      </div>
      <hr class="kbb-hr">
      <div class="container-fluid" style="text-align: center;">
      	<p>
      	<a href="">忘记密码</a><br><br>
      	</p>
      </div>
      			      
    </div>
  </div>
</div>