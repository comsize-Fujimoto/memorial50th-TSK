package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
//HttpServletを継承したTaskAddServlerをシリアライズ
public class TaskAddServler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//GETリクエストがあった場合にdoGetメソッドを実行
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			//リクエストのエンコーディングを指定
			request.setCharacterEncoding("UTF-8");

			//TaskCategoryDAOに入っているメソッドを使うためにインスタンス化
			TaskCategoryDAO dao = new TaskCategoryDAO();

			/* DAOからデータを受け取るための箱、
			 * カテゴリ情報を保存するためのリスト、CategoryBean型の順序付きListを作成
			 * ステータス情報を保存するためのリストを用意する*/
			List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
			List<StatusBean> statusList = new ArrayList<StatusBean>();

			//カテゴリリストとステータスリストにselectメソッドの処理結果を代入
			categoryList = dao.selectCategory();
			statusList = dao.selectStatus();

			//session開始
			HttpSession session = request.getSession();

			//セッションにカテゴリリストとステータスリストのデータを一時的に保存する
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("statusList", statusList);

			//転送先の指定
			RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
			rd.forward(request, response);
		} catch (SQLException | ClassNotFoundException | ServletException | IOException e) {
			//エラーの内容をコンソールに表示
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//登録が完了した場合と失敗した場合で、遷移先を変更する。
		//まずurlの初期値を空文字に設定する
		String url = "";

		/*セッション呼び出し*/
		HttpSession session = request.getSession();
		/*セッションスコープへユーザ名を設定
		セッションにユーザーネームとユーザーIDのデータを一時的に保存する*/
		session.getAttribute("userName");
		session.getAttribute("userId");

		//登録内容をServletで受け取り、DAOに送る
		//(タスク名、カテゴリ情報、期限)
		String taskName = request.getParameter("TaskName");
		int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
		String userId = (String) session.getAttribute("userId");
		String statusCode = request.getParameter("StatusCode");
		String memo = request.getParameter("memo");

		//DBで使うためのDate型のlimitDate変数を宣言（入れ物作り）
		LocalDate limitDate;

		//limit_dateのデータが空でないかどうかを確認するための条件分岐
		/* 期限(LimitDate)を型に変換
		* それ以外、期限(LimitDate)にnullが入る*/
		if (!request.getParameter("LimitDate").isEmpty()) {
			limitDate = LocalDate.parse(request.getParameter("LimitDate"));
		} else {
			limitDate = null;
		}

		/*最大文字数制限を設定する*/
		int maxlength = 100;
		/*条件分岐　memoの文字数制限を超えている場合または、期日が過去の日付の場合、
		 * 登録失敗画面へ遷移(エラーチェック)*/
		/*trueの場合、urlを登録失敗画面へ遷移する*/
		if (memo.length() > maxlength || (limitDate != null && limitDate.isBefore(LocalDate.now()))) {
			url = "register-failure.jsp";
		} else {
			//TaskBeanをインスタンス化
			TaskBean task = new TaskBean();
			//バラバラなので↓を一塊の入れ物TaskBeanに入れる
			task.setTaskName(taskName);
			task.setCategoryId(categoryId);
			task.setLimitDate(limitDate);
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
				if (count == 1) {
					url = "register-success.jsp";
				} else {
					url = "register-failure.jsp";
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();

			}

		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
