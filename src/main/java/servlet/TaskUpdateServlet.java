package servlet;

import java.io.IOException;
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
		Map<Integer,StatusBean> statusMap = dao.allStatus();
		
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
		
		
		
	}

}
