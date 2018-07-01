<%@page import="java.util.ArrayList"%>
<%@page import="been.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	ArrayList<Blog> list2 = (ArrayList<Blog>) request.getAttribute("list2");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Blog blog = (Blog) request.getAttribute("blog");
%>
<title>文章详情</title>
<script type="text/javascript" src="/blog1/ckeditor/ckeditor.js"></script>
</head>
<body>
	<p>文章详情：
	<table border="1">
		<tr>
			<td bgcolor="#57C9F4">序号ID：</td>
			<td><input name="id" value="<%=blog.getId()%>" readonly></td>
		</tr>
		<tr>
			<td bgcolor="#57C9F4">类型序号ID：</td>
			<td><input name="type_id" value="<%=blog.getType_id()%>"
				readonly></td>
		</tr>
		<tr>
			<td bgcolor="#57C9F4">标题：</td>
			<td><input type="text" name="title" value="<%=blog.getTitle()%>"readonly></td>
		</tr>
		<tr>
			<td bgcolor="#57C9F4">内容：</td>
			<td><%=blog.getContent()%><img alt="" src="/blog1/ckeditor/uploader/upload/images/file1485154529200.jpg"></td>
		</tr>
		<tr>
			<td bgcolor="#57C9F4">日期</td>
			<td><input type="text" name="time" value="<%=blog.getTime()%>"readonly></td>
		</tr>
	</table>
	<br>
	<a href="commentadd.jsp?action=add&id=<%=blog.getId()%>">发布新评论</a>

	<p>评论内容：
	<table border="1px">
		<tr>
			<td>序号</td>
			<td>文章序号</td>
			<td>内容</td>
			<td>时间</td>
		</tr>
		<%
			for (int i = 0; i < list2.size(); i++) {
				blog = list2.get(i);
		%>
		<tr>
			<td><%=blog.getId()%></td>
			<td><%=blog.getArticle_id()%></td>
			<td><%=blog.getContent()%></td>
			<td><%=blog.getTime()%></td>
		</tr>
		<%
			}
		%>
	</table>


</body>
</html>