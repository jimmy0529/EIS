package tw.eis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Users;
import tw.eis.model.UsersService;

@Controller
@SessionAttributes(names = {"employeeid","username","userpassword","errors"})
public class CheckLoginSystem {
	
	private UsersService UService;

	@Autowired
	public CheckLoginSystem(UsersService UService) {
		this.UService = UService;
	}
	
	@RequestMapping(path = "/preLoginAction.controller", method = RequestMethod.GET)
	public String processAction() {
		return "loginSystem";
	}

	@RequestMapping(path = "/checkLogin.controller", method = RequestMethod.POST)
	public String processLoginSystemAction(@RequestParam("username") String username, @RequestParam("userpassword") String userpassword, Model model) {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		if(username==null || username.length()==0) {
			errors.put("username", "name is required.");
        }
		
		if(userpassword==null || userpassword.length()==0) {
			errors.put("userpassword", "password is required.");
        }
		
		if(errors!=null && !errors.isEmpty()) {
			return "loginSystem";
		}
		model.addAttribute("username", username);
		model.addAttribute("userpassword", userpassword);
		boolean status = UService.checkLogin(new Users(username,userpassword));
		if(status) {
			int id = UService.getEmployeeID();
			model.addAttribute("employeeid",id);
			return "LeaveSystem";
		}
		
		errors.put("msg", "username or password is not correct.");		
		
		return "loginSystem";
	}
}









