<%@page import="been.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript"
	src="/blog1/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/blog1/ckeditor/ckeditor.js"></script>
<!-- 插入ckeditor插件 -->
<%
	String Type_id = request.getParameter("id");
	int type_id = Integer.parseInt(Type_id);
	System.out.println(type_id);

	Blog blog = (Blog) request.getAttribute("blog");
%>
<title>发布文章</title>
</head>
<body>
	<form action="ArticleAddServlet" method="post">
		<table>
			<tr>
				<td bgcolor="#57C9F4">类型ID：</td>
				<td><input name="type_id" type="text" value="<%=type_id%>"
					readonly></td>
			</tr>
			<tr>
				<td bgcolor="#57C9F4">标题</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td bgcolor="#57C9F4">内容</td>
				<td><textarea rows="10" cols="300" name="content"
						class="ckeditor"></textarea></td>

			</tr>
			<tr>
				<td bgcolor="#57C9F4">日期</td>
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