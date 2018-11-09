package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.Recipe;
import nutrino.model.Users;

public class RecipesDao {
	
protected ConnectionManager connectionManager;
	
	private static RecipesDao instance = null;
	protected RecipesDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipesDao getInstance() {
		if(instance == null) {
			instance = new RecipesDao();
		}
		return instance;
	}
	
	public List<Recipe> listRecipeByLabel(String label) throws SQLException {
		String selectRecipes = "SELECT * FROM Recipe WHERE health_labels LIKE ? LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Recipe> recipes = new ArrayList<Recipe>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
			selectStmt.setString(1, "%" + label + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String uri = results.getString("uri");
				String newLabel = results.getString("label");
				String image = results.getString("image");
				String url = results.getString("url");
				String calories = results.getString("calories");
				String ingredient_lines = results.getString("ingredient_lines");
				String total_weight = results.getString("total_weight");
				String health_labels = results.getString("health_labels");
				Recipe recipe = new Recipe(uri, newLabel, image, url, calories, ingredient_lines, total_weight, health_labels);
				recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
		}
		return recipes;
	}

}
