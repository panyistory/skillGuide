package userDb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		frontDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		frontDo(request, response);
	}

	protected void frontDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().equals("/skillGuide/write.do")) {
			response.sendRedirect("write/write.jsp?job="+request.getParameter("job"));
		} else if (request.getRequestURI().equals("/skillGuide/read.do")
				&& (request.getParameter("job").equals("worrior_1") || request.getParameter("job").equals("worrior_2")
						|| request.getParameter("job").equals("worrior_3")
						|| request.getParameter("job").equals("worrior_4"))) {
			
			// DAO ��ü ���� DB ���� �õ�
			DAO dao = new DAO();
			// �Ķ���� ��(�Ķ���� ��: job)�� ���ڷ� ���̺��� �˻��ؼ� �迭�� ���� dao.skillIdx �迭�� ����
			dao.selectDb(request.getParameter("job"));

			// Request �Ӽ� ����
			for (int i = 1; i <= dao.i - 2; i++)
				request.setAttribute("skill_" + i, dao.skillIdx[i - 1]);

			// RequestDispacher
			RequestDispatcher dispacher = request.getRequestDispatcher("read/"+request.getParameter("job") + ".jsp");
			dispacher.forward(request, response);
		}
		else {
			System.out.println("Error !");
		}
	}
}
