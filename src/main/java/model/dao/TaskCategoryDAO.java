package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.TaskBean;

public class TaskCategoryDAO {

	/* 全商品情報の一覧リストを返します。
		 * @return 商品情報リスト
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
	//servletで使うための定義
	//selectCatergory引数なしのメソッドを作るTaskBean型が入るList（オブジェクトクラス)
	public List<CategoryBean> selectCategory() throws SQLException, ClassNotFoundException {

		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_category")) {
			// 結果の操作
			while (res.next()) {
				int categoryCode = res.getInt("category_code");
				String categoryName = res.getString("category_name");
				CategoryBean category = new CategoryBean();
				category.setCategoryCode(categoryCode);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
	
	
	public List<TaskBean> insertTask()
			throws SQLException, ClassNotFoundException {

		//TaskBean型のデータが入っているListを作成。インスタンス化。
		/*selectCtegoryメソッドでList<TaskBean>を戻り値で返したいので
		 * そのためにTaskBean型のデータが入っているListを作成*/
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		
		//sql文の作成
		String sql = "INSERT INTO t_task (task_name,category_name,limit_data,user_name,status_name,memo) VALUES (?,?,?,?,?,?)";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();

				//34行目抽出するために↓が必要
				/*ConnectionオブジェクトのcreateStatementメソッドを使用する*/
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//参照型StatementオブジェクトのexecuteQueryメソッドを使用
			ResultSet res = pstmt.executeQuery();
		}

				// 結果の操作
				//resの間繰り返す

			}
			while (res.next()) {
				String taskName = res.getString("taskName");
				String categoryName = res.getString("CategoryName");
				//sqlデータ型のDATEをJavaデータ型に変換↓表記合ってる？
				Date limitData = res.getDate("LimitData");
				String userName = res.getString("UserName");
				String statusName = res.getString("statusName");
				String memo = res.getString("memo");

				//TaskBean型に入れる属性値を記入している？
				TaskBean insertBean = new TaskBean();
				insertBean.setTaskName(taskName);
				insertBean.setCategoryName(categoryName);
				insertBean.setLimitDate(limitData);
				insertBean.setUserName(userName);
				insertBean.setStatusName(statusName);
				insertBean.setMemo(memo);

				taskList.add(insertBean);
			}
		}
		return taskList;
	}

}
