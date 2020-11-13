package com.nmt.converter;

import org.springframework.stereotype.Component;

import com.nmt.dto.CategoryDTO;
import com.nmt.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryDTO toDTO(CategoryEntity categoryEntity) {
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setCode(categoryEntity.getCode());
		categoryDto.setName(categoryEntity.getName());
		
		return categoryDto;
	}
	
	public CategoryEntity toEntity(CategoryDTO categoryDto) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCode(categoryDto.getCode());
		categoryEntity.setName(categoryDto.getName());
		
		return categoryEntity;
	}
	
	public CategoryEntity toUpdateEntity(CategoryDTO newDto, CategoryEntity oldEntity) {
		oldEntity.setCode(newDto.getCode());
		oldEntity.setName(newDto.getName());
		
		return oldEntity;
	}
}
