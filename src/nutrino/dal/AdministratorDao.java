package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import nutrino.model.Administrator;

public class AdministratorDao {
	
protected ConnectionManager connectionManager;
	
	private static AdministratorDao instance = null;
	protected AdministratorDao() {
		connectionManager = new ConnectionManager();
	}
	public static AdministratorDao getInstance() {
		if(instance == null) {
			instance = new AdministratorDao();
		}
		return instance;
	}
	
	// CREATE
	public Administrator create(Administrator user)throws SQLException {
		String insertUser = "INSERT INTO Administrator(username, height, weight, diet) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUsername());
			insertStmt.setTimestamp(2, user.getLastLogin());
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
	
	// READ
	public Administrator getAdministratorByUserName(String UserName) throws SQLException {
		String selectUser = "SELECT * FROM Administrator WHERE username=?;";
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
				Timestamp lastLogin = results.getTimestamp("lastLogin");
				Administrator user = new Administrator(resultUserName, lastLogin);
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
	
	// UPDATE
	public Administrator updateLastLogin(Administrator user, Timestamp lastLogin) throws SQLException {
		String updateFirstName = "UPDATE administrator SET lastLogin=? WHERE username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFirstName);
			updateStmt.setTimestamp(1, lastLogin);
			updateStmt.setString(2, user.getUsername());
			updateStmt.executeUpdate();
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
	
	// DELETE
	public Administrator delete(Administrator user) throws SQLException {
		String deleteUser = "DELETE FROM Administrator WHERE UserName=?;";
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
