package ggcodes.myws.dao;

import java.sql.SQLException;

import ggcodes.myws.models.User;

public interface UserDAO {

	long save(User u) throws SQLException;
	boolean delete(long id) throws SQLException;
	
}
