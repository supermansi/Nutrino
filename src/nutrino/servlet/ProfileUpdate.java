package nutrino.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nutrino.dal.LoggedInUserDao;
import nutrino.model.LoggedInUser;

@WebServlet("/profileupdate")
public class ProfileUpdate extends HttpServlet{
	
	protected LoggedInUserDao loggedInUserDao;
	
	@Override
	public void init() throws ServletException {
		loggedInUserDao = loggedInUserDao.getInstance();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        LoggedInUser user;
        String username = req.getParameter("username");
        String diet = req.getParameter("diet");
        try {
        	user = loggedInUserDao.getLoggedInUserByUserName(username);
        	user = loggedInUserDao.updateDiet(user, diet);
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("user", user);
		req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
	}

}
