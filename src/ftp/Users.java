package ftp;

public class Users {
	private String userName;
	private boolean teacher;
	private String eMail;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean isTeacher() {
		return teacher;
	}
	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}
	
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Users() {
		
	}
	
	public Users(String userName, boolean teacher, String eMail, String password) {
		this.userName = userName;
		this.teacher = teacher;
		this.eMail = eMail;
		this.password = password;
	}
	
	
	
}
