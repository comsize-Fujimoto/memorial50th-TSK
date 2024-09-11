<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.entity.TaskBean,java.util.List,model.entity.CategoryBean,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
	List<TaskBean> taskList = (List<TaskBean>) session.getAttribute("taskList");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	String userName = (String) session.getAttribute("userName");
	%>
	<h1>タスク登録画面</h1>
	<hr>
	<h3>【登録内容入力】</h3>
	<form action="TaskAddServler" method="POST">
		<table border="1">

			<tr>
				<th>①タスク名</th>
				<td>
					<!-- textに入力した値に名前をつける。パラメーター名がTaskName --> 
					<input type="text"name="TaskName" required>
				</td>
			</tr>
			<tr>
				<th>②カテゴリ情報</th>
				<td><select name="CategoryId">
						<%
						for (CategoryBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>③期限</th>
				<td><input type="date" name="LimitDate" value = LimitData></td>
			</tr>
			<tr>
				<th>④担当者情報</th>
				<td><%= userName %></td>
			</tr>
			<tr>
				<th>⑤ステータス情報</th>
				<td><select name="StatusCode"required>
						<%
						for (StatusBean status : statusList) {
						%>
						<option value="<%=status.getStatusCode()%>"><%=status.getStatusName()%></option>
						<%
						}
						%>
				</select></td>
				
			</tr>
			<tr>
				<th>➅メモ</th>
				<td><input type="text" name="memo" size="30" maxlength="100"></td>
			</tr>
		</table>
		<br> <input type="submit" value="登録"> <input type="reset"
			value="クリア">
	</form>
	<br>
	<br>
	<form action="task-menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>