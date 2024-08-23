//login.jspからPOSTで送られてきたものをdoPOSTで受け取る
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskLoginDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		インスタンス化する
		TaskLoginDAO dao = new TaskLoginDAO();
		
		UserBean user = null;
		
		// 入力されたユーザIDとパスワードを取得する

		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		try {
			
			// 入力されたユーザIDとパスワードでユーザの情報を取得する
			//id,pwを引数としてdaoのログインメソッドを呼び出す
			user = dao.login(userId, password);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		String url = "";// 転送先
		// ログイン認証
		if (user != null) {
			// セッションオブジェクトを生成
			HttpSession session = request.getSession();
			// セッションスコープへユーザ名を設定
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userId", user.getUserId());

			url = "task-menu.jsp";// メニュー画面
		} else {
			url = "login.failure.jsp";// ログイン失敗画面
		}
//		RequestDispatcherオブジェクトのforwardメソッドを使い
//		リクエストの転送をする
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
//	
	}

}
