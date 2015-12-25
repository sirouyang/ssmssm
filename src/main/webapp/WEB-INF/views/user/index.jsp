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
    
    <script src="${ctx}/res/js/system/user_index.js"></script>
    <script>
    	var commonCodeList = ${commonCodeList};
    </script>
</body>
</html>