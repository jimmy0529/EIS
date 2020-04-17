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
public class HolidayCalendarSystem {

	private HolidayCalendarService CService;

	@Autowired
	public HolidayCalendarSystem(HolidayCalendarService CService) {
		this.CService = CService;
	}
	
	@RequestMapping(path = "/SelectCalendar", method = RequestMethod.GET)
	public String SelectCalendar(HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		List<HolidayCalendar> calenderlist = CService.SelectCalendar(year);
		request.setAttribute("calenderlist", calenderlist);
		return "HolidayCalendar";
	}
	
	@RequestMapping(path = "/InsertWeekend", method = RequestMethod.POST)
	public String InsertWeekend(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("year") int year, HttpServletRequest request) {
		boolean Insert = CService.InsertWeekend(usersResultMap, year);
		return "redirect:/SelectCalendar";
	}

	@RequestMapping(path = "/InsertHoliday", method = RequestMethod.POST)
	public String InsertHoliday(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("date") String date, @RequestParam("remark") String remark, HttpServletRequest request) {
		boolean Insert = CService.InsertHoliday(usersResultMap, date, remark);
		return "redirect:/SelectCalendar";
	}
	
	@RequestMapping(path = "/UpdateWorkday", method = RequestMethod.POST)
	public String UpdateWorkday(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("date") String date, @RequestParam("remark") String remark, HttpServletRequest request) {
		boolean Update = CService.UpdateWorkday(usersResultMap, date, remark);
		return "redirect:/SelectCalendar";
	}

	@RequestMapping(path = "/DeleteCalendar", method = RequestMethod.POST)
	public String DeleteCalendar(@RequestParam("Date") List<String> date) {

		boolean Delete = CService.DeleteCalendar(date);
		return "redirect:/SelectCalendar";
	}

}
