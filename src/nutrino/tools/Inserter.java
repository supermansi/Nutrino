package nutrino.tools;

import java.sql.SQLException;
import java.util.List;

import nutrino.dal.UsersDao;
import nutrino.model.Users;

public class Inserter {
	
	public static void main(String args[]) throws SQLException {
		
		UsersDao  usersDao = UsersDao.getInstance();
		
		Users user = usersDao.getUserByUserName("alice");
		System.out.println(user.getFirstName());
		
		List<Users> users = usersDao.getAllUsers();
		for(Users u: users) {
			System.out.println(u.getUsername() + "  " + u.getFirstName() + " " + u.getLastName() + " " + u.getEmail());
		}
		
	}

}
