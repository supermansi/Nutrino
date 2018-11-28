package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.Recipe;

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
	
	// CREATE
	public Recipe create(Recipe recipe)throws SQLException{
		String insertRecipe = "INSERT INTO Recipe(uri, label, image, url, calories, ingredient_lines, total_weight, health_labels) VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipe,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recipe.getUri());
			insertStmt.setString(2, recipe.getLabel());
			insertStmt.setString(3, recipe.getImage());
			insertStmt.setString(4, recipe.getUrl());
			insertStmt.setString(5, recipe.getCalories());
			insertStmt.setString(6, recipe.getIngredient_lines());
			insertStmt.setString(7, recipe.getTotal_weight());
			insertStmt.setString(8, recipe.getHealth_labels());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int recipeID = -1;
			if(resultKey.next()) {
				recipeID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipe.setId(recipeID);
			return recipe;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	// READ
	
	public List<Recipe> getRecipeByName(String RecipeName) throws SQLException {
		String selectRecipe = "SELECT * from recipe where label like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Recipe> recipeList = new ArrayList<Recipe>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setString(1, "%" + RecipeName + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String uri = results.getString("uri");
				String label = results.getString("label");
				String image = results.getString("image");
				String url = results.getString("url");
				String calories = results.getString("calories");
				String ingredient_lines = results.getString("ingredient_lines");
				String total_weight = results.getString("total_weight");
				String health_labels = results.getString("health_labels");
				Recipe recipe = new Recipe(id,uri, label,image,url,calories, ingredient_lines,total_weight, health_labels);
				recipeList.add(recipe);
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
		return recipeList;
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
				int id = results.getInt("id");
				String uri = results.getString("uri");
				String newLabel = results.getString("label");
				String image = results.getString("image");
				String url = results.getString("url");
				String calories = results.getString("calories");
				String ingredient_lines = results.getString("ingredient_lines");
				String total_weight = results.getString("total_weight");
				String health_labels = results.getString("health_labels");
				Recipe recipe = new Recipe(id, uri, newLabel, image, url, calories, ingredient_lines, total_weight, health_labels);
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
	
	// UPDATE
	public Recipe updateCalories(Recipe recipe, String calories) throws SQLException {
		String updateCalories = "UPDATE Recipe SET calories=? WHERE id=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCalories);
			updateStmt.setString(1, calories);
			updateStmt.setInt(2, recipe.getId());
			updateStmt.executeUpdate();
			return recipe;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	// DELETE
	public Recipe delete(Recipe recipe) throws SQLException {
		String deleteUser = "DELETE FROM Recipe WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, recipe.getId());
			deleteStmt.executeUpdate();

			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
