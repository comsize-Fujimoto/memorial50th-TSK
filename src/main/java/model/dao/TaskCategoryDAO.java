package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;

public class TaskCategoryDAO {

	/* 全商品情報の一覧リストを返す
		 * @return 商品情報リスト
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
	//servletでデータ取得のためselectCategoryを定義
	/*public(異なるパッケージのクラスからでもアクセス可能)
	 * 戻り値<CategoryBean>を扱うList型(オブジェクトクラス)のselectCategoryメソッド。
	 * 引数なし
	 * SQL、クラスが見つからない時の例外処理をselectCategoryメソッドの呼び出し元に投げる*/
	public List<CategoryBean> selectCategory()
			throws SQLException, ClassNotFoundException {

		//戻り値を設定するためにCategoryBeanを扱うList型の変数名categoryListをインスタンス化
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		String sql = "SELECT * FROM m_category";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		//中身を詰めていく
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);

		) {
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			//カーソルが動いたらtrue。
			while (res.next()) {
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				CategoryBean category = new CategoryBean();
				category.setCategoryId(categoryId);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
		}
		return categoryList;
	}

	public List<StatusBean> selectStatus()
			throws SQLException, ClassNotFoundException {

		//戻り値を設定するためにCategoryBeanを扱うList型の変数名statusListをインスタンス化
		//statusListにStatusBeanを格納
		List<StatusBean> statusList = new ArrayList<StatusBean>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_status")) {

			// 結果の操作
			//一行ずつデータを取り出して、すべての行を処理するためのループ
			//行が存在する限り true を返す
			while (res.next()) {
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				StatusBean status = new StatusBean();
				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				statusList.add(status);
			}
		}
		return statusList;
	}

	//タスク登録メソッド
	public int insertTask(TaskBean newTask)
			throws SQLException, ClassNotFoundException {

		//sql文の作成
		String sql = "INSERT INTO t_task (task_name,category_id,limit_date,user_id,status_code,memo) VALUES (?,?,?,?,?,?);";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();

				/*SQL文を準備、実行*/
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//値の設定
			pstmt.setString(1, newTask.getTaskName());
			pstmt.setInt(2, newTask.getCategoryId());
			
			Date limitDate = null;
			if (newTask.getLimitDate() != null) {
				try {
					// データ型を文字列に変換
					limitDate = Date.valueOf(newTask.getLimitDate());
				} catch (DateTimeParseException e) {
					limitDate = null;
				}
			}
			pstmt.setDate(3, limitDate);
			pstmt.setString(4, newTask.getUserId());
			pstmt.setString(5, newTask.getStatusCode());
			pstmt.setString(6, newTask.getMemo());

			//countに登録件数が代入される
			int count = pstmt.executeUpdate();

			// 結果の操作
			return count;
		}
	}

}
