package nutrino.model;

public class Users {
	
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String privileges;
	
	public Users(String username, String password, String firstName, String lastName, String email, String privileges) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.privileges = privileges;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

}
