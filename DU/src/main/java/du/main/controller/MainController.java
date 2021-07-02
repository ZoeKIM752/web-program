package du.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import du.user.domain.UserVO;
import du.user.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/main.do", method = RequestMethod.POST)
	public String mainPage(@ModelAttribute UserVO user) {
		if(userService.selectPwd(user.getId(), user.getPassword())){
			return "main.jsp";
		} else {
			return "login.jsp";
		}
	}
	
	@RequestMapping("/login.do")
	public String loginPage() {
		return "login.jsp";
	}
}
