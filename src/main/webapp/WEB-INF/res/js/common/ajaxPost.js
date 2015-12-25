function ajaxPost(url, dataParam, callback) {
	var retData = null;
//	var headers = {};
//    headers['X-CSRF-TOKEN'] = ($("meta[name=_csrf]")).attr("content");
	$.ajax({
		type : "post",
		url : url,
		headers: headers,
		data : dataParam,
		dataType : "json",
		success : function(data, status) {
			retData = data;
			if (callback != null && callback != "" && callback != undefined)
				callback(data, status);
		},
		error : function(err, err1, err2) {
			alert("调用方法发生异常:" + JSON.stringify(err) + "err1"
					+ JSON.stringify(err1) + "err2:" + JSON.stringify(err2));
		}
	});
	return retData;
}