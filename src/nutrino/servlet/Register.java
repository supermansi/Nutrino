package nutrino.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nutrino.dal.LoggedInUserDao;
import nutrino.dal.UsersDao;
import nutrino.model.LoggedInUser;
import nutrino.model.Users;
import sun.util.logging.resources.logging_zh_CN;

@WebServlet("/usercreate")
public class Register extends HttpServlet {

	protected UsersDao usersDao;
	protected LoggedInUserDao loggedInUserDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = usersDao.getInstance();
		loggedInUserDao = loggedInUserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/LoggedInHome.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users user = null;
    	float bmi = 0.0f;
        LoggedInUser loginEntry = null;
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String password = req.getParameter("password");
        	float height = Float.parseFloat(req.getParameter("height"));
        	float weight = Float.parseFloat(req.getParameter("weight"));
        	String diet = req.getParameter("diet");
        	user = new Users(userName, password, firstName, lastName, email, "0");
	        try {
	        	loginEntry = new LoggedInUser(userName, height, weight, LoggedInUser.DietLabels.valueOf(diet));
	        	user = usersDao.create(user);
	        	bmi = loginEntry.getWeight()/(loginEntry.getHeight() * loginEntry.getHeight() / 10000);
	        	loggedInUserDao.create(loginEntry);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
		req.setAttribute("bmi", bmi);
        req.setAttribute("user", loginEntry);
        req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
    }
}
