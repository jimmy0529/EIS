package tw.eis.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.eis.model.AttendanceService;

@Controller
public class AttendancePunchSystem {

	private AttendanceService AttService;

	@Autowired
	public AttendancePunchSystem(AttendanceService AttService) {
		this.AttService = AttService;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) throws Exception {
		InetAddress localIp;
		localIp = InetAddress.getLocalHost();
		String ip = localIp.getHostAddress();
		System.out.println("IP:" + ip);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date Time1700 = cal.getTime();
		Date nowtime = new Date();
		
		if (ip.equals("192.168.27.143")) {
			if (nowtime.before(Time1700)) {
				boolean Insert = AttService.InsertAttendance(usersResultMap);
			} else {
				boolean Update = AttService.UpdateAttendance(usersResultMap);
			}
			System.out.println("IP正確");
		}else {
			System.out.println("IP錯誤");
		}
		return "AttendancePunch";
	}
}
