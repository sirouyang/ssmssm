function ajaxPost(url, dataParam, callback) {
	// 防止多次提交
	if (lock) {
		return;
	}
	lock = true;
	var retData = null;
	$.ajax({
		type : "post",
		url : contextPath + url,
		headers: headers,
		data : dataParam,
		dataType : "json",
		//contentType : "application/json;charset=utf-8",
		success : function(data, status) {
			lock = false;
			retData = data;
			if (callback != null && callback != "" && callback != undefined)
				callback(data, status);
		},
		error : function(err, err1, err2) {
			lock = false;
			alert("调用方法发生异常:" + JSON.stringify(err) + "err1"
					+ JSON.stringify(err1) + "err2:" + JSON.stringify(err2));
		}
	});
	return retData;
}