package com.nmt.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nmt.dto.NewDTO;
import com.nmt.service.ICategoryService;
import com.nmt.service.INewService;
import com.nmt.util.MessageUtil;

@Controller(value = "newsControllerAdmmin")
public class NewsController {
	
	@Autowired
	private INewService newsService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/admin/news/list", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletRequest request) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setSize(size);
		ModelAndView mav = new ModelAndView("admin/news/list");
		Pageable pageable = new PageRequest(page -1, size);
		model.setListResult(newsService.findAll(pageable));
		model.setTotalItem(newsService.totalItem());
		model.setTotalPage((int)Math.ceil((double) model.getTotalItem() / model.getSize()));
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
	public ModelAndView editNews(@RequestParam(value="id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav  = new ModelAndView("admin/news/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newsService.findOneById(id);
		}
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
	

}
