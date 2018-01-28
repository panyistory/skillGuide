package userCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.DAO;
import userDAO.DTO;

public class ReadCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		DTO dto = dao.selectDb(request.getParameter("job").toString());
		request.setAttribute("dto", dto);
	}

}
