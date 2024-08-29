package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskCategoryDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskAddServler
 */
@WebServlet("/TaskAddServler")
public class TaskAddServler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try{
		request.setCharacterEncoding("UTF-8");
			//TaskCategoryDAO入っているメソッドを使うためにインスタンス化する。
			TaskCategoryDAO dao = new TaskCategoryDAO();
			//DAOからデータを受け取るための箱を用意する。
			List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
			List<StatusBean> statusList = new ArrayList<StatusBean>();
			
			//categoryListにselectCategoryメソッドの結果を代入
			
				categoryList = dao.selectCategory();
				statusList = dao.selectStatus();
			//session開始
			HttpSession session = request.getSession();
			session.setAttribute("categoryList",categoryList);
			session.setAttribute("statusList",statusList);	
				//転送先の指定
			RequestDispatcher rd = request.getRequestDispatcher("Task-register.jsp");
			//
			rd.forward(request,response);
			}catch(SQLException | ClassNotFoundException | ServletException | IOException e) {
				
				e.printStackTrace();
			}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//データの受け取りのためにインスタンス化？
				TaskCategoryDAO dao = new TaskCategoryDAO();

				try {
					List<TaskBean> taskList = dao.insertTask();
					//データ型HttpSession　requestからgetSessionしてるからセッション開始
					HttpSession session = request.getSession();
					
					//属性名taskListとペアで属性値taskListを持つ
					//セッションスコープで同一クライアントからアクセスが続く間データ保持する
					session.setAttribute("taskList", taskList);

					RequestDispatcher rd = request.getRequestDispatcher("Register-success.jsp");

					rd.forward(request, response);
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();

				}
			}

		
	}


