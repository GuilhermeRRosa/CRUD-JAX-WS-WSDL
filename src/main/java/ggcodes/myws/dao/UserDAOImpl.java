package ggcodes.myws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ggcodes.myws.models.User;

public class UserDAOImpl implements UserDAO {

	private Connection connection;
	
	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public long save(User u) throws SQLException {
		Statement stmt = null;
		long id = 0;
		try {
			
			String sql = "INSERT INTO Usuario (username, password) VALUES (\'"+u.getUsername()+"\', \'"+u.getPassword()+"\') RETURNING ID";
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				id = rs.getLong(1);
			}			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			stmt.close();
		}
		return id;
	}

	@Override
	public boolean delete(long id) throws SQLException {
		PreparedStatement statement = null;
		try {
			
			String sql = "delete from usuario WHERE id = ?";
			statement = this.connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			statement.close();
		}
		return false;
	}

}
