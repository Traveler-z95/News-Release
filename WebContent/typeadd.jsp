<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>类型发布</title>
</head>
<body>
	<form action="TypeAddServlet" method="post">
		<table>
			<tr>
				<td bgcolor="#57C9F4">名称</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td bgcolor="#57C9F4">描述</td>
				<td><input type="text" name="description" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>