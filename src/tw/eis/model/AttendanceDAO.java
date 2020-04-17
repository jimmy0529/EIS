package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceDAO {

	private SessionFactory sessionFacotry;

	@Autowired
	public AttendanceDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public List<Attendance> InquiryToday(Map<String, String> usersResultMap) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String today = nowdate.format(new Date());
			String hqlstr = "from Attendance where EmployeeID=:EmployeeID and Date =:Date";
			Query<Attendance> query = session.createQuery(hqlstr, Attendance.class);
			query.setParameter("EmployeeID", usersResultMap.get("EmployeeID"));
			query.setParameter("Date", today);
			List<Attendance> myPunch = query.list();
			return myPunch;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public List<Attendance> InquiryAttendance(Map<String, String> usersResultMap, String month) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			String hqlstr = "from Attendance where EmployeeID=:id and Date like :Month";
			Query<Attendance> query = session.createQuery(hqlstr, Attendance.class);
			query.setParameter("id", usersResultMap.get("EmployeeID"));
			query.setParameter("Month", month + "%");

			List<Attendance> attlist = query.list();

			return attlist;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public boolean InsertStartTime(Map<String, String> usersResultMap) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String datestr = nowdate.format(new Date());
			String timestr = nowtime.format(new Date());
			Date utilDate = nowdate.parse(datestr);
			Date utilTime = nowtime.parse(timestr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			java.sql.Time Time = new java.sql.Time(utilTime.getTime());

			Attendance attendance = new Attendance();
			attendance.setId(Integer.parseInt(usersResultMap.get("EmployeeID")));
			attendance.setDate(Date);
			attendance.setStartTime(Time);
			session.save(attendance);
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return true;
	}

	public boolean InsertEndTime(Map<String, String> usersResultMap) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String datestr = nowdate.format(new Date());
			String timestr = nowtime.format(new Date());
			Date utilDate = nowdate.parse(datestr);
			Date utilTime = nowtime.parse(timestr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			java.sql.Time Time = new java.sql.Time(utilTime.getTime());

			Attendance attendance = new Attendance();
			attendance.setId(Integer.parseInt(usersResultMap.get("EmployeeID")));
			attendance.setDate(Date);
			attendance.setEndTime(Time);
			session.save(attendance);
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return true;
	}

	public boolean UpdateEndTime(Map<String, String> usersResultMap) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String datestr = nowdate.format(new Date());
			String timestr = nowtime.format(new Date());
			Date utilDate = nowdate.parse(datestr);
			Date utilTime = nowtime.parse(timestr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			java.sql.Time Time = new java.sql.Time(utilTime.getTime());

			String hqlstr = "Update Attendance SET EndTime=:Time where Date=:Date";
			Query query = session.createQuery(hqlstr);
			query.setParameter("Time", Time);
			query.setParameter("Date", Date);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return true;
	}

}
