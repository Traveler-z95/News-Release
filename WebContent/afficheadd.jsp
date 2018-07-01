<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布公告</title>
<script language="javascript" type="text/javascript"
	src="/blog1/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<form action="AfficheAddServlet" method="post">
		<table>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><input type="text" name="content" /></td>
			</tr>
			<tr>
				<td>日期</td>								
				<td><input id="1" type="text" name="time" class="Wdate"
					onfocus="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss',isShowClear:false,readOnly:true})"></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>