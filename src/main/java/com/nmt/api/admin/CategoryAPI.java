package com.nmt.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nmt.dto.CategoryDTO;
import com.nmt.service.impl.CategoryService;

@Controller(value = "adminCategoryAPI")
public class CategoryAPI {
	
	@Autowired
	private CategoryService categoryService;
	
	@PutMapping(value = "/admin/api/category", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO cateogryDTO) {
		return categoryService.save(cateogryDTO);
	}
	
	@PostMapping(value = "/admin/api/category", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.save(categoryDTO);
	}
	
	@DeleteMapping(value = "/admin/api/category")
	public void deleteCategory(@RequestBody Long[] ids) {
		categoryService.delete(ids);
	}
}
