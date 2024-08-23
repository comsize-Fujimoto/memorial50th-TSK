<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"import="model.entity.TaskBean,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
	List<TaskBean> taskList = (List<TaskBean>) session.getAttribute("taskList");
	%>
	<h1>タスク登録画面</h1>
	<hr>
	<h5>【登録内容入力】</h5>
	<form action="TaskRegisterServlet" method="POST">
		<table border="1">
		
			<tr>
				<th>①タスク名</th>
				<td><select name="task_name">
						<%
						for (TaskBean category : taskList) {
						%>
						<option value="<%=taskList.taskName()%>"><%=taskList.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>②カテゴリ情報</th>
				<td><select name="category_name">
				<option value=""></option>
				<input type="text" name="category_name" size="100" maxlength="100" required></td>
				</select>
			</tr>
			<tr>
				<th>③期限</th>
				<td><input type="number" name="price" maxlength="8" min="1" max="99999999" required>円</td>
			</tr>
			<tr>
				<th>④担当者情報</th>
				<td><input type="number" name="user_name" maxlength="8" min="1" max="20" required>円</td>
			</tr>
			<tr>
				<th>⑤ステータス情報</th>
				<select name="status_code">
				<td><input type="number" name="status_code" maxlength="8" min="1" max="20" required>円</td>
				</select>
			</tr>
			<tr>
				<th>➅メモ</th>
				<td><input type="number" name="memo" maxlength="200" min="1" max="99999999" required>円</td>
			</tr>
		</table>
		<br>
			<input type="submit" value="登録"> 
			<input type="reset"value="クリア">
	</form>
	<br>
	<br>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
	

</body>
</html>