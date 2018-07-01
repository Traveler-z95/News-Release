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
	String title = (String) request.getAttribute("title"); //获取title的值
	if (title == null) {
		title = "";
		request.removeAttribute("title");
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
<title>公告列表</title>
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
	<form action="AfficheSearchServlet" Method="get">
		<!-- 向SearchServlet提交查询要求 -->
		<p>按序号查询
		<p>
			输入序号 <input type="text" name="Id" value="<%=id%>">
		<p>按关键字查询
		<p>
			输入关键字 <input type="text" name="title" value="<%=title%>">
		<p>按时间查询
		<p>
			输入时间 <input type="text" name="date1" value="<%=dat1%>">和<input
				type="text" name="date2" value="<%=dat2%>">之间
		<p>
			<input type=submit value="提交">
	</form>

	<a href="afficheadd.jsp">发布公告</a>
	<a href="ajax/affichejson.jsp">输出json列表</a>
	<table border="1px">
		<tr>
			<td>序号</td>
			<td>标题</td>
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
			<td><%=blog.getTitle()%></td>
			<td><%=blog.getContent()%></td>
			<td><%=blog.getTime()%></td>


			<td><a
				href="AfficheDeleteServlet?action=delete&id=<%=blog.getId()%>">删除</a>
				<a
				href="AfficheSearchByIdServlet?action=update&id=<%=blog.getId()%>">更新</a></td>
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
		<a href="AfficheListServlet?pageNow=1">首页</a>&nbsp;
		<!-- 跳转页面超链接 -->
		<%
			}
		%>

		<%
			if (pageNow != 1) {
		%>
		<a href="AfficheListServlet?pageNow=<%=pageNow - 1%>">上一页</a>&nbsp;
		<%
			}
		%>
		[
		<%
			for (int i = 1; i <= pageCount; i++) {
		%>
		<a href="AfficheListServlet?pageNow=<%=i%>"><%=i%></a>&nbsp;&nbsp;
		<%
			}
		%>
		]
		<%
			if (pageNow != pageCount) {
		%>
		<a href="AfficheListServlet?pageNow=<%=pageNow + 1%>">下一页</a>&nbsp;

		<%
			}
		%>

		<%
			if (pageNow != pageCount) {
		%>
		<a href="AfficheListServlet?pageNow=<%=pageCount%>">末页</a>&nbsp;
		<%
			}
		%>
	
	<form action="AfficheListServlet" Method="post">
		<p>
			跳转到<input type="text" name="pageNow">页 <input type=submit
				value="跳转">
	</form>
</body>
</html>