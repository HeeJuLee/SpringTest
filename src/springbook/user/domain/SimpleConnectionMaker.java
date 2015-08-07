package springbook.user.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {

	public Connection makeConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook", "spring", "book"
			);
		
		return c;
	}
}
