package nutrino.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutrino.model.HealthLabels;

public class HealthLabelsDao {

	protected ConnectionManager connectionManager;

	private static HealthLabelsDao instance = null;
	protected HealthLabelsDao() {
		connectionManager = new ConnectionManager();
	}
	public static HealthLabelsDao getInstance() {
		if(instance == null) {
			instance = new HealthLabelsDao();
		}
		return instance;
	}

	public HealthLabels create(HealthLabels label) throws SQLException {

		String insertHealthLabels = "INSERT INTO healthlabels (labelName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHealthLabels);
			insertStmt.setString(1, label.getLabelName());
			insertStmt.executeUpdate();
			return label;
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

	public HealthLabels delete(HealthLabels label) throws SQLException {
		String deleteHealthLabels = "DELETE FROM healthlabels WHERE labelName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHealthLabels);
			deleteStmt.setString(1, label.getLabelName());
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

	public HealthLabels updateLabel(HealthLabels label, String newLabel) throws SQLException {
		String updateHealthLabels = "UPDATE healthlabels SET labelName=? WHERE labelName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHealthLabels);
			updateStmt.setString(1, newLabel);
			updateStmt.setString(2, label.getLabelName());
			updateStmt.executeUpdate();
			label.setLabelName(newLabel);
			return label;
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

	public List<String> getLabels() throws SQLException {
		List<String> labels = new ArrayList<String>();
		String selectLabelNames =
				"SELECT * FROM healthlabels;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLabelNames);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String label = results.getString("labelName");
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
