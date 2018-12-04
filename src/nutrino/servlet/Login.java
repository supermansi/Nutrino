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

@WebServlet("/userlogin")
public class Login extends HttpServlet {

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
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/LoggedInHome.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users user = null;
        LoggedInUser lUser = null;
        float bmi = 0.0f;
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	String password = req.getParameter("password");
	        try {
	        	user = usersDao.getUserByUserCredentials(userName, password);
	        	messages.put("success", "Successfully created " + userName);
	        	// req.setAttribute("userName", user.getFirstName());
	        	lUser = loggedInUserDao.getLoggedInUserByUserName(user.getUsername());
	        	bmi = lUser.getWeight()/(lUser.getHeight() * lUser.getHeight() / 10000);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.setAttribute("user", lUser);
        req.setAttribute("bmi", bmi);
        req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
     /*   else{ 
        	req.getRequestDispatcher("/ListUsers.jsp").forward(req, resp);
        }*/
    }
}
