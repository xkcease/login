package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import po.DataJson;
import po.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

		// 注册页面跳转
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "register_page.html";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public DataJson register(@RequestBody User user) {
		boolean flag = false;
		DataJson dataJson = new DataJson();
		
		
		flag = userService.register(user, dataJson);
		
		if(flag) {						// 结果检测
			dataJson.setState(true);
			System.out.println("success");
		}
		else {
			dataJson.setState(false);
			System.out.println(dataJson);
		}
		
		return dataJson;
	}
	
		// 登录页面跳转
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login_page.html";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public DataJson login(@RequestBody User user, HttpServletRequest request) {

		
		DataJson dataJson = userService.login(user);
		
		if(dataJson.isState()) {		//	登录成功
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(10 * 60);		// 有效期10min
			session.setAttribute("user", user);
			System.out.println("login success");
		}
		else {
			System.out.println("login fail");
		}
		
		return dataJson;
	}
	
	
		// 登录成功跳转
	@RequestMapping(value="/success", method=RequestMethod.GET)
	public String loginSuccess(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("user"));
		return "index.jsp";
	}

	
		// 获取用户信息
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@ResponseBody
	public String getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		return user.getUsername();
	}
	
	
		// 登出
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("user");
		session.invalidate();
		System.out.println("logout");
		return "redirect:/login";
	}

		// 忘记密码页面跳转
	@RequestMapping(value="/forget", method=RequestMethod.GET)
	public String forgetPassword() {
		return "forget_page.html";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public DataJson forgetPassword(@RequestBody User user) {
		boolean flag = false;
		DataJson dataJson = new DataJson();
		
		
		flag = userService.forgetPassword(user, dataJson);
		if(flag) {						// 结果检测
			dataJson.setState(true);
			System.out.println("success");
		}
		else {
			dataJson.setState(false);
			System.out.println(dataJson);
		}
		
		return dataJson;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
