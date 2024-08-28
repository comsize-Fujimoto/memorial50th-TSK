package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

/**
 *  @author Fujimoto
 */
public class TaskListDAO {
	
	//タスクを表示するためにDBから取得する
	public List<TaskBean> taskAll() throws SQLException, ClassNotFoundException{
		
		//データベース情報格納リスト
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		
		//データベース情報取得SQL
		//taskIdは詳細表示に必要
		//StringBuilderを使うとメモリの消費を軽減できる
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT t1.task_id,");
		sb.append("t1.task_name,");
		sb.append("t2.category_name,");
		sb.append("t1.limit_date,");
		sb.append("t3.user_name,");
		sb.append("t4.status_name,");
		sb.append("t1.memo ");
		sb.append("FROM t_task t1 JOIN m_category t2 ");
		sb.append("ON t1.category_id = t2.category_id ");
		sb.append("JOIN m_user t3 ");
		sb.append("ON t1.user_id = t3.user_id ");
		sb.append("JOIN m_status t4 ");
		sb.append("ON t1.status_code = t4. status_code");
		
		String sql = sb.toString();
		
		/*このやり方だとメモリを食うのでNG
		String sql = "SELECT t1.task_id,"
				+ "t1.task_name,"
				+ "t2.category_name,"
				+ "t1.limit_date,"
				+ "t3.user_name,"
				+ "t4.status_name,"
				+ "t1.memo "
				+ "FROM t_task t1 JOIN m_category t2 "
				+ "ON t1.category_id = t2.category_id "
				+ "JOIN m_user t3 "
				+ "ON t1.user_id = t3.user_id "
				+ "JOIN m_status t4 "
				+ "ON t1.status_code = t4. status_code";
		*/
		
		//DB接続
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				//タスクID
				int taskId = res.getInt("t1.task_id");
				//タスク名
				String taskName = res.getString("t1.task_name");
				//カテゴリ名
				String categoryName = res.getString("t2.category_name");
				
				//期限
				//LocalDateクラスにしてみたい
				Date limitDate = res.getDate("t1.limit_date");
				
				//担当者名
				String userName = res.getString("t3.user_name");
				//ステータス名
				String statusName = res.getString("t4.status_name");
				//メモ
				String memo = res.getString("t1.memo");
				
				//各値をBeanへセットし、Listへ格納
				TaskBean tb = new TaskBean();
				
				tb.setTaskId(taskId);
				tb.setTaskName(taskName);
				tb.setCategoryName(categoryName);
				tb.setLimitDate(limitDate);
				tb.setUserName(userName);
				tb.setStatusName(statusName);
				tb.setMemo(memo);
				
				taskList.add(tb);
				
			}
		}
		
		//リストをリターン
		return taskList;
	}

}
