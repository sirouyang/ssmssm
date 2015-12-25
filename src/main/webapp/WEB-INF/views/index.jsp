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
<script src="${ctx}/res/js/jquery-2.1.4/jquery-2.1.4.min.js"></script>
<script src="${ctx}/res/js/system/index.js"></script>
</head>
<body>
	<a href="${ctx}/user/index">跳转到用户</a>
	中文测试
</body>
</html>