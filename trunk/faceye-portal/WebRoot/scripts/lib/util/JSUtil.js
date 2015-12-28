/**
 * www.faceye.com 网络支持系统 作者:宋海鹏 ecsun@sohu.com/myecsun@hotmail.com/QQ:82676683
 * 说明:javascripts 工具类
 * 
 */

/**
 * 动态加载JS文件
 */
var Faceye = {
	version : 1.0,
	allHttpMethld : {
		GET : 'GET',
		POST : 'POST',
		HEAD : 'HEAD',
		PUT : 'PUT',
		DELETE : 'DELETE'
	},
	/**
	 * 简单取得httpRequest
	 */
	httpRequest : function() {
		var xRequest = null;
		if (window.XMLHttpRequest) {
			xRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xRequest = new ActiveXObject("MsXml2.XmlHttp");
		}
		return xRequest;
	},
	/**
	 * 发送http请求
	 */
	sendRequest : function(url, params, httpMethod) {
		var xRequest = this.httpRequest();
		if (xRequest) {
			xRequest.open(httpMethod, url, true);
			xRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xRequest.send(params);
		}
	},
	/**
	 * 异步加载js文件
	 */
	load : function(src) {
		var headerDom = document.getElementsByTagName('head').item(0);
		var jsDom = document.createElement('script');
		jsDom.type = 'text/javascript';
		jsDom.scr = src;
		var scripts = document.getElementsByTagName('script');
		var runScript = scripts[scripts.length - 3];
		headerDom.insertBefore(jsDom, runScript);
//		headerDom.appendChild(jsDom);
	},
	/**
	 * 同步加载
	 */
	ajaxLoad : function(source) {
		var headerDom = document.getElementsByTagName('head').item(0);
		var jsDom = document.createElement('script');
		jsDom.type = 'text/javascript';
		jsDom.language = 'javascript';
		jsDom.defer = true;
		jsDom.text = source;
		var scripts = document.getElementsByTagName('script');
		var runScript = scripts[scripts.length - 3];
		headerDom.insertBefore(jsDom, runScript);
		// headerDom.appendChild(jsDom);

		// for(var i=0;i<scripts.length;i++){
		// if(scripts[i].text){
		// alert(scripts[i].text);
		// }else{
		// alert(scripts[i].src);
		// }
		// }
	},
	/**
	 * 取得dom arg可以是id,标签名字. 如果是标签名字,如(div),返回的是一个dom数组.反之,返回的是一个单独的dom
	 */
	get : function(arg0) {
		var dom = null;
		dom = document.getElementById(arg0);
		if (!dom) {
			dom = document.getElementsByTagName(arg0);
		}
		return dom;
	}
};