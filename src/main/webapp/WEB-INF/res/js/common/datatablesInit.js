// 检索
var paramUrl = "";
var paramPostData = {};
var paramColumns = new Array();
var paramColumnDefs = new Array();
var tableId = "mytable";
var lock = false;
function datatablesInit() {
	// 防止多次提交
	if (lock) {
		return;
	}
	lock = true;
	var headers = {};
	headers['X-CSRF-TOKEN'] = ($("meta[name=_csrf]")).attr("content");
	var t = $('#' + tableId).DataTable({
		"ajax" : $.fn.dataTable.pipeline({
			"url" : contextPath + paramUrl,
			"type" : "POST",
			"headers" : headers,
			"data" : paramPostData
		}),
		"processing": true,
		"serverSide": true,
		"columns" : paramColumns,
		"columnDefs" : paramColumnDefs,
		"paging" : true,
		"lengthChange" : true,
		"searching" : false,
		"ordering" : true,
		"info" : true,
		"autoWidth" : true,
		"deferRender" : true,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			"infoEmpty" : "无记录",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"paginate": {
                "previous": "上一页",
                "next": "下一页"
            },
			"decimal" : ",",
			"thousands" : "."
		},
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" + "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
		initComplete: function () {
            $("#mytool").append('<button type="button" class="btn btn-default btn-sm" id="btnAdd">添加</button>');
            $("#btnAdd").on("click", funAdd);
            lock = false;
        }
	});
//	// 前台添加序号
//	t.on('order.dt search.dt', function() {
//		t.column(0, {
//			"search" : 'applied',
//			"order" : 'applied'
//		}).nodes().each(function(cell, i) {
//			cell.innerHTML = i + 1;
//		});
//	}).draw();
}
