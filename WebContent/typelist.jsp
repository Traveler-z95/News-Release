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
	String name = (String) request.getAttribute("name");
	if (name == null) {
		name = "";
		request.removeAttribute("name");
	}
%>
<title>类型列表</title>
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
	<form action="TypeSearchServlet" Method="get">
		<!-- 向SearchServlet提交查询要求 -->
		<p>按序号查询
		<p>
			输入序号 <input type="text" name="Id" value="<%=id%>">
		<p>按名称查询
		<p>
			输入关键字 <input type="text" name="name" value="<%=name%>"> <input
				type=submit value="提交">
	</form>
	<a href="typeadd.jsp">添加类型</a>&nbsp;&nbsp;
	<a href="ArticleListServlet">文章列表</a>
	<a href="ajax/typejson.jsp">输出json列表</a>
	<!-- 跳转页面超链接 -->
	<table border="1px">
		<tr>
			<td>序号</td>
			<td>名称</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				Blog blog = list.get(i);
		%>
		<tr>
			<td><%=blog.getId()%></td>
			<td><%=blog.getName()%></td>
			<td><%=blog.getDescription()%></td>


			<td><a
				href="TypeDeleteServlet?action=delete&id=<%=blog.getId()%>">删除</a> <a
				href="TypeSearchByIdServlet?action=update&id=<%=blog.getId()%>">更新</a>
				<a href="articleadd.jsp?action=add&id=<%=blog.getId()%>">发布文章</a></td>
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
		<a href="TypeListServlet?pageNow=1">首页</a>&nbsp;
		<!-- 跳转页面超链接 -->
		<%
			}
		%>

		<%
			if (pageNow != 1) {
		%>
		<a href="TypeListServlet?pageNow=<%=pageNow - 1%>">上一页</a>&nbsp;
		<%
			}
		%>
		[
		<%
			for (int i = 1; i <= pageCount; i++) {
		%>
		<a href="TypeListServlet?pageNow=<%=i%>"><%=i%></a>&nbsp;&nbsp;
		<%
			}
		%>
		]
		<%
			if (pageNow != pageCount) {
		%>
		<a href="TypeListServlet?pageNow=<%=pageNow + 1%>">下一页</a>&nbsp;

		<%
			}
		%>

		<%
			if (pageNow != pageCount) {
		%>
		<a href="TypeListServlet?pageNow=<%=pageCount%>">末页</a>&nbsp;
		<%
			}
		%>
	
	<form action="TypeListServlet" Method="post">
		<p>
			跳转到<input type="text" name="pageNow">页 <input type=submit
				value="跳转">
	</form>
</body>
</html>