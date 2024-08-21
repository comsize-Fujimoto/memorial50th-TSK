package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

public class TaskCategoryDAO {
	public class TaskDAO {
		/* 全商品情報の一覧リストを返します。
		 * @return 商品情報リスト
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		//servletで使うための定義

		public List<TaskBean> selectCatergory()
				throws SQLException, ClassNotFoundException {
			
			//TaskBean型のデータが入っているListを作成。インスタンス化。
			List<TaskBean> taskList = new ArraytaskList<TaskBean>();

			// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
			try (Connection con = ConnectionManager.getConnection();
					
					//29行目抽出するために↓が必要
					Statement stmt = con.createStatement();
					ResultSet res = stmt.executeQuery("SELECT category_name,item_name,price FROM m_item"
							)) {

			// 結果の操作
			//resの間繰り返す
				
			while (res.next()) {
					String categoryName = res.getString("categoryName");
					int categoryCode = res.getInt("categoryCode");
					
					TaskBean categoryBean = new TaskBean();
					categoryBean.setCategoryCode(categoryCode);
					categoryBean.setCategoryName(categoryName);


					categoryList.add(categoryBean);
				}
			}
			return categoryList;
	}
		
		public List<ItemBean> selectAll()
				throws SQLException, ClassNotFoundException {
			
			//ItemCategoryBean型のデータが入っているListを作成。インスタンス化。
			List<ItemBean> itemList = new ArrayList<ItemBean>();

			// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
			try (Connection con = ConnectionManager.getConnection();
					
					//29行目抽出するために↓が必要
					Statement stmt = con.createStatement();
					ResultSet res = stmt.executeQuery("SELECT t1.item_code,t2.category_name,t1.item_name,t1.price FROM m_item t1 LEFT OUTER JOIN m_category t2 ON t1.category_code = t2.category_code"
							)) {

			// 結果の操作
			//resの間繰り返す
				
			while (res.next()) {
					int code = res.getInt("t1.item_code");
					String itemName = res.getString("t1.item_name");
					int categoryCode = res.getInt("t1.category_code");		
					int price = res.getInt("t1.price");
					

					ItemBean itemBean = new ItemBean();
					itemBean.setItemCode(code);
					itemBean.setItemName(itemName);
					itemBean.setCategoryCode(categoryCode);
					itemBean.setPrice(price);

					itemList.add(itemBean);
				}

}
