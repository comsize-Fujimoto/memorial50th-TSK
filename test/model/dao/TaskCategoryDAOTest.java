package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;

class TaskCategoryDAOTest {

	@Test
	void test() {
		
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

}
