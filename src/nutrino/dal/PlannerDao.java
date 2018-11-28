package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.Plan;
import nutrino.model.Planner;

public class PlannerDao {
	
	protected ConnectionManager connectionManager;
	
	private static PlannerDao instance = null;
	protected PlannerDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlannerDao getInstance() {
		if(instance == null) {
			instance = new PlannerDao();
		}
		return instance;
	}
	
	// CREATE
	public Planner create(Planner plan)throws SQLException{
		String insertPlan = "INSERT INTO Planner(username, day, time) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlan,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, plan.getUser().getUsername());
			insertStmt.setString(2, plan.getDay().name());
			insertStmt.setString(3, plan.getTime().name());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int planID = -1;
			if(resultKey.next()) {
				planID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			plan.setPlanID(planID);
			return plan;
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
	public Planner getPlanByPlanID(int planID) throws SQLException {
		String selectPlan = "SELECT * FROM Planner WHERE planID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlan);
			selectStmt.setInt(1, planID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("username");
				UsersDao userDao = UsersDao.getInstance();
				Planner.Day day = Planner.Day.valueOf(results.getString("day"));
				Planner.Time time = Planner.Time.valueOf(results.getString("time"));
				Planner plan = new Planner(planID, userDao.getUserByUserName(resultUserName), day, time);
				return plan;
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
		return null;
	}
	
	public List<Planner> getPlanByUsername(String username) throws SQLException {
		String selectPlan = "SELECT * FROM Planner WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Planner> planners = new ArrayList<Planner>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlan);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int planID = results.getInt("planID");
				UsersDao userDao = UsersDao.getInstance();
				Planner.Day day = Planner.Day.valueOf(results.getString("day"));
				Planner.Time time = Planner.Time.valueOf(results.getString("time"));
				Planner plan = new Planner(planID, userDao.getUserByUserName(username), day, time);
				planners.add(plan);
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
		return planners;
	}
	
	// UPDATE
	public Planner updateDay(Planner plan, String day) throws SQLException {
		String updateDay = "UPDATE Planner SET day=? WHERE planID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDay);
			updateStmt.setString(1, day);
			updateStmt.setInt(2, plan.getPlanID());
			updateStmt.executeUpdate();
			return plan;
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
	public Planner delete(Planner plan) throws SQLException {
		String deleteUser = "DELETE FROM Planner WHERE planID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, plan.getPlanID());
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
	
	public List<Plan> getPlannerByUsername(String username) throws SQLException {
		String selectPlan = "SELECT Planner.day, Planner.time, Recipe.label FROM Planner JOIN PlannerToRecipe ON Planner.planID = PlannerToRecipe.planID JOIN Recipe ON PlannerToRecipe.recipeID = Recipe.id WHERE username = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Plan> planners = new ArrayList<Plan>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlan);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String day = results.getString("day");
				String time = results.getString("time");
				String label = results.getString("label");
				Plan plan = new Plan(day, time, label);
				planners.add(plan);
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
		return planners;
	}
	
	public float dailyAverageCalories(String username) throws SQLException {
		String findCalories = "SELECT SUM(calories)/7 AS Daily_Average_Calories FROM Recipe JOIN PlannerToRecipe ON Recipe.id = PlannerToRecipe.recipeID JOIN Planner ON PlannerToRecipe.planID = Planner.planID WHERE Planner.username = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		float dailyAverageCalories = (float) 0.0;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(findCalories);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if(results.next()) {
				dailyAverageCalories = results.getFloat(1);
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
		return dailyAverageCalories;
	}

}
