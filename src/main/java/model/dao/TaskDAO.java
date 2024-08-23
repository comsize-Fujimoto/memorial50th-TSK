package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * @author dadadaaaaaaike
 */

public class TaskDAO {
	
	public TaskBean selectTask(int taskId) {
		
		//読み出したタスク保持するBean
		TaskBean task = new TaskBean();
		//SQL文
		//String sql = "SELECT task_id,task_name,category_id,limit_date,user_id,status_code,memo FROM t_task WHERE task_id = ?";
		
		String sql = "SELECT t1.task_id,t1.task_name,t2.category_name,"
				+ "t1.limit_date,"
				+ "t1.user_id,"
				+ "t3.user_name,"
				+ "t4.status_name,"
				+ "t1.memo "
				+ "FROM t_task t1 JOIN m_category t2 "
				+ "ON t1.category_id = t2.category_id "
				+ "JOIN m_user t3 "
				+ "ON t1.user_id = t3.user_id "
				+ "JOIN m_status t4 "
				+ "ON t1.status_code = t4.status_code "
				+ "WHERE task_id = ?";
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			//プレースホルダの指定
			pstmt.setInt(1, taskId);
			
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをUserBeanに格納する
			while(res.next()) {
				task.setTaskId(res.getInt("t1.task_id"));
				task.setTaskName(res.getString("t1.task_name"));
				task.setCategoryName(res.getString("t2.category_name"));
				task.setLimitDate(res.getDate("t1.limit_date"));
				task.setUserId(res.getString("t1.user_Id"));
				task.setUserName(res.getString("t3.user_name"));
				task.setStatusName(res.getString("t4.status_name"));
				task.setMemo(res.getString("t1.memo"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return task;
	}
	
	public Map<Integer,CategoryBean> allCategory(){ //マップ使ってみた
		
		//sql文の作成をする
		String sql = "SELECT category_id,category_name FROM m_category";
		
		//抽出したテーブルを格納するリストの作成をする
		Map<Integer,CategoryBean> categoryMap = new HashMap<Integer,CategoryBean>();
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
				){
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをリストに格納する
			int categoryId;
			String categoryName;
			while(res.next()) {
				
				categoryId = res.getInt("category_id");
				categoryName = res.getString("category_name");
				
				categoryMap.put(categoryId,new CategoryBean());
				categoryMap.get(categoryId).setCategoryId(categoryId);
				categoryMap.get(categoryId).setCategoryName(categoryName);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return categoryMap;
		
	}
	
	public Map<String,UserBean> allUser(){ //マップ使ってみた
		
		//sql文の作成をする
		String sql = "SELECT user_id,user_name FROM m_user";
		
		//抽出したテーブルを格納するリストの作成をする
		Map<String,UserBean> userMap = new HashMap<String,UserBean>();
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
				){
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをリストに格納する
			String userId;
			String userName;
			while(res.next()) {
				
				userId = res.getString("user_id");
				userName = res.getString("user_name");
				
				userMap.put(userId,new UserBean());
				userMap.get(userId).setUserId(userId);
				userMap.get(userId).setUserName(userName);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return userMap;
		
	}
	
	public Map<Integer,StatusBean> allStatus(){ //マップ使ってみた
		
		//sql文の作成をする
		String sql = "SELECT status_code,status_name FROM m_status";
		
		//抽出したテーブルを格納するリストの作成をする
		Map<Integer,StatusBean> statusMap = new HashMap<Integer,StatusBean>();
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
				){
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをリストに格納する
			int statusCode;
			String statusName;
			while(res.next()) {
				
				statusCode = res.getInt("status_code");
				statusName = res.getString("status_name");
				
				statusMap.put(statusCode,new StatusBean());
				statusMap.get(statusCode).setStatusCode(statusCode);
				statusMap.get(statusCode).setStatusName(statusName);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return statusMap;
		
	}
	
}
