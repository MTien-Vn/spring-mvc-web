package com.nmt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerAdmmin")
public class HomeController {
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav  = new ModelAndView("admin/home");
		return mav;
	}
	
	@RequestMapping(value = "/admin/thoat", method = RequestMethod.GET)
	public ModelAndView exit() {
		return new ModelAndView("redirect: /trang-chu");
	}
	
	

}
