package model;

public class User{
	private int id;
	private String username;
	private String password;
	private String name;
	private String role;
	
	public User() {
		super();
	}

	public User(String username, String password, String name, String position) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
