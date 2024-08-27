package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  @author Toumi
 */

public class TaskDeleteDAO {
	
	public int deleteTask(int taskId) throws SQLException, ClassNotFoundException {

		String sql = "DELETE FROM t_task WHERE task_id = ?";
		int processingNumber = 0; //処理件数
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
	

}
