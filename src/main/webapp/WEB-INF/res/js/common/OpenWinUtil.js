/**
 * 提供打开弹出窗口的工具类
 */

var OpenWinUtil={
	curIntevalObj:null,
	curChildWin:null,
	refreshCallBack:null,
	tempParam:null,
	
	//打开窗口，当子窗口关闭时，执行刷新操作，操作由_refreshCallBack制定的函数进行，若_refreshCallBack不是一个函数，那么默认刷新当前页面
	//只允许打开一个子窗口
	//第三个参数 isNewWindow表示是否弹出一个新窗口，当且仅当其值为true时才弹出新窗口
	//_tempParam参数可用可不用，用于临时保存一些参数，供回调函数_refreshCallBack使用，在OpenWinUtil。waitRefresh函数调用_refreshCallBack时，会将这个属性作为参数传给_refreshCallBack函数，并且在回调函数执行完毕之后会自动将_tempParam属性的值设置为null
	openWin:function(url,_refreshCallBack,isNewWindow,_tempParam){
		var openWinParams="";
		if(isNewWindow==true){
			var iWidth = window.screen.width;
			var iHeight = window.screen.height-60;
			openWinParams='height='+iHeight+',width='+iWidth+',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no';
		}
		this.tempParam=_tempParam;
		this.refreshCallBack=_refreshCallBack;
		if (isNewWindow==true) {
			this.curChildWin = window.open(url,("mywindow"+(Math.floor(Math.random() * 1000 + 1))),openWinParams);
		} else {
			window.location.href = url;
		}
		this.curIntevalObj=setInterval("OpenWinUtil.waitRefresh();",1000);
	},
	//打开窗口，但不刷新父页面，此时允许打开多个子窗口
	openWinWithoutRefresh:function(url,isNewWindow,iWidth,iHeight){
		var openWinParams="";
		if(isNewWindow==true){
			if (iWidth == undefined) {
				var iWidth = window.screen.width;
			}
			if (iHeight == undefined) {
				var iHeight = window.screen.height-60;
			}
			var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
			openWinParams='height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no';
		}
		window.open(url,"",openWinParams);//'height='+iHeight+',width='+iWidth+',top=0,left=0,toolbar=no,menubar=no, resizable=no,location=no, status=no');
	},
	waitRefresh:function(){
		if(this.curChildWin&&this.curChildWin.closed){
			clearInterval(this.curIntevalObj);
			if(this.refreshCallBack!=null&&typeof this.refreshCallBack=='function'){
				this.refreshCallBack(this.tempParam);
			}else window.location.reload();
			
			this.curIntevalObj=null;
			this.curChildWin=null;
			this.refreshCallBack=null;
			this.tempParam=null;
			
		}
	}
}