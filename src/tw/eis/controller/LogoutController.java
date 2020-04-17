package tw.eis.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tw.eis.model.Users;


@Controller
@RequestMapping(path ="/logout")
@SessionAttributes(names = {"usersResultMap", "errorMsgMap", "LoginOK"})
public class LogoutController {
	
	@RequestMapping(path ="/toLoginPage", method = RequestMethod.GET)
	public String toLoginPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, 
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, 
			@ModelAttribute("LoginOK") Users LoginOK, SessionStatus status) {
		
		status.setComplete();
		return "redirect:/login/userLogin";
	}
}
