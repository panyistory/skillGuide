package userCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userDAO.DAO;
import userDAO.DTO;

public class BloginCmd implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int status;
		String userInputId = request.getParameter("id");
		String userInputPw = request.getParameter("pw");
		DAO dao = new DAO();
		status = dao.userLogin(new DTO(userInputId, userInputPw));
		
		if (status == 1) {
			System.out.println("Session Setting and Save Start.");
			HttpSession session = request.getSession();
			session.setAttribute("userLoginSuccessId", userInputId);
		}
	}

}
