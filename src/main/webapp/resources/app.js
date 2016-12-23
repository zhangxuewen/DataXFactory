/*!
 * SunHater Circle Loader v0.1 (2016-06-06)
 * jnoos plugin
 * Copyright (c) 2016 jnoos.com
 * http://www.jnoos.com
 */
(function(e, f) {
	var b = null;
	var jn = (function() {
	});
	jn.open = function(o) {
		var path = window.location.host + o.path;
		var _cid = o.cid;
		if (jn.isNull(_cid))
			_cid = jn.getUrlArg("cid");
		if (jn.isNull(_cid))
			_cid = jn.getCookie("CLIENTID");
		if ('WebSocket' in window) {
			b = new WebSocket("ws://" + path + "/ws?cid=" + _cid);
		} else if ('MozWebSocket' in window) {
			b = new MozWebSocket("ws://" + path + "/ws" + _cid);
		} else {
			b = new SockJS("http://" + path + "/ws/sockjs" + _cid);
		}
		b.binaryType = "arraybuffer";
		b.onmessage = o.onmessage;
		b.onopen = o.onopen;
		b.onerror = o.onerror;
		b.onclose = o.onclose;
	};

	jn.close = function() {
		b.close();
	};
	jn.send = function(m) {
		var h = function() {
			b.send(JSON.stringify(m));
		};
		if (b.readyState !== 1) {
			b.close();
			setTimeout(function() {
				h();
			}, 250);
		} else {
			h();
		}
	};

	jn.loaderHide = function() {
		$('.bg').fadeOut(800);
		$('.content').fadeOut(800);

	};
	jn.loaderShow = function(msg) {
		if (!jn.isNull(msg))
			$('.content').text(msg);
		$('.bg').fadeIn(200);
		$('.content').fadeIn(400);
	};

	jn.isNull = function(str) {
		var bol = true;
		if (typeof (str) != "undefined" && str != "" && str != null)
			bol = false;
		return bol;
	}

	// 获取指定名称的cookie的值
	jn.getCookie = function(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';'); // 把cookie分割成组
		for (var i = 0; i < ca.length; i++) {
			var c = unescape(ca[i]); // 取得字符串
			while (c.charAt(0) == ' ') { // 判断一下字符串有没有前导空格
				c = c.substring(1, c.length); // 有的话，从第二位开始取
			}
			if (c.indexOf(nameEQ) == 0) { // 如果含有我们要的name
				return c.substring(nameEQ.length, c.length); // 解码并截取我们要值
			}
		}
		return false;
	}
	// 清除cookie
	jn.clearCookie = function(name) {
		jn.setCookie(name, "", -1);
	}
	// 设置cookie
	jn.setCookie = function(name, value, seconds) {
		seconds = seconds || 0; // seconds有值就直接赋值，没有为0，这个根php不一样。
		var expires = "";
		if (seconds != 0) { // 设置cookie生存时间
			var date = new Date();
			date.setTime(date.getTime() + (seconds * 1000));
			expires = "; expires=" + date.toGMTString();
		}
		document.cookie = name + "=" + escape(value) + expires + "; path=/"; // 转码并赋值
	}

	// 获取url中的参数
	jn.getUrlArg = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); // 匹配目标参数
		if (r != null)
			return unescape(r[2]);
		return ""; // 返回参数值
	}

	e.jn = jn;
})(window);

Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}