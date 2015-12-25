$(function() {
	var userTypeList = $.grep(commonCodeList, function(objdata, i) {
		return objdata.codeId == 100004;
	});
	var userType;
	paramUrl = '/user/getUserListPagination',
	paramColumns.push({"data" : "id"});
	paramColumns.push({"data" : "userName"});
	paramColumns.push({"data" : "userPsw"});
	paramColumns.push({"data" : "userType"});
	paramColumns.push({"data" : "userEnabled"});
	paramColumns.push({"data" : "fullName"});
	paramColumns.push({"data" : null});// 此列不绑定数据源，用来显示操作
	paramColumnDefs.push({
		"render" : function(data, type, row) {
			var staticResop = "";
			staticResop = staticResop
					+ "<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"funUpd(\'"
					+ row["id"] + "\')\">修改</button>"
			staticResop = staticResop
					+ "<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"funUpd(\'"
					+ row["id"] + "\')\">删除</button>"
			return staticResop;
		},
		"targets" : 6
	});
	paramColumnDefs.push({
		"visible" : false,
		"targets" : [ 4 ]
	});
	paramColumnDefs.push({
		"render" : function(data, type, row) {
			userType = $.grep(userTypeList, function(objdata, i) {
				return objdata.codeDtlId == data;
			});
			return userType[0].codeDtlName;
		},
		"targets" : 3
	});
	datatablesInit();
//	// 前台添加序号
//	t.on('order.dt search.dt', function() {
//		t.column(0, {
//			"search" : 'applied',
//			"order" : 'applied'
//		}).nodes().each(function(cell, i) {
//			cell.innerHTML = i + 1;
//		});
//	}).draw();
});

/**
 *添加按钮
 **/
function funAdd() {
	$("#myModal").modal("show");
}

/**
 *编辑按钮
 **/
function funUpd(id) {
	$("#userId").val(id);
	$("#mode").val("U");
	$("#myModal").modal("show");
}
/**
 *删除按钮
 **/
function funDel(id) {
	$("#userId").val(id);
	$("#mode").val("D");
	$("#myModal").modal("show");
}

