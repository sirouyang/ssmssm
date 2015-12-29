<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="com.ssmssm.core.utils.*"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>Insert title here</title>
<link href="${ctx}/res/strength-meter/css/strength-meter.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>User Profile</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li class="active">用户管理</li>
		</ol>
	</section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="mytable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>id</th>
                  <th>userName</th>
                  <th>userPsw</th>
                  <th>userType</th>
                  <th>userEnabled</th>
                  <th>fullName</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>id</th>
                  <th>userName</th>
                  <th>userPsw</th>
                  <th>userType</th>
                  <th>userEnabled</th>
                  <th>fullName</th>
                  <th>操作</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    <!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<!-- form start -->
				<form class="form-horizontal">
					<div class="box-body">
						<div class="form-group">
							<label for="lblUserName" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="txtUserName"
									placeholder="用户名" maxlength="10" />
							</div>
						</div>
						<div class="form-group">
							<label for="lblUserPsw" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control strength" id="txtUserPsw"
									placeholder="密码" maxlength="20" />
							</div>
						</div>
						<div class="form-group">
							<label for="lblUserPswConfirm" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control strength"
									id="txtUserPswConfirm" placeholder="确认密码" maxlength="20" />
							</div>
						</div>
						<div class="form-group">
							<label for="lblFullName" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="txtFullName"
									placeholder="姓名" maxlength="20" />
							</div>
						</div>
						<div class="form-group">
							<label for="lblSex" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-5 radio">
								<label> <input type="radio" name="radSex"
									id="radSex1" value="<%=ComConst.SEX_1001%>" checked />男
								</label>
							</div>
							<div class="col-sm-5 radio">
								<label> <input type="radio" name="radSex"
									id="radSex2" value="<%=ComConst.SEX_1002%>" />女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="lblEmail" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="txtEmail"
									placeholder="邮箱" maxlength="50" />
							</div>
						</div>
						<div class="form-group">
							<label for="lblMobilePhone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="txtMobilePhone"
									placeholder="手机" data-inputmask='"mask": "999 9999 9999"' data-mask />
							</div>
						</div>
						<div class="form-group">
							<label for="lblComments" class="col-sm-2 control-label">备注</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="3" placeholder="备注"
									id="txtComments" maxlength="100" ></textarea>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<input type="hidden" id="userId" /> <input type="hidden"
							id="mode" value="I" />
						<button type="submit" class="btn btn-info pull-right" id="save">保存</button>
					</div>
					<!-- /.box-footer -->
				</form>
			</div>
		</div>
	</div>

	<script src="${ctx}/res/js/system/user_index.js"></script>
	<script src="${ctx}/res/strength-meter/js/strength-meter.js" type="text/javascript"></script>
    <script src="${ctx}/res/strength-meter/js/locales/strength-meter-zh-CN.js" type="text/javascript"></script>
    <script>
    	var commonCodeList = ${commonCodeList};
    </script>
</body>
</html>