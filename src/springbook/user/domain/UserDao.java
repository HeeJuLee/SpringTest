package springbook.user.domain;

public class UserDao {

	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
	}
}
