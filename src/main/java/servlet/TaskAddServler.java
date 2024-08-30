package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
			RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
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
		
		//登録内容をServletで受け取り、DAOに送る
		String taskName = request.getParameter("TaskName");
		int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
		String dateStr = request.getParameter("LimitDate");
		LocalDate date = null; 
				
		//日付が入っていたらSQLに送る
		if(dateStr != null) {
			try{
				date = LocalDate.parse(request.getParameter("LimitDate"));
			}catch(DateTimeParseException e){
				date = null;
			}
		}
			
		HttpSession session = request.getSession();
		// セッションスコープへユーザ名を設定
		session.getAttribute("userName");
		session.getAttribute("userId");
		
		String userId = (String)session.getAttribute("userId");
		String statusCode = request.getParameter("StatusCode");
		String memo = request.getParameter("Memo");
		
		//バラバラなので↑を一塊の入れ物TaskBeanに入れる
		//TaskBeanをインスタンス化
		TaskBean task = new TaskBean();
		
		task.setTaskName(taskName);
		task.setCategoryId(categoryId);
		task.setLimitDate(date);
		task.setUserId(userId);
		task.setStatusCode(statusCode);
		task.setMemo(memo);
		
		
		
		//データの受け取りのためにインスタンス化？
				TaskCategoryDAO dao = new TaskCategoryDAO();

				try {
					/*daoクラスのinsertTaskメソッドに引数としてtaskを指定する。
					 * メソッドの戻り値(登録件数)をcountに入れる。*/
					int count = dao.insertTask(task);
					
					/*登録件数が１の場合、登録完了画面
					 * それ以外の場合、登録失敗画面*/
					RequestDispatcher rd;
					if (count ==1) {
						rd = request.getRequestDispatcher("register-success.jsp");
					}else {
						rd = request.getRequestDispatcher("register-failure.jsp");
					}
					
					/*転送する*/
					rd.forward(request, response);
					
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();

				}
			}

		
	}


