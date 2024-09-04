package servlet;

import java.io.IOException;
import java.sql.Date;
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
 @author saito
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

		try {
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
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("statusList", statusList);
			//転送先の指定
			RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
			//
			rd.forward(request, response);
		} catch (SQLException | ClassNotFoundException | ServletException | IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//
		//登録が完了した場合と失敗した場合で、遷移先を変更する。
		//まずurlの初期値を空文字に設定する
		String url = "";
		
		//DBで使うためのDate型のlimitDate変数を宣言（入れ物作り）
		Date limitDate;
		//limit_dateのデータが空でないかどうかを確認
		/*limit_dateのデータが空でないときのif文
		 * 期日(LimitDate)をint型に変換
		 * それ以外、期日(LimitDate)にnullが入る*/
		if (!request.getParameter("LimitDate").isEmpty()) {
			limitDate = Date.valueOf(request.getParameter("LimitDate"));
		} else {
			limitDate = null;
		}
		/*最大文字数制限を設定して条件分岐する*/
		int maxlength = 100;

		/*セッション呼び出し*/
		HttpSession session = request.getSession();
		// セッションスコープへユーザ名を設定
		session.getAttribute("userName");
		session.getAttribute("userId");
		
		
		//？？？？
		String userId = (String) session.getAttribute("userId");
		String statusCode = request.getParameter("StatusCode");
		String memo = request.getParameter("Memo");

		//	
		//

		//登録内容をServletで受け取り、DAOに送る
		//(タスク名、カテゴリ情報、期限)
		String taskName = request.getParameter("TaskName");
		int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
		String dateStr = request.getParameter("LimitDate");

		/*初期値がnull(未設定)*/
		LocalDate date = null;

		/*Date型のtoday変数＝DateクラスのvalueOf今の日付int型で取得	*/
		Date today = Date.valueOf(LocalDate.now());

		/*条件分岐　memoの文字数制限を超えている場合または、期日が過去の日付の場合、
		 * 登録失敗画面へ遷移(エラーチェック)*/
		/*trueの場合、urlを登録失敗画面へ遷移する*/
		if (memo.length() > maxlength || (limitDate != null && limitDate.before(today))) {
			url = "task-register-failure.jsp";
		}else {
			
			/*String inputDataStr = request.getParameter("LimitDate");
			SimpleDataFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = sdf.parse("inputDate");
			Date todday =new Date();*/
			/*LocalDate date = null; 
			
			Date today = Date valueOf(LocalDate.now());
			
			if (memo.length() > maxlengs || (limitDate != null && limitDate.before(today))){
				
			}*/

			/*if(inputDate.before(today)) {
				response. sendRedirect("register-failure.jsp");
			}else {
				
			}*/

			//日付が入っていたらSQLに送る
			if (dateStr != null) {
				try {
					date = LocalDate.parse(request.getParameter("LimitDate"));
				} catch (DateTimeParseException e) {
					date = null;
				}
			}

		}

		//バラバラなので↑を一塊の入れ物TaskBeanに入れる
		//TaskBeanをインスタンス化
		TaskBean task = new TaskBean();

		task.setTaskName(taskName);
		task.setCategoryId(categoryId);
		task.setLimitDate(date);
		task.setUserId(userId);
		task.setStatusCode(statusCode);
		task.setMemo(memo);

		//データの受け取りのためにインスタンス化
		TaskCategoryDAO dao = new TaskCategoryDAO();

		try {
			/*daoクラスのinsertTaskメソッドに引数としてtaskを指定する。
			 * メソッドの戻り値(登録件数)をcountに入れる。*/
			int count = dao.insertTask(task);

			/*登録件数が１の場合、登録完了画面
			 * それ以外の場合、登録失敗画面*/
			RequestDispatcher rd;
			if (count == 1) {
				rd = request.getRequestDispatcher("register-success.jsp");
			} else {
				rd = request.getRequestDispatcher("register-failure.jsp");
			}

			/*転送する*/
			rd.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
