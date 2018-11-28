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

import nutrino.dal.PlannerDao;
import nutrino.dal.PlannerToRecipeDao;
import nutrino.dal.RecipesDao;
import nutrino.model.Plan;

@WebServlet("/planner")
public class ViewPlanner extends HttpServlet  {
	
	protected PlannerDao plannerDao;
	protected PlannerToRecipeDao planToRDao;
	protected RecipesDao recipeDao;
	
	@Override
	public void init() throws ServletException {
		plannerDao = plannerDao.getInstance();
		planToRDao = planToRDao.getInstance();
		recipeDao = recipeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String username = req.getParameter("username");
        List<Plan> planner = new ArrayList<Plan>();
        try {
        	planner = plannerDao.getPlannerByUsername(username);
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("planner", planner);
        req.getRequestDispatcher("/Planner.jsp").forward(req, resp);
	}

}
