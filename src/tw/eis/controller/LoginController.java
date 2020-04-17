package tw.eis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;
import tw.eis.model.UsersService;
import tw.eis.util.AESUtil;
import tw.eis.util.EmailUtil;



@Controller
@SessionAttributes(names = {"usersResultMap", "errorMsgMap", "LoginOK", "userName"})
@RequestMapping(path ="/login")
public class LoginController {
	private UsersService uService;
	private EmployeeService eService;
	AESUtil aes = new AESUtil();
	
	@Autowired
	public LoginController(UsersService uService, EmployeeService eService){
		this.uService = uService;
		this.eService=eService;
		
	}
	
	@RequestMapping(path = "/userLogin", method = RequestMethod.GET)
	public String goToLoginPage() {
		return "UserLogin";
	}
	
	@RequestMapping(path = "/userLoginCheck", method = {RequestMethod.POST, RequestMethod.GET})
	public String processLoginAction(@RequestParam(value="userName", required=false) String userName,
			@RequestParam(value="userPassword", required=false) String userPassword, Model model) throws IOException, Exception {
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		model.addAttribute("errorMsgMap", errorMsgMap);
		
		if((userName==null || userName.trim().length() == 0) && (userPassword==null || userPassword.trim().length() == 0)) {
			errorMsgMap.put("NoLoginError", "Please Login again");
			return "UserLogin";
		}

		if (userName == null || userName.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "User account should not be empty");
		}
		
		if (userPassword == null || userPassword.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "User password should not be empty");
		}

		if (!errorMsgMap.isEmpty()) {
			return "UserLogin";
		}
		
		String encryptPwd=aes.parseByte2HexStr(aes.encrypt(userPassword));
		
		List<Users> loginResult=uService.findUsers(userName, encryptPwd);
		
		if(loginResult.size()>0) {
			Iterator<Users> loginResultIT = loginResult.iterator();
			Users uBean = loginResultIT.next();
			model.addAttribute("LoginOK", uBean);
			Map<String, String> usersResultMap = new HashMap<String, String>();
			usersResultMap.put("EmployeeID", String.valueOf(uBean.getEmployeeID()));
			usersResultMap.put("UserName", uBean.getUserName());
			usersResultMap.put("UserPassword", encryptPwd);
			usersResultMap.put("Title", uBean.getTitle());
			usersResultMap.put("Department", uBean.getDepartment());
			model.addAttribute("usersResultMap", usersResultMap);
			return "LoginSucess";
			
		}
		else {
			errorMsgMap.put("LoginError", "Account doesn't exit or password wrong");
			return "UserLogin";
		}
	} 
	
	@RequestMapping(path = "/forgetPwd", method = RequestMethod.GET)
	public String goToSendEmailPage() {
		return "CheckEmail";
	}
	
	@RequestMapping(path = "/sendMail", method = {RequestMethod.POST, RequestMethod.GET})
	public String ProcessForgotPwd(@RequestParam("email") String email, Model model) {
		Map<String, String> errorMsgFromForgetPwd = new HashMap<String, String>();
		model.addAttribute("errorMsgFromForgetPwd", errorMsgFromForgetPwd);
		
		if (email == null || email.trim().length() == 0) {
			errorMsgFromForgetPwd.put("emailEmptyError", "E-mail should not be empty");
			return "CheckEmail";
		}
		
		List<Employee> employeeByEmail=eService.findEmployeeByEmail(email);
		
		if(employeeByEmail.size() <= 0) {						
			errorMsgFromForgetPwd.put("emailNotFound", "E-mail沒被註冊過");
			return "CheckEmail";
		}
		
		Iterator<Employee> employeeByEmailIT = employeeByEmail.iterator();
		Employee eBean = employeeByEmailIT.next();
		List<Users> uList=uService.findUsersByID(eBean.getEmpID());
		Iterator<Users> uListIT=uList.iterator();
		Users uBean=uListIT.next();
		EmailUtil eUtil=new EmailUtil();
		eUtil.sendResetPasswordEmail(uBean, email);
		errorMsgFromForgetPwd.put("emailSucess", "提交成功，請查看你的E-mail信箱");
		return "CheckEmail";
	}
	
	@RequestMapping(path = "/resetPassword", method = RequestMethod.GET)
	public String goToResetPwdPage(@RequestParam("userName") String userName, Model model) {
		model.addAttribute("userName",userName);
		return "resetPassword";
	}
	
	@RequestMapping(path = "/passwordChange", method = RequestMethod.POST)
	public String resetPew(@ModelAttribute("userName") String userName, @RequestParam("psw") String psw, Model model) {
		String encryptPwd=aes.parseByte2HexStr(aes.encrypt(psw));
		Boolean status=uService.updateUsersPassword(userName, encryptPwd);
		Map<String, String> MsgFromPwdReset = new HashMap<String, String>();
		model.addAttribute("MsgFromPwdReset", MsgFromPwdReset);
		if(status) {
			MsgFromPwdReset.put("resetSuccess", "密碼重設成功，請重新登入");
			return "UserLogin";
		}
		MsgFromPwdReset.put("resetFailed", "密碼重設失敗，請再試一次");
		return "resetPassword";
	}
	
}