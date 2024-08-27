package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class TaskLoginDAO {
	
	public UserBean login(String userId, String password)throws SQLException, ClassNotFoundException  {
//		UserBeanをインスタンス化
		UserBean  user = new UserBean(); 
		
//		分かりやすいようにSQL文を先に書く
//		m_userのテーブルからid,pwが？と一致するものすべてのカラムを抽出する。
		String sql = "SELECT * FROM m_user WHERE user_id = ? and password = ?;";
		
//		データベースへの接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
//			プレースホルダにセットする
//			1番目の？にuserIdをセット
			pstmt.setString(1, userId);
//			2番目の？にpasswordをセット
			pstmt.setString(2, password);
			
//		executeQueryでSQLで実行
			ResultSet res = pstmt.executeQuery();
//			UserBeanのセッター
			if (res.next()) {
				//ユーザーID
				user.setUserId(res.getString("user_id"));
				//パスワード
				user.setPassword(res.getString("password"));
				//ユーザー名
				user.setUserName(res.getString("user_name"));
				return user;
			}
			return null;
		}
	
	
	}

}
