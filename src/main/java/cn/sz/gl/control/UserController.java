package cn.sz.gl.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IUserService;

@Controller
@RequestMapping("/uc")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="islogin",method=RequestMethod.POST)
	public String islogin(Users user,HttpServletRequest request) {
		Users u = userService.islogin(user);
		if(u!=null) {
			request.getSession().setAttribute("myuser", u);
			return "main";
		}
		return "login";
	}
	
	@RequestMapping(value="findbyid",method=RequestMethod.POST)
	public String checkuser(Integer userid,Model model) {
		System.out.println("进入checkuser方法...");
		Users u = userService.findbyid(userid);
		model.addAttribute("userinfo", u);
		return "user_info";
	}
	
}
