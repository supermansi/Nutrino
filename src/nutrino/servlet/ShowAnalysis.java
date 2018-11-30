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
import nutrino.model.Analysis;

@WebServlet("/analysis")
public class ShowAnalysis extends HttpServlet {
	
	protected RecipesDao recipesDao;
	
	@Override
	public void init() throws ServletException {
		recipesDao = recipesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Analysis> analysis = new ArrayList<Analysis>();
		try {
			analysis = recipesDao.fetchAnalysis("rice");
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Displaying analysis");
		req.setAttribute("analysis", analysis);
		req.getRequestDispatcher("ShowAnalysis.jsp").forward(req, resp);
	}

}
