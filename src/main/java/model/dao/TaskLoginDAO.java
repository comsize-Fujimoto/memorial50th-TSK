package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class TaskLoginDAO {
	
	public UserBean login(String userId, String password)throws SQLException, ClassNotFoundException  {
		UserBean  user = new UserBean(); 
//		分かりやすいようにSQL文を先に書く
//		m_userのテーブルの条件がid,pwが？と一致するものすべてのカラムを抽出する。
		String sql = "SELECT * FROM m_user WHERE user_id = ? and password = ?;";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
//		プレースホルダにセットする
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
//		executeQueryでSQLで実行
			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				
				user.setUserId(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setUserName(res.getString("user_name"));
				return user;
			}
			return null;
		}
	
	
	}

}
