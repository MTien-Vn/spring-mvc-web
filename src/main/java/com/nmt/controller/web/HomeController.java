package com.nmt.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nmt.service.impl.CategoryService;
import com.nmt.service.impl.NewService;

@Controller(value = "homeControllerWeb")
public class HomeController {
	
	@Autowired
	private NewService newsService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		Pageable pageable = new PageRequest(0, 3);
		mav.addObject("model", newsService.findAll(pageable));
		mav.addObject("categories", categoryService.findAllCate());
		return mav;
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav  = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return new ModelAndView("redirect: /trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView mav  = new ModelAndView("redirect: /trang-chu/accessDenied");
		return mav;
	}

}
