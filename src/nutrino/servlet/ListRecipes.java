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

import nutrino.dal.RecipesDao;
import nutrino.model.Recipe;

@WebServlet("/listrecipes")
public class ListRecipes extends HttpServlet {
	
	protected RecipesDao recipeDao;
	
	@Override
	public void init() throws ServletException {
		recipeDao = recipeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("in get");
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String label = req.getParameter("diet");
		System.out.println(label);     
        List<Recipe> recipes = new ArrayList<Recipe>();
        try {
        	recipes = recipeDao.listRecipeByLabel(label);
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying results for users");
        req.setAttribute("recipes", recipes);
		req.getRequestDispatcher("/ListRecipes.jsp").forward(req, resp);
	}

}
