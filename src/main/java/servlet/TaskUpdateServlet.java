package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDAO;
import model.dao.TaskDAO;
import model.entity.CategoryBean;
import model.entity.CommentBean;
import model.entity.StatusBean;
import model.entity.TaskBean;

/**
 * @author dadadaaaaaaike
 */

/**
 * Servlet implementation class UpdateTaskServlet
 */
@WebServlet("/update-task-servlet")
public class TaskUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定する
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを取得する
		int taskId = Integer.parseInt(request.getParameter("task_code"));
		
		//TaskDAOのインスタンス化
		TaskDAO dao = new TaskDAO();
		
		//データベースから対象のタスクを読み出し
		TaskBean taskBean = dao.selectTask(taskId);
		//データベースからカテゴリとステータスを読み出し
		Map<Integer,CategoryBean> categoryMap = dao.allCategory();
		Map<String,StatusBean> statusMap = dao.allStatus();
		
		//データベースからコメント情報を呼び出し
		CommentDAO comDao = new CommentDAO();
		
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		try {
			commentList = comDao.commentDisplay(taskId);
						
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//セッションスコープを使えるようにする
		HttpSession session = request.getSession();
		
		//セッションスコープに書き出し
		session.setAttribute("updateTask", taskBean);
		session.setAttribute("categoryMap", categoryMap);
		session.setAttribute("statusMap", statusMap);
		
		//コメントをセット
		session.setAttribute("commentList" ,commentList );
		
		//メニュー画面のパスを指定して転送処理用のオブジェクトを取得する
		RequestDispatcher rd = request.getRequestDispatcher("task-detail.jsp");
		
		//リクエストの転送
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定する
		request.setCharacterEncoding("UTF-8");
		
		//セッション開始
		HttpSession session = request.getSession();
		
		//セッションからタスクBeanを取得する
		TaskBean updateTask = (TaskBean) session.getAttribute("updateTask");
		
		//リクエストパラメータを取得する
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		String limitDate = request.getParameter("limitDate");
		String dateBeforeUpdate = "";
		if(updateTask.getLimitDate() != null) {
			dateBeforeUpdate = updateTask.getLimitDate().toString();
		}
		String beforeMemo = "";
		if(updateTask.getMemo() != null) {
			beforeMemo = updateTask.getMemo();
		}
		
		//リクエストパラメータをTaskBeanと比較して、変更点があったら変更し、なかったらエラーを出す
		
		Boolean updateFlag = false; //変更点の有無を識別する更新フラグ
		
		//各項目のいずれかで変更があった場合に変更部分をTaskBeanに適用して、更新フラグをtrueにする
		if(!(taskName.equals(updateTask.getTaskName()))) {
			updateTask.setTaskName(taskName);
			updateFlag = true;
		}
		if(categoryId != updateTask.getCategoryId()) {
			updateTask.setCategoryId(categoryId);
			updateFlag = true;
		}
		if(!(statusCode.equals(updateTask.getStatusCode()))) {
			updateTask.setStatusCode(statusCode);
			updateFlag = true;
		}
		
		if(!(limitDate.equals(dateBeforeUpdate))) { 
			Date date;
			if(limitDate.isEmpty()) {
				date = null;
			}else {
				date = Date.valueOf(request.getParameter("limitDate"));
			}
			updateTask.setLimitDate(date);
			updateFlag = true;
		}
		if(!(memo.equals(beforeMemo))) {
			if(memo.isEmpty()) {
				updateTask.setMemo(null);
			}else {
				updateTask.setMemo(memo);
			}
			updateFlag = true;
		}
		
		//更新フラグがtureの場合はTaskDAOのupdateTaskメソッドで更新を適用する
		
		if(updateFlag) {
			//TaskDAOのインスタンス化
			TaskDAO dao = new TaskDAO();
			
			//データベースにTaskBeanの内容を書き込み
			int updateCount = dao.updateTask(updateTask);
			
			//データベースから更新したデータを再読み込み
			updateTask = dao.selectTask(updateTask.getTaskId());
			
			//セッションに更新したデータを書き出し
			session.setAttribute("updateTask", updateTask);
			
			//updateCountの内容に応じて転送先を指定する
			RequestDispatcher rd;
			if(updateCount == 1) {
				//タスク編集完了画面のパスを指定して転送処理用のオブジェクトを取得する
				rd = request.getRequestDispatcher("task-update-success.jsp");
			}else {
				//タスク編集失敗画面のパスを指定して転送処理用のオブジェクトを取得する
				rd = request.getRequestDispatcher("task-update-failure.jsp");
			}
			//リクエストの転送
			rd.forward(request, response);
			
		}else {
			
			//タスク編集未遂画面のパスを指定して転送処理用のオブジェクトを取得する
			RequestDispatcher rd = request.getRequestDispatcher("task-update-not-input.jsp");
			
			//リクエストの転送
			rd.forward(request, response);
		}
	}
}
