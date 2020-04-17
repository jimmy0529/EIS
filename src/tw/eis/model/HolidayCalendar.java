package tw.eis.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HolidayCalendar")
public class HolidayCalendar {
	private Date Date;
	private String DateType;
	private String Remark;
	private int EmployeeID;

	public HolidayCalendar() {
	}
	
	public HolidayCalendar(Date Date,String DateType,String Remark,int EmployeeID) {
		this.Date = Date;
		this.DateType = DateType;
		this.Remark = Remark;
		this.EmployeeID = EmployeeID;
	}

	@Id 
	@Column(name = "DATE")
	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		this.Date = date;
	}
	
	@Column(name = "DATETYPE")
	public String getDateType() {
		return DateType;
	}

	public void setDateType(String datetype) {
		this.DateType = datetype;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}
	
	@Column(name = "EMPLOYEEID") 
	public int getId() {
		return EmployeeID;
	}

	public void setId(int id) {
		this.EmployeeID = id;
	}
}
