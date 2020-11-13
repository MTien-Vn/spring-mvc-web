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

import com.nmt.dto.CategoryDTO;
import com.nmt.service.impl.CategoryService;
import com.nmt.util.MessageUtil;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/admin/category/listCategory", method = RequestMethod.GET)
	public ModelAndView showCategory(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletRequest request) {
		CategoryDTO model = new CategoryDTO();
		model.setPage(page);
		model.setSize(size);
		ModelAndView mav = new ModelAndView("admin/category/listCategory");
		Pageable pageable = new PageRequest(page-1, size);
		model.setListResult(categoryService.findAllPageable(pageable));
		model.setTotalItem(categoryService.totalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getSize()));
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/admin/category/editCategory", method = RequestMethod.GET)
	public ModelAndView editCateogry(@RequestParam(value="id", required=false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/category/editCategory");
		CategoryDTO model = new CategoryDTO();
		if(id != null) {
			model = categoryService.findOneById(id);
		}
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	

}
