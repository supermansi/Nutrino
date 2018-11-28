package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nutrino.model.Favorites;

public class FavoritesDao {

	protected ConnectionManager connectionManager;

	private static FavoritesDao instance = null;
	protected FavoritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavoritesDao getInstance() {
		if(instance == null) {
			instance = new FavoritesDao();
		}
		return instance;
	}

	public Favorites create(Favorites fav) throws SQLException {

		String insertFavorites = "INSERT INTO favorites (username,recipeID) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFavorites);
			insertStmt.setString(1, fav.getUsername());
			insertStmt.setInt(2, fav.getRecipeID());
			insertStmt.executeUpdate();
			return fav;
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

	public Favorites delete(Favorites fav) throws SQLException {
		String deleteFavorites = "DELETE FROM favorites WHERE favoriteID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFavorites);
			deleteStmt.setInt(1, fav.getFavoriteID());
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

	public Favorites updateFavorites(Favorites fav, String newUsername) throws SQLException {
		String updateFavorites = "UPDATE favorites SET username=? WHERE favoriteID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFavorites);
			updateStmt.setString(1, newUsername);
			updateStmt.setInt(2, fav.getFavoriteID());
			updateStmt.executeUpdate();
			fav.setUsername(newUsername);
			return fav;
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

	public int getRecipeId(int favId) throws SQLException {
		String selectRecipeId = "SELECT * FROM favorites WHERE favoriteID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeId);
			selectStmt.setInt(1, favId);
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
