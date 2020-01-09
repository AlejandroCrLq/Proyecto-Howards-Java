package ftp;

public class Users {
	private String eMail;
	private String password;
	private boolean teacher;
	private String userName;

	public Users() {

	}

	public Users(String userName, boolean teacher, String eMail, String password) {
		this.userName = userName;
		this.teacher = teacher;
		this.eMail = eMail;
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isTeacher() {
		return teacher;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}