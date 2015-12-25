function customDropDown(ele) {
	this.dropdown = ele;
	this.placeholder = this.dropdown.find(".placeholder");
	this.options = this.dropdown.find("ul.dropdown-menu > li");
	this.val = '';
	this.index = -1;// 默认为-1;
	this.initEvents();
}
customDropDown.prototype = {
	initEvents : function() {
		var obj = this;
		// 这个方法可以不写，因为点击事件被Bootstrap本身就捕获了，显示下面下拉列表
		obj.dropdown.on("click", function(event) {
			$(this).toggleClass("active");
		});

		// 点击下拉列表的选项
		obj.options.on("click", function() {
			var opt = $(this);
			obj.text = opt.find("a").text();
			obj.val = opt.attr("value");
			obj.index = opt.index();
			obj.placeholder.text(obj.text);
		});
	},
	getText : function() {
		return this.text;
	},
	getValue : function() {
		return this.val;
	},
	getIndex : function() {
		return this.index;
	}
}