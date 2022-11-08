package ggcodes.myws.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static final Connection CONNECTION = connect();
	
	private static Connection connect() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/jaxws", "postgres", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Connection getConnection() {
		return CONNECTION;
	}

}
