<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Table</title>
<link rel="stylesheet"
	href="https://www.jq22.com/demo/bootstrap-table-master20161026/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://www.jq22.com/demo/bootstrap-table-master20161026/src/bootstrap-table.css">
<link rel="stylesheet" href="docs.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/assets/bootstrap/js/bootstrap.min.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/docs/examples.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/src/bootstrap-table.js"></script>
<script src="docs.js"></script>
</head>
<body>

	<div>
		<div class="page-header">
			<h1 id="pagination-table">Pagination Table</h1>
		</div>
		<div class="bs-example">
			<table data-toggle="table" data-url="data2.json" data-height="246"
				data-pagination="true">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="id" data-align="right">Item ID</th>
						<th data-field="name" data-align="center">Item Name</th>
						<th data-field="price" data-align="">Item Price</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="highlight">
			<pre>
				<code class="language-html"></code>
			</pre>
		</div>
	</div>

</body>
</html>