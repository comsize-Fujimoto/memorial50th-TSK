package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

public class TaskListDAOTest {

	@Test
	public void test_getTaskList() {
		
		//リストの中身がある場合true
		TaskListDAO dao = new TaskListDAO();
		List<TaskBean> bean = null;
		
		try {
			bean = dao.taskAll();
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		assertTrue(!bean.isEmpty());
	}
	
	@Test
	public void test_getTaskListEmpty() {
		
		TaskListDAO dao = new TaskListDAO();
		List<TaskBean> bean = null;
		
		try {
			bean = dao.taskAll();
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		assertFalse(bean.isEmpty());
	}
	
}
