package userCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDAO.DAO;
import userDAO.DTO;

public class WriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("job");
		int skill_1 = Integer.parseInt(request.getParameter("skill_1"));
		int skill_2 = Integer.parseInt(request.getParameter("skill_2"));
		int skill_3 = Integer.parseInt(request.getParameter("skill_3"));
		int skill_4 = Integer.parseInt(request.getParameter("skill_4"));
		
		DTO dto = new DTO(name, skill_1, skill_2, skill_3, skill_4);
		
		
		DAO dao = new DAO();
		int state = dao.modifyDb(dto);
		System.out.println("True or False, 1 equal Success! [" + state + "]");
		request.setAttribute("insertState", state);
	}
}
