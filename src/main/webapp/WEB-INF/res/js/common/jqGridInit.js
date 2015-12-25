// 检索
var paramUrl = "";
var paramPostData = {};
var paramColNames = new Array();
var paramColModel = new Array();
var tableId = "mytable";
var pageSpanName = "page";
var lock = false;
function jqGridInit() {
	// 防止多次提交
	if (lock) {
		return;
	}
	lock = true;
	jQuery("#" + tableId).jqGrid('GridUnload');
	jQuery("#" + tableId).jqGrid({
		sortable : true,
		url : paramUrl,
		postData : paramPostData,
		datatype : "json",
		mtype : 'post',
		async : false,
		colNames : paramColNames,
		colModel : paramColModel,
		prmNames : {
			page : 'P_pageNumber',// 表示请求页码的参数名称
			rows : 'P_pagesize',// 表示请求行数的参数名称
			sort : 'orderBy',// 表示用于排序的列明的参数名称
			order : 'order',// 表示采用的排序方式的参数名称
			search : 'page.search',// 表示是否搜索请求的参数名称
			id : 'id'// 表示当在编辑数据模块中发送数据时，使用的id的名称
		},
		jsonReader : {
			root : "data",// 代表实际模型的数据入口
			page : "pageNumber", // 当前页面
			total : "totalPages", // 总页数
			records : "total", // 总条数
			hasNext : 'hasNext', // 下一页
			hasPre : 'hasPre', // 上一页
			repeatitems : false
		// 根据name来搜索对应的数据元素
		},
		gridComplete : function() {
			paramUrl = "";
			paramPostData = {};
			paramColNames = new Array();
			paramColModel = new Array();
			tableId = "mytable";
			pageSpanName = "page";
		},
		pager : 'pager',
		height : '100%', // 表格高度
		width : $("#" + tableId).width() - 2, // 每列的初始宽度按照shrinkToFit 设置的值
		autowidth : true, // 表格宽度将自动匹配到父元素的宽度
		rowNum : 10, // 表格中可见的记录数
		altRows : true, // 设置为交替行表格
		shrinkToFit : true, // 计算每列相对于表格宽度的初始宽度的类型
		forceFit : true, // 改变列宽，相邻列也将调整以适应整体表格，将不会出现水平滚动条
		rowList : [ 10, 20, 50 ], // 设置表格显示的记录数
		viewrecords : true, // 是否要显示总记录数
		multiselect : false, // 定义是否可多选
		sortorder : "desc", // 排序顺序
		rownumbers : true, // 显示行数
		rownumWidth : 39, // 当rownumbers为true时，定义显示行数的列的宽度
		gridview : true
	// 设置为true将提高5~10倍的显示速度。但不能再使用treeGrid, subGrid, 或afterInsertRow事件
	});

	$(".tables").bind('resize', function() {
		$('#' + tableId).setGridWidth($('.tables').width() - 2);
	}).trigger('resize');
}
