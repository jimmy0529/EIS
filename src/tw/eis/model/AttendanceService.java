package tw.eis.model;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

	private AttendanceDAO attendanceDao;

	@Autowired
	public AttendanceService(AttendanceDAO attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
	
	public List<Attendance> InquiryToday(Map<String, String> usersResultMap) {
		return attendanceDao.InquiryToday(usersResultMap);
	}
	
	public List<Attendance> InquiryAttendance(Map<String, String> usersResultMap, String month) {
		return attendanceDao.InquiryAttendance(usersResultMap, month);
	}
	
	public boolean InsertStartTime(Map<String, String> usersResultMap) {
		return attendanceDao.InsertStartTime(usersResultMap);
	}
	
	public boolean InsertEndTime(Map<String, String> usersResultMap) {
		return attendanceDao.InsertEndTime(usersResultMap);
	}
	
	public boolean UpdateEndTime(Map<String, String> usersResultMap) {
		return attendanceDao.UpdateEndTime(usersResultMap);
	}

}
