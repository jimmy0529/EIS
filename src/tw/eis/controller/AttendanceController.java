package tw.eis.controller;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;

@Controller
public class AttendanceController {

	private AttendanceService AttService;

	@Autowired
	public AttendanceController(AttendanceService AttService) {
		this.AttService = AttService;
	}

	@RequestMapping(path = "/InquiryPage", method = RequestMethod.GET)
	public String goToInquiryPage() {
		return "AttendanceInquiry";
	}

	@RequestMapping(path = "/PunchPage", method = RequestMethod.GET)
	public String goToPunchPage() {
		return "AttendancePunch";
	}

	@RequestMapping(path = "/InquiryAttendance", method = RequestMethod.POST)
	public String InquiryAttendance(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		List<Attendance> attlist = AttService.InquiryAttendance(usersResultMap, month);
		request.setAttribute("attlist", attlist);
		return "AttendanceInquiry";
	}

	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(usersResultMap);
		request.setAttribute("myPunch", myPunch);
		return "AttendancePunch";
	}

	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) throws Exception {
		InetAddress localIp;
		localIp = InetAddress.getLocalHost();
		String ip = localIp.getHostAddress();
		System.out.println("IP:" + ip);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date Time1700 = cal.getTime();
		Date nowtime = new Date();
		List<Attendance> myPunch = AttService.InquiryToday(usersResultMap);
		
		if (ip.equals("192.168.27.143")) {
			
			if (nowtime.before(Time1700)) {
				if(myPunch==null||myPunch.size()==0) {
					boolean Insert = AttService.InsertStartTime(usersResultMap);
				}else {
					boolean Update = AttService.UpdateEndTime(usersResultMap);
				}
			} else {
				if(myPunch==null||myPunch.size()==0) {
					boolean Insert = AttService.InsertEndTime(usersResultMap);
				}else {
					boolean Update = AttService.UpdateEndTime(usersResultMap);
				}
			}
			
			System.out.println("IP正確");
		} else {
			System.out.println("IP錯誤");
		}
		return "redirect:/InquiryToday";
	}
}
