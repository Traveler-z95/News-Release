<%@page import="been.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	/*ArrayList<Blog> list = (ArrayList<Blog>) request.getAttribute("list");*/
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Blog blog = (Blog) request.getAttribute("blog");
%>
<title>公告修改</title>
</head>
<script language="javascript" type="text/javascript"
	src="/blog1/My97DatePicker/WdatePicker.js"></script>
<body>
	<center>
		<font color="#57C9F4" size=11>修改用户页面</font>
		<hr>
		<form action="AfficheUpdateServlet" method="post">
			<table border="1">
				<tr>
					<td bgcolor="#57C9F4">序号ID：</td>
					<td><input name="id" value="<%=blog.getId()%>" readonly></td>
				</tr>
				<tr>
					<td bgcolor="#57C9F4">标题：</td>
					<td><input type="text" name="title"
						value="<%=blog.getTitle()%>"></td>
				</tr>
				<tr>
					<td bgcolor="#57C9F4">内容：</td>
					<td><input type="text" name="content"
						value="<%=blog.getContent()%>"></td>
				</tr>
				<tr>
					<td bgcolor="#57C9F4">日期</td>
					<td><input id="1" type="text" name="time" class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss',isShowClear:false,readOnly:true})"></td>
				</tr>
				<tr>
					<td colspan="2">
						<center>
							<input type="submit" value="提交"> <input type="reset"
								value="重置">
						</center>
					</td>
				</tr>
			</table>
		</form>
	</center>
	<center>
		<a href="AfficheListServlet">公告列表</a>
	</center>
</body>
</html>