$(function() {
	initDataTables();
	
	$("#userPsw").strength({language: 'zh-CN'});
	$("#userPswConfirm").strength({language: 'zh-CN'});
	
	// modal事件
	$("#btnSave").click(function() {
		funSave();
	})
});

function initDataTables() {
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
}
/**
 * 添加按钮
 **/
function funAdd() {
	$("#hdnUserId").val("");
	$("#hdnMode").val("I");
	$("#myModal").modal("show");
};

/**
 * 编辑按钮
 **/
function funUpd(id) {
	$("#hdnUserId").val(id);
	$("#hdnMode").val("U");
	$("#myModal").modal("show");
};

/**
 * 删除按钮
 **/
function funDel(id) {
	$("#hdnUserId").val(id);
	$("#hdnMode").val("D");
	$("#myModal").modal("show");
};

/**
 * 保存按钮
 **/
function funSave() {
	ajaxPost('/user/insertUser', $("#subform").serialize(), function(data,
			status) {
		funCallback(data, status);
	});
};
function funCallback(data, status) {
	if (data.RESULT_CODE == RESULT_CODE_SUCCESS) {
		$("#myModal").modal("hide");
		$("#mytable").dataTable().fnReloadAjax();
	}
};
