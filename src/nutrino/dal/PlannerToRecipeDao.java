package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nutrino.model.PlannerToRecipe;

public class PlannerToRecipeDao {

	protected ConnectionManager connectionManager;

	private static PlannerToRecipeDao instance = null;
	protected PlannerToRecipeDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlannerToRecipeDao getInstance() {
		if(instance == null) {
			instance = new PlannerToRecipeDao();
		}
		return instance;
	}

	public PlannerToRecipe create(PlannerToRecipe plan) throws SQLException {

		String insertPlannerToRecipe = "INSERT INTO plannertorecipe (recipeId,planId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlannerToRecipe);
			insertStmt.setInt(1, plan.getRecipeId());
			insertStmt.setInt(2, plan.getPlanId());
			insertStmt.executeUpdate();
			return plan;
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

	public PlannerToRecipe delete(PlannerToRecipe plan) throws SQLException {
		String deletePlannerToRecipe = "DELETE FROM plannertorecipe WHERE pToRid=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlannerToRecipe);
			deleteStmt.setInt(1, plan.getpToRid());
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

	public PlannerToRecipe updatePlanId(PlannerToRecipe rec, int newPlanId) throws SQLException {
		String updatePlannerToRecipe = "UPDATE plannertorecipe SET planID=? WHERE pToRid=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePlannerToRecipe);
			updateStmt.setInt(1, newPlanId);
			updateStmt.setInt(2, rec.getpToRid());
			updateStmt.executeUpdate();
			rec.setPlanId(newPlanId);
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

	public int getRecipeId(int planId) throws SQLException {
		String selectRecipeId = "SELECT * FROM plannertorecipe WHERE planID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeId);
			selectStmt.setInt(1, planId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultrecipeID = results.getInt("recipeID");
				return resultrecipeID;
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
		return 0;
	}
}
