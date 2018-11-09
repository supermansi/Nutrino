package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nutrino.model.LoggedInUser;
import nutrino.model.LoggedInUser.DietLabels;

public class LoggedInUserDao {
	
	protected ConnectionManager connectionManager;
	
	private static LoggedInUserDao instance = null;
	protected LoggedInUserDao() {
		connectionManager = new ConnectionManager();
	}
	public static LoggedInUserDao getInstance() {
		if(instance == null) {
			instance = new LoggedInUserDao();
		}
		return instance;
	}
	
	public LoggedInUser create(LoggedInUser user)throws SQLException {
		String insertUser = "INSERT INTO LoggedInUser(username, height, weight, diet) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUsername());
			insertStmt.setFloat(2, user.getHeight());
			insertStmt.setFloat(3, user.getWeight());
			insertStmt.setString(4, user.getDiet().name());
			insertStmt.executeUpdate();
			return user;
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
	
	public LoggedInUser getLoggedInUserByUserName(String UserName) throws SQLException {
		String selectUser = "SELECT * FROM LoggedInUser WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, UserName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("userName");
				Float height = results.getFloat("height");
				Float weight = results.getFloat("weight");
				LoggedInUser.DietLabels diet = LoggedInUser.DietLabels.valueOf(results.getString("diet"));
				LoggedInUser user = new LoggedInUser(resultUserName, height, weight, diet);
				return user;
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
	
	public LoggedInUser updateDiet(LoggedInUser user, String diet) throws SQLException {
		String userUpdateDiet = "UPDATE loggedinuser SET diet=? WHERE username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(userUpdateDiet);
			updateStmt.setString(1, diet);
			updateStmt.setString(2, user.getUsername());
			updateStmt.executeUpdate();
			
			user.setDiet(LoggedInUser.DietLabels.valueOf(diet));
			return user;
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
	
	public LoggedInUser delete(LoggedInUser user) throws SQLException {
		String deleteUser = "DELETE FROM LoggedInUser WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUsername());
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
