<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細</title>
</head>
<body>
	<h3>タスク詳細</h3>
	
	<hr>
	
	<% TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
		String userId = (String) session.getAttribute("userId");
	%>
	
	
	<div><b>タスク名</b></div>
	<%=taskBean.getTaskName() %>
	<br>
	
	<div><b>カテゴリ情報</b></div>
	<%=taskBean.getCategoryName() %>
	<br>
	
	<!-- 期限がnullの場合期限なしを表示 -->
	<div><b>期限</b></div>
	<% if(taskBean.getLimitDate() != null){ %>
				
			<%=taskBean.getLimitDate() %>
		<%
		}else{
			%> 期限なし<%
		}
		%>
	<br>
	
	<div><b>担当者情報</b></div>
	<%=taskBean.getUserName() %>
	<br>
	
	<div><b>ステータス情報</b></div>
	<%=taskBean.getStatusName() %>
	<br>
	
	<div><b>メモ</b></div>
	<%=taskBean.getMemo() %>
	<br>
	
	<hr>
	
	<table>
	<!-- ログイン時のuseridと詳細情報のuseridが一致した時編集・削除できる -->
	
	<!-- 編集へ飛ぶ -->
	<tr>
	<td>
	<form action="task-update.jsp" method="POST">
		<input type="submit" value="タスク編集">
	</form>
	</td>
	<!-- 削除確認画面へ飛ぶ -->
	<td>
	<form action="task-delete-confirm.jsp" method="GET">
		<input type="submit" value="タスク削除">
	</form>
	</td>
	<!-- 一覧へ戻る -->
	<td>
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
		
	</form>
	<!-- メニューへ戻る -->
	<td>
	<form action="task-menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
	</td>
	</tr>
	</table>
</body>
</html>