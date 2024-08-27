<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
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
	TaskBean updateTask = (TaskBean) session.getAttribute("updateTask");
	Map<Integer,CategoryBean> categoryMap = (Map<Integer,CategoryBean>) session.getAttribute("categoryMap");
	Map<Integer,StatusBean> statusMap = (Map<Integer,StatusBean>) session.getAttribute("statusMap");
	%>
	
	<form action="update-task-servlet" method="post">
	
	<div>タスク名</div>
	<input type="text" name="taskName" value="<%=updateTask.getTaskName() %>">
	
	<div>カテゴリ情報</div>
	<select name="categoryId">
		<%
		for(CategoryBean catMap : categoryMap.values()){
			if(catMap.getCategoryId() == updateTask.getCategoryId()){
			%>
			
			<option value="<%=catMap.getCategoryId()%>" selected><%=catMap.getCategoryName()%></option>
			
			<%
			} else{
			%>
			<option value="<%=catMap.getCategoryId()%>"><%=catMap.getCategoryName()%></option>
			<%
			}
		}
		%>
	</select>
	
	<div>期限</div>
	<input type="date" name="limitDate" value="<%=updateTask.getLimitDate() %>">
	
	<div>担当者情報</div>
	<%=updateTask.getUserName() %>
	<br>
	
	<div>ステータス情報</div>
	<select name="statusCode">
		<%
		for(StatusBean staMap : statusMap.values()){
			if(staMap.getStatusCode() == updateTask.getStatusCode()){
			%>
			<option value="<%=staMap.getStatusCode()%>" selected><%=staMap.getStatusName()%></option>
			<%
			}else{
			%>
			<option value="<%=staMap.getStatusCode()%>"><%=staMap.getStatusName()%></option>
			<%
			}
		}
		%>
	</select>
	
	<div>メモ</div>
	<textarea name="memo" rows="4" cols="25"><%
	if(updateTask.getMemo() != null){
		%><%=updateTask.getMemo()%><%
	}%></textarea>
	<br>
	<input type="submit" value="完了">
	<input type="reset" value="元に戻す">
	</form>
	
	
</body>
</html>