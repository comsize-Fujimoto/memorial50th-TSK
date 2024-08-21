<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.TaskBean"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧</title>
</head>
<body>
	<% List<TaskBean> taskList = (List<TaskBean>)request.getAttribute("taskList");%>
	
	<hr>
	
		<table border="1">
		<tr>
			<th>商品コード</th>
			<th>商品分類</th>
			<th>商品名</th>
			<th>価格</th>
		</tr>
		<%
		for (TaskBean task : taskList) {
		%>
		<tr>
			<td><a
				href="ItemDetailServlet?item_code=<%=item.getItemCode()%>"><%=item.getItemCode()%></a>
			</td>
			<td><%=item.getCategoryName()%></td>
			<td><%=item.getItemName()%></td>
			<td><%=item.getPrice()%>円</td>
		</tr>
		<%
		}
		%>
	</table>
	
	
	
	
</body>
</html>