/**
 * 动态加载类
 */

var Faceye = {
	version : '1.0',
	/**
	 * 取得xmlHttpRequest
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
	open : function(url, params, httpMethod) {
		var xRequest = this.httpRequest();
		xRequest.open(httpMethod, url, false);
		xRequest.send();
		return xRequest;
	}
};
var ClassLoader = function() {
};

/**
 * 根据文件路径进行加载
 */
ClassLoader.prototype.loadFormFileSrc = function(url) {
	var xRequest = Faceye.httpRequest();

	xRequest.onreadystatechange = function() {
		var state = xRequest.readyState;
		if (state == 4) {
			if (xRequest.status == 200 || xRequest.status == 304) {
				var source = xRequest.responseText;
				var headerDom = document.getElementsByTagName('head').item(0);
				var jsDom = document.createElement('script');
				jsDom.type = 'text/javascript';
				jsDom.language = 'javascript';
				jsDom.defer = true;
				jsDom.text = source;
				headerDom.appendChild(jsDom);
			}
		}
	};
	xRequest.open('GET', url, false);
	xRequest.send(null);
};
/**
 * 加载javascript源文件代码
 */
ClassLoader.prototype.loadFromSource = function(source) {
	var xRequest = Faceye.httpRequest();
	xRequest.onreadystatechange = function() {
		var state = xRequest.readyState;
		if (state == 4) {
			if (xRequest.status == 200 || xRequest.status == 304) {
				var headerDom = document.getElementsByTagName('head').item(0);
				var jsDom = document.createElement('script');
				jsDom.type = 'text/javascript';
				jsDom.language = 'javascript';
				jsDom.defer = true;
				jsDom.text = source;
				headerDom.appendChild(jsDom);
			}
		}
	};
};
