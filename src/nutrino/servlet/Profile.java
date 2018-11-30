package nutrino.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nutrino.dal.LoggedInUserDao;
import nutrino.model.LoggedInUser;
import nutrino.model.Users;

@WebServlet("/profile")
public class Profile extends HttpServlet{
	
	protected LoggedInUserDao loggedInUserDao;
	
	@Override
	public void init() throws ServletException {
		loggedInUserDao = loggedInUserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        LoggedInUser user;
        String username = req.getParameter("username");
        float bmi;
        try {
        	user = loggedInUserDao.getLoggedInUserByUserName(username);
        	bmi = user.getWeight()/(user.getHeight() * user.getHeight() / 10000);
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying logged in user");
        req.setAttribute("user", user);
        req.setAttribute("bmi", bmi);
        //System.out.println(user.getUsername());
		req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
	}

}
