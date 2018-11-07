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

}
