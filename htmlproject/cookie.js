//正则实现得到cookie
function getCookieReg(cookieName) {
   var re = new RegExp("\\b"+cookieName+"=([^;]*)\\b");
   var arr =  re.exec(document.cookie);
   return arr?arr[1]:"";
}

// 通过; 截取得到cookie
function getCookieSplit(cookieName) {
 	var cookies=document.cookie.split("; ");//一个分号加一个空格
 	if (!cookies.length) {
		return "";
	}
	var pair=["",""];
	for (var i=0;i< cookies.length;i++) {
		pair = cookies[i].split("=");//以赋值号分隔,第一位是Cookie名,第二位是Cookie值
		if (pair[0]==cookieName) {
			break;
		}
	}
	return pair[1];
}

//直接截取字符串
function getCookie(cookieName) {
	var start = document.cookie.indexOf(cookieName+"=");
	if (start ==-1) {
		return "";
	}
	start = start+cookieName.length+1;
	var end = document.cookie.indexOf(";",start);
	if (end == -1) {
		end = document.cookie.length;
	}
	return document.cookie.substring(start,end);
}

//设置cookie数据, name表示cookie名称,expires 表示过期时间, value表示cookie对应的数据, domain表示服务器（域名）, path路径下的文件可以读取
//secure属性设置为true时，cookie只有在https协议下才能上传到服务器，而在http协议下是没法上传的，所以也不会被窃听。
function setCookie(name,value,expires,path,domain,secure) {
	var str = name+"="+encodeURI(value);//不要忘了在对应getCookie函数里面加上decodeURI方法
	if (expires) {
		str += "; expires="+expires.toGMTString();
	}
	if (path) {
		str += "; path="+path;
	}
	if (domain) {
		str += "; domain="+domain;
	}
	if (secure) {
		str += "; secure";
	}
	document.cookie = str;
}

function setCookieDate(name,value,date, path ,domain,secure) {
	var str = name+"="+encodeURI(value);//不要忘了在对应getCookie函数里面加上decodeURI方法
	if (date) {
		var d =new Date();
		d.setTime(d.getTime()+(parseInt(date)*24*60*60*1000));
		str += "; expires="+d.toGMTString();
	}
	if (path) {
		str += "; path="+path;
	}
	if (domain) {
		str += "; domain="+domain;
	}
	if (secure) {
		str += "; secure";
	}
	document.cookie = str;
}

//删除cookie
function delCookie(cookieName) {
	var expires = new Date();
	expires.setTime(expires.getTime()-1);//将expires设为一个过去的日期，浏览器会自动删除它
	document.cookie = cookieName+"=; expires="+expires.toGMTString();
}