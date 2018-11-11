package nutrino.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/recipeSearch")
public class Search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected RecipesDao recipeDao;

	@SuppressWarnings("static-access")
	@Override
	public void init() throws ServletException {
		recipeDao = recipeDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		String recipeName = req.getParameter("recipeName");
		try {
			List<Recipe> recipes = recipeDao.getRecipeByName(recipeName);
			 req.setAttribute("recipes", recipes);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.getRequestDispatcher("/Search.jsp").forward(req, resp);
	}
}
