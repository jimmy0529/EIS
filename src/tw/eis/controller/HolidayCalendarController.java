package tw.eis.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;

@Controller
public class HolidayCalendarController {

	private HolidayCalendarService CService;

	@Autowired
	public HolidayCalendarController(HolidayCalendarService CService) {
		this.CService = CService;
	}

	@RequestMapping(path = "/InqueryCalendar", method = RequestMethod.GET)
	public String InqueryCalendar(HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		List<HolidayCalendar> calenderlist = CService.InqueryCalendar(year);
		request.setAttribute("calenderlist", calenderlist);
		return "HolidayCalendarSetup";
	}

	@RequestMapping(path = "/HolidayAction", method = RequestMethod.POST)
	public String HolidayAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("action") int action, @RequestParam("date") String date,
			@RequestParam("dateType") String dateType, @RequestParam("remark") String remark,
			HttpServletRequest request) {
		if (action == 1) {
			boolean Insert = CService.InsertCalendar(usersResultMap, date, dateType, remark);
		} else {
			boolean Update = CService.UpdateCalendar(usersResultMap, date, dateType, remark);
		}
		return "redirect:/InqueryCalendar";
	}

	@RequestMapping(path = "/DeleteCalendar", method = RequestMethod.POST)
	public String DeleteCalendar(@RequestParam("Date") List<String> date) {
		boolean Delete = CService.DeleteCalendar(date);
		return "redirect:/InqueryCalendar";
	}

}
