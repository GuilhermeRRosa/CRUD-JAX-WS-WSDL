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
	
	//CREATE - UPDATE
	@Override
	public long save(User u) throws SQLException {
		Statement stmt = null;
		String sql = "";
		long id = 0;			
		if(u.getId()!=0) {
			id = u.getId();
			sql = "UPDATE INTO Usuario SET username = \'"+u.getUsername()+"\', password = \'"+u.getPassword()+"\' WHERE id = "+id+" RETURNING ID";
			try {
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
		} else {
			sql = "INSERT INTO Usuario (username, password) VALUES (\'"+u.getUsername()+"\', \'"+u.getPassword()+"\') RETURNING ID";
			try {
				
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
		}	
		return id;
	}

	//DELETE
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

	//READ
	@Override
	public User getUser(long id) throws SQLException {
		User u = new User();
		PreparedStatement statement = null;
		try {
			
			String sql = "SELECT * FROM usuario WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				u.setId(rs.getLong("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			statement.close();
		}
		return u;
	}

}
