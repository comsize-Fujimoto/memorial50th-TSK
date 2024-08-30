package model.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;

public class TaskCategoryDAOTest {

	@Test
	public void test() {
		
		TaskCategoryDAO dao = new TaskCategoryDAO();
		
		try {
			List<CategoryBean> catList = dao.selectCategory();
			
			assertTrue(!catList.isEmpty());
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void ttest() {
		
		TaskCategoryDAO dao = new TaskCategoryDAO();
		
		try {
			List<CategoryBean> catList = dao.selectCategory();
			
			assertEquals("新商品A:開発プロジェクト", catList.get(0).getCategoryName());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
