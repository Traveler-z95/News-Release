<%@page import="been.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	ArrayList<Blog> list = (ArrayList<Blog>) request.getAttribute("list");
	String msg = (String) session.getAttribute("msg"); //获取msg的值
	String id = (String) request.getAttribute("id"); //获取id的值
	if (id == null) {
		id = "";
		request.removeAttribute("id");
	}
	String article_id = (String) request.getAttribute("article_id"); //获取id的值
	if (article_id == null) {
		article_id = "";
		request.removeAttribute("article_id");
	}
	String dat1 = (String) request.getAttribute("dat1"); //获取dat1的值
	if (dat1 == null) {
		dat1 = "";
		request.removeAttribute("dat1");
	}
	String dat2 = (String) request.getAttribute("dat2"); //获取dat2的值
	if (dat2 == null) {
		dat2 = "";
		request.removeAttribute("dat2");
	}
%>
<title>评论列表</title>
<%
	if (msg != null && !msg.equals("")) {
		session.removeAttribute("msg");
%>
<script type="text/javascript">
alert("<%=msg%>
	");
</script>
<%
	}
%>
</head>
<body>
	<form action="CommentSearchServlet" Method="get">
		<!-- 向SearchServlet提交查询要求 -->
		<p>按序号查询
		<p>
			输入序号 <input type="text" name="Id" value="<%=id%>">
		<p>按文章序号查询
		<p>
			输入序号 <input type="text" name="Article_id" value="<%=article_id%>">
		<p>按时间查询
		<p>
			输入时间 <input type="text" name="date1" value="<%=dat1%>">和<input
				type="text" name="date2" value="<%=dat2%>">之间
		<p>
			<input type=submit value="提交">
	</form>
	<a href="ArticleListServlet">文章列表</a>
	<a href="ajax/commentjson.jsp">输出json列表</a>
	<table border="1px">
		<tr>
			<td>序号</td>
			<td>文章序号</td>
			<td>内容</td>
			<td>时间</td>
			<td>操作</td>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				Blog blog = list.get(i);
		%>
		<tr>
			<td><%=blog.getId()%></td>
			<td><%=blog.getArticle_id()%></td>
			<td><%=blog.getContent()%></td>
			<td><%=blog.getTime()%></td>


			<td><a
				href="CommentDeleteServlet?action=delete&id=<%=blog.getId()%>">删除</a>
				<a
				href="CommentSearchByIdServlet?action=update&id=<%=blog.getId()%>">更新</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		int pageNow, pageCount;
		String temp;
		temp = (String) request.getAttribute("pageNow");
		if (temp == null || temp.equals("")) {
			temp = "1";
		}
		pageNow = Integer.parseInt(temp);
		temp = (String) request.getAttribute("pageCount");
		if (temp == null || temp.equals("")) {
			temp = "1";
		}
		pageCount = Integer.parseInt(temp);
	%>
	<p>
		共有<%=pageCount%>页 <br>目前显示第<%=pageNow%>页
		<%
		if (pageNow != 1) {
	%>
		<a href="CommentListServlet?pageNow=1">首页</a>&nbsp;
		<!-- 跳转页面超链接 -->
		<%
			}
		%>

		<%
			if (pageNow != 1) {
		%>
		<a href="CommentListServlet?pageNow=<%=pageNow - 1%>">上一页</a>&nbsp;
		<%
			}
		%>
		[
		<%
			for (int i = 1; i <= pageCount; i++) {
		%>
		<a href="CommentListServlet?pageNow=<%=i%>"><%=i%></a>&nbsp;&nbsp;
		<%
			}
		%>
		]
		<%
			if (pageNow != pageCount) {
		%>
		<a href="CommentListServlet?pageNow=<%=pageNow + 1%>">下一页</a>&nbsp;

		<%
			}
		%>

		<%
			if (pageNow != pageCount) {
		%>
		<a href="CommentListServlet?pageNow=<%=pageCount%>">末页</a>&nbsp;
		<%
			}
		%>
	
	<form action="CommentListServlet" Method="post">
		<p>
			跳转到<input type="text" name="pageNow">页 <input type=submit
				value="跳转">
	</form>
</body>
</html>