<%@page import="been.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript"
	src="/blog1/My97DatePicker/WdatePicker.js"></script>
<%
	String Article_id = request.getParameter("id");
	int article_id = Integer.parseInt(Article_id);
	System.out.println(article_id);

	Blog blog = (Blog) request.getAttribute("blog");
%>
<title>发布评论</title>
</head>
<body>
	<form action="CommentAddServlet" method="post">
		<table>
			<tr>
				<td bgcolor="#57C9F4">文章ID：</td>
				<td><input name="article_id" type="text" value="<%=article_id%>"
					readonly></td>
			</tr>
			<tr>
				<td bgcolor="#57C9F4">内容</td>
				<td><input type="text" name="content" /></td>
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