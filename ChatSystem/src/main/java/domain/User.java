package domain;

public class User {
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String name, password , email,ip;
	private Long phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public User(String name, String password, String email, Long phone, String ip) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.ip = ip;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", ip=" + ip + ", phone=" + phone
				+ "]";
	}
	
}
