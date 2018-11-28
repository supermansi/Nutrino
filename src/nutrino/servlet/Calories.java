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
import nutrino.dal.PlannerDao;
import nutrino.model.LoggedInUser;

@WebServlet("/calories")
public class Calories extends HttpServlet{

	protected PlannerDao plannerDao;
	protected LoggedInUserDao loggedInUserDao;
	
	@Override
	public void init() throws ServletException {
		plannerDao = plannerDao.getInstance();
		loggedInUserDao = loggedInUserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        LoggedInUser user;
        float calories;
        String username = req.getParameter("username");
        try {
        	calories = plannerDao.dailyAverageCalories(username);
        	user = loggedInUserDao.getLoggedInUserByUserName(username);
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying logged in user");
        req.setAttribute("user", user);
        req.setAttribute("calories", calories);
        //System.out.println(user.getUsername());
		req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
	}
}
