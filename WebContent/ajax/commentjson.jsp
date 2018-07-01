<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xmlhttp;
	function loadXMLDoc(url) {
		xmlhttp = null;
		//检查浏览器是否支持 XMLHttpRequest 对象,如果支持，则创建 XMLHttpRequest 对象。如果不支持，则创建 ActiveXObject
		if (window.XMLHttpRequest) {// code for IE7, Firefox, Mozilla, etc.
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {// code for IE5, IE6
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		//向服务器发送请求
		if (xmlhttp != null) {
			xmlhttp.onreadystatechange = onResponse;
			xmlhttp.open("POST", url, true);//请求的类型、URL 异步处理请求
			xmlhttp.send(null);
		} else {
			alert("Your browser does not support XMLHTTP.");
		}
	}

	function onResponse() {
		//在 onreadystatechange 事件中，我们规定当服务器响应已做好被处理的准备时所执行的任务。当 readyState 等于 4 且状态为 200 时，表示响应已就绪：
		if (xmlhttp.readyState != 4)
			return;
		if (xmlhttp.status != 200) {
			alert("Problem retrieving XML data");
			return;
		}

		//var x;
		//var strText = "";
		var strText = "";
		var jsonResult = eval("(" + xmlhttp.response + ")");//eval() 函数使用的是 JavaScript 编译器，可解析 JSON 文本，然后生成 JavaScript 对象。必须把文本包围在括号中，这样才能避免语法错误：
		jsonResult = jsonResult.news;
		//var jsonResult = eval("(" + xmlhttp.responseText + ")");
		//var jsonResult = JSON.parse(xmlhttp.responseText);
		//创建表格
		strText = "<table border='1'><tr><th>Id</th><th>Article_id</th><th>Content</th><th>Time</th></tr>";
		for (var i = 0; i < jsonResult.length; i++) {//循环显示
			strText = strText + "<tr>";
			strText += '<td>' + jsonResult[i].id + '</td>' + " " + '<td>'
					+ jsonResult[i].article_id + '</td>' + " " + '<td>'
					+ jsonResult[i].content + '</td>' + " " + '<td>'
					+ jsonResult[i].time + '</td>';

			strText += '</tr>'
		}
		strText = strText + "</table>";
		/*for (var i = 0; i < jsonResult.length; i++) {
			if (i == 0)
				strText = jsonResult[i].id + ", " + jsonResult[i].title + ", "
						+ jsonResult[i].content + ", " + jsonResult[i].author
						+ ", " + jsonResult[i].time + ", " + jsonResult[i].area;
			else
				strText = strText + "; " + jsonResult[i].id + ", "
						+ jsonResult[i].title + ", " + jsonResult[i].content
						+ ", " + jsonResult[i].author + ", "
						+ jsonResult[i].time + ", " + jsonResult[i].area;
		}*/
		//alert(strText);
		document.getElementById('copy').innerHTML = strText;//来自服务器的响应
	}
</script>
</head>
<body>
	<div id="copy">
		<button onclick="loadXMLDoc('../CommentJsonServlet')">获取json</button>
	</div>
	<a href="../table/commenttable.jsp">表格</a>
</body>
</html>