<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
	//List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	%>
	<h1>タスク登録画面</h1>
	<hr>
	 <h5>【登録内容入力】</h5>
	//<form action="TaskRegisterServlet" method="POST">
		<table border="1">
		
			<tr>
				<th>①タスク名</th>
				<td><select name="task_name">
						<%
						for (TaskBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryCode()%>"><%=category.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>②カテゴリ情報</th>
				<td><input type="text" name="item_name" size="100" maxlength="100" required></td>
			</tr>
			<tr>
				<th>③期限</th>
				<td><input type="number" name="price" maxlength="8" min="1" max="99999999" required>円</td>
			</tr>
			<tr>
				<th>④担当者情報</th>
				<td><input type="number" name="user_name" maxlength="8" min="1" max="99999999" required>円</td>
			</tr>
			<tr>
				<th>⑤ステータス情報</th>
				<td><input type="number" name="status_code" maxlength="8" min="1" max="99999999" required>円</td>
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