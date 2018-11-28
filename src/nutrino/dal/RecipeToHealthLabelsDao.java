package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.RecipeToHealthLabels;

public class RecipeToHealthLabelsDao {

	protected ConnectionManager connectionManager;

	private static RecipeToHealthLabelsDao instance = null;
	protected RecipeToHealthLabelsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeToHealthLabelsDao getInstance() {
		if(instance == null) {
			instance = new RecipeToHealthLabelsDao();
		}
		return instance;
	}

	public RecipeToHealthLabels create(RecipeToHealthLabels rec) throws SQLException {

		String insertRecipeToHealthLabels = "INSERT INTO recipe_to_health_labels (hid,label_name,recipe_name) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipeToHealthLabels);
			insertStmt.setInt(1, rec.gethId());
			insertStmt.setString(2, rec.getLabelName());
			insertStmt.setString(3, rec.getRecipeName());
			insertStmt.executeUpdate();
			return rec;
		} catch (SQLException e) {
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

	public RecipeToHealthLabels delete(RecipeToHealthLabels rec) throws SQLException {
		String deleteRecipeToHealthLabels = "DELETE FROM recipe_to_health_labels WHERE hid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipeToHealthLabels);
			deleteStmt.setInt(1, rec.gethId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete");
			}
			return null;
		} catch (SQLException e) {
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

	public RecipeToHealthLabels updateLabelName(RecipeToHealthLabels rec, String newLabel) throws SQLException {
		String updateRecipeToHealthLabels = "UPDATE recipe_to_health_labels SET label_name=? WHERE hId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipeToHealthLabels);
			updateStmt.setString(1, newLabel);
			updateStmt.setInt(2, rec.gethId());
			updateStmt.executeUpdate();
			rec.setLabelName(newLabel);
			return rec;
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

	public List<String> getLabels(String recipe) throws SQLException {
		List<String> labels = new ArrayList<String>();
		String selectLabelNames =
				"SELECT label_name FROM recipe_to_health_labels WHERE recipe_name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLabelNames);
			selectStmt.setString(1, recipe);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String label = results.getString("label_name");
				labels.add(label);
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
			if(results != null) {
				results.close();
			}
		}
		return labels;
	}
}
