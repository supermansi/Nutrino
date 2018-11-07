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

import nutrino.dal.UsersDao;
import nutrino.model.Users;

@WebServlet("/listusers")
public class ListUsers extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = usersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("in get");
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Users> users = new ArrayList<Users>();
        try {
        	users = usersDao.getAllUsers();
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying results for users");
        req.setAttribute("users", users);
		req.getRequestDispatcher("/ListUsers.jsp").forward(req, resp);
	}

}
