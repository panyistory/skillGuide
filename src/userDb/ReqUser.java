package userDb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userCommand.BCommand;
import userCommand.BjoinCmd;
import userCommand.BloginCmd;
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

		if(reqUrl.equals("/loginOk.do")) {
			command = new BloginCmd();
			command.execute(request, response);
			viewPage = "index.jsp";
		}
		else if (reqUrl.equals("/joinOk.do")) {
			command = new BjoinCmd();
			command.execute(request, response);
			viewPage = "index.jsp"; // to be continue : joginSuccess page create plz
		}

		// RequestDispacher
		RequestDispatcher dispacher = request.getRequestDispatcher(viewPage);
		dispacher.forward(request, response);

	}
}
