package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.TaskBean;

public class TaskDAO {
	
	public TaskBean selectTask(int taskId) {
		
		//読み出したタスク保持するBean
		TaskBean task = new TaskBean();
		//SQL文
		String sql = "SELECT task_id,task_name,category_id,limit_date,user_id,status_code,memo FROM t_task WHERE = ?";
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			//プレースホルダの指定
			pstmt.setInt(1, taskId);
			
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをUserBeanに格納する
			while(res.next()) {
				task.setTaskId(res.getInt("task_id"));
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				task.setLimitDate(res.getDate("limit_date"));
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				task.setMemo(res.getString("memo"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return task;
	}
	
	public List<CategoryBean> allCategory(){ //制作中
		
		//sql文の作成をする
		String sql = "SELECT category_code,category_name FROM m_category";
		
		//抽出したテーブルを格納するリストの作成をする
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		
		//データベースへの接続とSQL文の準備をする
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
				){
			//SQLの実行をする
			ResultSet res = pstmt.executeQuery();
			
			//抽出したテーブルをリストに格納する
			int i = 0;
			while(res.next()) {
				
				categoryList.add(new CategoryBean());
				categoryList.get(i).setCategoryCode(res.getInt("category_code"));
				categoryList.get(i).setCategoryName(res.getString("category_name"));
				
				i++;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return categoryList;
		
	}
	
	
}
