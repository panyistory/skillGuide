package userDb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userCommand.BCommand;
import userCommand.ReadCommand;
import userCommand.WriteCommand;

/**
 * Servlet implementation class ReqUser
 */
@WebServlet("*.do")
public class ReqUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReqUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		frontDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		frontDo(request, response);
	}

	protected void frontDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null;
		BCommand command = null;
		String reqUrl = request.getRequestURI().toString().substring(request.getContextPath().length());

		if (reqUrl.equals("/read.do")) {
			command = new ReadCommand();
			command.execute(request, response);
			viewPage = "jobInfo.jsp?job=" + request.getParameter("job");;
		}else if (reqUrl.equals("/write.do")) { // 공략 수정을 들어 갔을 경우
			command = new ReadCommand();
			command.execute(request, response);
			viewPage = "write.jsp?job=" + request.getParameter("job");;
		}else if (reqUrl.equals("/write_apply.do")) { // wirte.jsp 페이지 에서 적용을 눌렀을 경우
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "index.jsp";
		}

		// RequestDispacher
		RequestDispatcher dispacher = request.getRequestDispatcher(viewPage);
		dispacher.forward(request, response);

	}
}
