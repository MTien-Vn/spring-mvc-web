package com.nmt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nmt.converter.CategoryConverter;
import com.nmt.dto.CategoryDTO;
import com.nmt.entity.CategoryEntity;
import com.nmt.entity.NewsEntity;
import com.nmt.repository.CategoryRepository;
import com.nmt.repository.NewsRepository;
import com.nmt.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryConverter categoryConverter;
	@Autowired
	private NewsRepository newRepository;

	@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<>();
		List<CategoryEntity> categoryList  = categoryRepository.findAll();
		for (CategoryEntity categoryEntity : categoryList) {
			result.put(categoryEntity.getCode(), categoryEntity.getName());
			
		}
		return result;
	}

	@Override
	public CategoryDTO findOneById(Long id) {
		CategoryEntity cateEntity = categoryRepository.findOne(id);
		return categoryConverter.toDTO(cateEntity);
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		if(categoryDTO.getId() == null) {
			CategoryEntity cateEntity = categoryConverter.toEntity(categoryDTO);
			cateEntity = categoryRepository.save(cateEntity);
			return categoryConverter.toDTO(cateEntity);
		}else {
			CategoryEntity cateEntity = categoryRepository.findOne(categoryDTO.getId());
			cateEntity = categoryConverter.toUpdateEntity(categoryDTO, cateEntity);
			cateEntity = categoryRepository.save(cateEntity);
			return categoryConverter.toDTO(cateEntity);
		}
	}

	@Override
	public CategoryDTO findOneByCode(String code) {
		return categoryConverter.toDTO(categoryRepository.findOneByCode(code));
	}

	@Override
	public List<CategoryDTO> findAllPageable(Pageable pageable) {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<CategoryEntity> CateEntity = categoryRepository.findAll(pageable).getContent();
		for (CategoryEntity categoryEntity : CateEntity) {
			CategoryDTO cateDto = categoryConverter.toDTO(categoryEntity);
			result.add(cateDto);
		}
		return result;
	}

	@Override
	public int totalItem() {
		return (int)categoryRepository.count();
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			CategoryEntity categoryEntity = categoryRepository.findOne(id);
			List<NewsEntity> listNews = categoryEntity.getNews();
			for (NewsEntity news : listNews) {
				newRepository.delete(news.getId());
			}
			categoryRepository.delete(id);
		}
		
	}

	@Override
	public List<CategoryDTO> findAllCate() {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<CategoryEntity> listEntity = categoryRepository.findAll();
		for (CategoryEntity categoryEntity : listEntity) {
			CategoryDTO categoryDto = categoryConverter.toDTO(categoryEntity);
			result.add(categoryDto);
		}
		return result;
	}
}
