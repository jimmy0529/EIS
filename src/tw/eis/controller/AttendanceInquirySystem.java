package tw.eis.controller;

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
public class AttendanceInquirySystem {
	
	private AttendanceService AttService;

	@Autowired
	public AttendanceInquirySystem(AttendanceService AttService) {
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

	@RequestMapping(path = "/attendanceInquiry", method = RequestMethod.POST)
	public String InquiryAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,@RequestParam("month") String month, HttpServletRequest request) throws Exception {

		List<Attendance> attlist = AttService.AttendanceInquiry(usersResultMap,month);

		request.setAttribute("attlist",attlist);	
		
		return "AttendanceInquiry";
	}
}









