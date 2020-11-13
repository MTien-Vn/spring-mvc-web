package com.nmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nmt.dto.CategoryDTO;

public interface ICategoryService {
	Map<String, String> findAll();
	List<CategoryDTO> findAllPageable(Pageable pageable);
	List<CategoryDTO> findAllCate();
	CategoryDTO findOneById(Long id);
	CategoryDTO save(CategoryDTO categoryDTO);
	CategoryDTO findOneByCode(String code);
	int totalItem();
	void delete(Long[] ids);
}
