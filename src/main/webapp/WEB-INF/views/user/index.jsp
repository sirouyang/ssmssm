<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>Insert title here</title>
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
				<div class="modal-body">
					<div class="form-group">
						<input type="text" class="form-control" id="name" placeholder="姓名">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="position"
							placeholder="位置">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="salary"
							placeholder="薪资">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="start_date"
							placeholder="时间" data-date-format="yyyy/mm/dd">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="office"
							placeholder="工作地点">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="extn" placeholder="编号">
					</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="userId" />
					<input type="hidden" id="mode" value="I" />
					<button type="button" class="btn btn-info" id="initData">添加模拟数据</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="save">保存</button>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/res/js/system/user_index.js"></script>
    <script>
    	var commonCodeList = ${commonCodeList};
    </script>
</body>
</html>