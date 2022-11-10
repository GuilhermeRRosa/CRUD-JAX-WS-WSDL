package ggcodes.myws.services;

import java.sql.Connection;
import java.sql.SQLException;

import ggcodes.myws.dao.UserDAO;
import ggcodes.myws.dao.UserDAOImpl;
import ggcodes.myws.models.User;
import ggcodes.myws.util.SingleConnection;
import jakarta.jws.WebService;

@WebService(endpointInterface = "ggcodes.myws.services.UserSEI")
public class UserSIB implements UserSEI{

	@Override
	public long save(User u) {
		Connection con = SingleConnection.getConnection();
		UserDAO dao = new UserDAOImpl(con);
		long id = 0;
		try {
			id = dao.save(u);
			return id;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return id;
	}

	@Override
	public boolean delete(long id) {
		Connection con = SingleConnection.getConnection();
		UserDAO dao = new UserDAOImpl(con);
		try {
			return dao.delete(id);		
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public User getUser(long id) {
		Connection con = SingleConnection.getConnection();
		UserDAO dao = new UserDAOImpl(con);
		try {
			return dao.getUser(id);		
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

}
