package tw.eis.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayCalendarService {

	private HolidayCalendarDAO calendarDao;

	@Autowired
	public HolidayCalendarService(HolidayCalendarDAO calendarDao) {
		this.calendarDao = calendarDao;
	}

	public List<HolidayCalendar> InqueryCalendar(int year) {
		return calendarDao.InqueryCalendar(year);
	}

	public boolean InsertCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		return calendarDao.InsertCalendar(usersResultMap, date, dateType, remark);
	}

	public boolean UpdateCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		return calendarDao.UpdateCalendar(usersResultMap, date, dateType, remark);
	}

	public boolean DeleteCalendar(List<String> date) {
		return calendarDao.DeleteCalendar(date);
	}

}
