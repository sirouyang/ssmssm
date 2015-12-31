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
					+ row["id"] + "\')\">" + OPERATE_MODENAME_U + "</button>"
			staticResop = staticResop
					+ "<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"funDel(\'"
					+ row["id"] + "\')\">" + OPERATE_MODENAME_D + "</button>"
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
	$("#id").val("");
	$("#hdnMode").val(OPERATE_MODE_I);
	$("#myModalLabel")[0].innerHTML = OPERATE_MODENAME_I;
	initDisable();
	$("#myModal").modal("show");
};

/**
 * 编辑按钮
 **/
function funUpd(id) {
	$("#id").val(id);
	$("#hdnMode").val(OPERATE_MODE_U);
	$("#myModalLabel")[0].innerHTML = OPERATE_MODENAME_U;
	initDisable();
	$("#myModal").modal("show");
};

/**
 * 删除按钮
 **/
function funDel(id) {
	$("#id").val(id);
	$("#hdnMode").val(OPERATE_MODE_D);
	$("#myModalLabel")[0].innerHTML = OPERATE_MODENAME_D;
	initDisable();
	$("#myModal").modal("show");
};

/**
 * 保存按钮
 **/
function funSave() {
	var url;
	if (OPERATE_MODE_I == $("#hdnMode").val()) {
		url = "/user/insertUser";
	} else if (OPERATE_MODE_U == $("#hdnMode").val()) {
		url = "/user/updateUser";
	} else if (OPERATE_MODE_D == $("#hdnMode").val()) {
		url = "/user/deleteUser";
	}
	ajaxPost(url, $("#subform").serialize(), function(data,
			status) {
		funCallback(data, status);
	});
};
function funCallback(data, status) {
	if (data.RESULT_CODE == RESULT_CODE_SUCCESS) {
		initModel();
		$("#myModal").modal("hide");
		$("#mytable").dataTable().fnReloadAjax();
	}
};

//删除模式下，控件不可用
function initDisable() {
	if (OPERATE_MODE_D == $("#hdnMode").val()) {
		$("#subform").find(":input").each(function() {
			if ($(this).attr("type") != "hidden" && $(this).attr("type") != "button") {
				$(this).attr("disabled", "disabled");
			}
		});
		$("#btnSave").attr("class", "btn btn-danger pull-right");
	} else {
		$("#subform").find(":input").each(function() {
			$(this).removeAttr("disabled");
		});
		if (OPERATE_MODE_I == $("#hdnMode").val()) {
			$("#btnSave").attr("class", "btn btn-success pull-right");
		} else {
			$("#btnSave").attr("class", "btn btn-primary pull-right");
		}
	}
	$("#btnSave")[0].innerHTML = $("#myModalLabel")[0].innerHTML;
}
// 初始化model
function initModel() {
	$("#subform").find("*").each(function() {
		$(this).val("");
	});
}