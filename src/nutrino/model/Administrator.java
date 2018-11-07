package nutrino.model;

import java.sql.Timestamp;

public class Administrator extends Users{
	
	protected Timestamp lastLogin;
	
	public Administrator(String username, Timestamp lastLogin) {
		super(username);
		this.lastLogin = lastLogin;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

}
