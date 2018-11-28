package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutrino.model.Search;

public class SearchDao {


	protected ConnectionManager connectionManager;

	private static SearchDao instance = null;
	protected SearchDao() {
		connectionManager = new ConnectionManager();
	}
	public static SearchDao getInstance() {
		if(instance == null) {
			instance = new SearchDao();
		}
		return instance;
	}

	public Search create(Search search) throws SQLException {

		String insertSearch = "INSERT INTO Search (searchId,userName,keyword) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSearch);
			insertStmt.setInt(1, search.getSearchId());
			insertStmt.setString(2, search.getUserName());
			insertStmt.setString(3, search.getKeyword());
			insertStmt.executeUpdate();
			return search;
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

	public Search delete(Search search) throws SQLException {
		String deleteSearch = "DELETE FROM Search WHERE searchId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSearch);
			deleteStmt.setInt(1, search.getSearchId());
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

	public Search updateKeyword(Search search, String newKeyword) throws SQLException {
		String updateSearch = "UPDATE Search SET keyword=? WHERE searchId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSearch);
			updateStmt.setString(1, newKeyword);
			updateStmt.setInt(2, search.getSearchId());
			updateStmt.executeUpdate();

			// Update the person param before returning to the caller.
			search.setKeyword(newKeyword);
			return search;
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

	public List<String> getKeywords(int searchId) throws SQLException {
		List<String> keywords = new ArrayList<String>();
		String selectKeywords =
				"SELECT keyword FROM Search WHERE searchId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywords);
			selectStmt.setInt(1, searchId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String keyword = results.getString("keyword");
				keywords.add(keyword);
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
		return keywords;
	}
}
