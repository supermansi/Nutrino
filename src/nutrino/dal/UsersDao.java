package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.Users;

public class UsersDao {
	
	protected ConnectionManager connectionManager;
	
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	// CREATE
	public Users create(Users user)throws SQLException {
		String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Privileges) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPrivileges());
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
	public List<Users> getAllUsers() throws SQLException {
		String findAll = "SELECT * FROM Users;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Users> users = new ArrayList<Users>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(findAll);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultUserName = results.getString("userName");
				String Password = results.getString("password");
				String FirstName = results.getString("firstName");
				String LastName = results.getString("lastName");
				String Email = results.getString("email");
				String Privileges = results.getString("privileges");
				Users user = new Users(resultUserName, Password, FirstName, LastName, Email, Privileges);
				users.add(user);
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
		return users;

	}
	
	public Users getUserByUserName(String UserName) throws SQLException {
		String selectUser = "SELECT * FROM Users WHERE username=?;";
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
				String Password = results.getString("password");
				String FirstName = results.getString("firstName");
				String LastName = results.getString("lastName");
				String Email = results.getString("email");
				String Privileges = results.getString("privileges");
				Users user = new Users(resultUserName, Password, FirstName, LastName, Email, Privileges);
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
	
	public Users getUserByUserCredentials(String UserName,String Password) throws SQLException {
		String selectUser = "SELECT * FROM Users WHERE username=? and password =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, UserName);
			selectStmt.setString(2, Password);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("userName");
				String FirstName = results.getString("firstName");
				String LastName = results.getString("lastName");
				String Email = results.getString("email");
				String Privileges = results.getString("privileges");
				Users user = new Users(resultUserName, Password, FirstName, LastName, Email, Privileges);
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
	public Users updateFirstName(Users user, String firstName) throws SQLException {
		String updateFirstName = "UPDATE users SET firstName=? WHERE username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFirstName);
			updateStmt.setString(1, firstName);
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
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
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
