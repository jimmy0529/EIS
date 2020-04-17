package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HolidayCalendarDAO {

	private SessionFactory sessionFacotry;

	@Autowired
	public HolidayCalendarDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public List<HolidayCalendar> InqueryCalendar(int year) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			String hqlstr = "from HolidayCalendar where Date like :Year order by Date";
			Query<HolidayCalendar> query = session.createQuery(hqlstr, HolidayCalendar.class);
			query.setParameter("Year", year + "%");
			List<HolidayCalendar> calenderlist = query.list();
			return calenderlist;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public boolean InsertCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utildate = nowdate.parse(date);
			java.sql.Date Date = new java.sql.Date(utildate.getTime());
			HolidayCalendar calendar = new HolidayCalendar();
			calendar.setDate(Date);
			calendar.setDateType(dateType);
			calendar.setRemark(remark);
			calendar.setId(Integer.parseInt(usersResultMap.get("EmployeeID")));
			session.save(calendar);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	public boolean UpdateCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utildate = nowdate.parse(date);
			java.sql.Date Date = new java.sql.Date(utildate.getTime());
			String hqlstr = "Update HolidayCalendar SET DateType=:DateType , Remark=:Remark ,EmployeeID=:EmployeeID where Date=:Date";
			Query query = session.createQuery(hqlstr);
			query.setParameter("DateType", dateType);
			query.setParameter("Remark", remark);
			query.setParameter("EmployeeID", usersResultMap.get("EmployeeID"));
			query.setParameter("Date", Date);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	public boolean DeleteCalendar(List<String> date) {
		try {
			Session session = sessionFacotry.getCurrentSession();
			for (String element : date) {
				String hqlstr = "Delete from HolidayCalendar where Date =:Date";
				Query query = session.createQuery(hqlstr);
				query.setParameter("Date", element);
				query.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

}
