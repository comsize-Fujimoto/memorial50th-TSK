package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.entity.TaskBean;

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
		TaskDAO tdao = new TaskDAO();
		
		//データベースから対象のタスクを読み出し
		TaskBean taskBean = dao.selectTask(taskId);
		
		//セッションスコープを使えるようにする
		HttpSession session = request.getSession();
		
		//セッションスコープに書き出し
		session.setAttribute("updateTask", taskBean);
		
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
