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
import nutrino.dal.PlannerDao;
import nutrino.dal.PlannerToRecipeDao;
import nutrino.model.LoggedInUser;
import nutrino.model.Plan;
import nutrino.model.Planner;
import nutrino.model.PlannerToRecipe;

@WebServlet("/editplan")
public class EditPlan extends HttpServlet {
	
	protected PlannerDao plannerDao;
	protected PlannerToRecipeDao planToRDao;
	protected LoggedInUserDao loggedInUserDao;

	
	@Override
	public void init() throws ServletException {
		plannerDao = plannerDao.getInstance();
		planToRDao = planToRDao.getInstance();
		loggedInUserDao = loggedInUserDao.getInstance();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String username = req.getParameter("username");
        int recipeID= Integer.parseInt(req.getParameter("recipeID"));
        String day = req.getParameter("day");
        String time = req.getParameter("time");    
        LoggedInUser user;
        Planner planner = null;
        PlannerToRecipe planToR = null;
        List<Plan> planners = new ArrayList<Plan>();
        try {
        	System.out.println(username);
        	user = loggedInUserDao.getLoggedInUserByUserName(username);
            Planner plan = new Planner(user, Planner.Day.valueOf(day), Planner.Time.valueOf(time));
            planner = plannerDao.create(plan);
        	PlannerToRecipe pToR = new PlannerToRecipe(recipeID, plan.getPlanID());
        	planToR = planToRDao.create(pToR);
        	planners = plannerDao.getPlannerByUsername(username);

        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("planner", planners);
		req.getRequestDispatcher("/Planner.jsp?username="+username).forward(req, resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int recipeID = Integer.parseInt(req.getParameter("recipeID"));
        messages.put("success", "Displaying logged in user");
        req.setAttribute("recipeID", recipeID);
        //System.out.println(user.getUsername());
		req.getRequestDispatcher("/EditPlanner.jsp").forward(req, resp);
	}
	

}
