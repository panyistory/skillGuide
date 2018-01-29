package userDAO;

public class DTO {
	String id = null;
	String pw = null;
	String eMail = null;
	
	public DTO(String id, String pw, String eMail) {
		this.id = id;
		this.pw = pw;
		this.eMail = eMail;
	}
	
	public DTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
