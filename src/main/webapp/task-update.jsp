<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean,model.entity.CategoryBean,model.entity.StatusBean,java.util.Map,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集</title>
</head>
<body>
	<h3>タスク編集</h3><hr>
	
	<%
	TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
	Map<Integer,CategoryBean> categoryMap = (Map<Integer,CategoryBean>) session.getAttribute("categoryMap");
	Map<Integer,StatusBean> statusMap = (Map<Integer,StatusBean>) session.getAttribute("statusMap");
	%>
	
	<form action="update-task-servlet" method="post">
	
	<div>タスク名</div><br>
	<input type="text" name="taskName" value="<%=taskBean.getTaskName() %>">
	
	<div>カテゴリ情報</div>
	<select name="categoryId value="<%=taskBean.getCategoryName() %>">
		<% for(CategoryBean catMap : categoryMap.values()){ %>
		<option value="<%=catMap.getCategoryId()%>"><%=catMap.getCategoryName()%></option>
		<% } %>
	</select>
	
	<div>期限</div>
	<% SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-DD"); %>
	<input type="date" name="limitDate" value="<%=taskBean.getLimitDate() %>" min="today">
	
	<div>担当者情報</div>
	<%=taskBean.getUserName() %>
	<br>
	
	<div>ステータス情報</div>
	<select name="statusCode" selected>
		<% for(StatusBean catMap : statusMap.values()){ %>
		<option value="<%=catMap.getStatusCode()%>"><%=catMap.getStatusName()%></option>
		<% } %>
	</select>
	
	<div>メモ</div>
	<textarea name="memo" rows="4" cols="25">
	<%=taskBean.getMemo() %>
	</textarea>
	
	</form>
	
	
</body>
</html>