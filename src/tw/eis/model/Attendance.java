package tw.eis.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Attendance")
public class Attendance {
	private int EmployeeID;
	private Date Date;
	private Time StartTime;
	private Time EndTime;
	private String Status;
	private String LeaveType;

	public Attendance() {
	}
	
	public Attendance(Date Date,Time StartTime,Time EndTime,String Status,String LeaveType) {
		this.Date = Date;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Status = Status;
		this.LeaveType = LeaveType;
	}

	@Column(name = "EMPLOYEEID") 
	public int getId() {
		return EmployeeID;
	}

	public void setId(int id) {
		this.EmployeeID = id;
	}

	@Id 
	@Column(name = "DATE")
	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		this.Date = date;
	}
	
	@Column(name = "STARTTIME")
	public Time getStartTime() {
		return StartTime;
	}

	public void setStartTime(Time starttime) {
		this.StartTime = starttime;
	}
	
	@Column(name = "ENDTIME")
	public Time getEndTime() {
		return EndTime;
	}

	public void setEndTime(Time endtime) {
		this.EndTime = endtime;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}
	
	@Column(name = "LEAVETYPE")
	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leavetype) {
		this.LeaveType = leavetype;
	}
	
}
