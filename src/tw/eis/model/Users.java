package tw.eis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "EMPLOYEEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeID;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "USERPASSWORD")
	private String userPassword;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DEPARTMENT")
	private String department;
	
//	@Column(name = "UUID")
//	private String uUID;
//
//	public String getuUID() {
//		return uUID;
//	}
//
//	public void setuUID(String uUID) {
//		this.uUID = uUID;
//	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
