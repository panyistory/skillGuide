package userCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.DAO;
import userDAO.DTO;

public class BjoinCmd implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		DAO dao = new DAO();
		String userInputId = request.getParameter("id");
		String userInputPw = request.getParameter("pw");
		String userInputEmail = request.getParameter("email");
		status = dao.userJoin(new DTO(userInputId, userInputPw, userInputEmail));
		request.setAttribute("joinStatus", status);
	}

}
