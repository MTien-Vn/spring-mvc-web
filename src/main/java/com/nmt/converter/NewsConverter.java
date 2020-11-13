package com.nmt.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nmt.dto.NewDTO;
import com.nmt.entity.CategoryEntity;
import com.nmt.entity.NewsEntity;
import com.nmt.repository.CategoryRepository;

@Component
public class NewsConverter {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public NewDTO toDTO(NewsEntity newsEntity) {
		NewDTO newDTO = new NewDTO();
		newDTO.setId(newsEntity.getId());
		newDTO.setTitle(newsEntity.getTitle());
		newDTO.setContent(newsEntity.getContent());
		newDTO.setThumbnail(newsEntity.getThumbnail());
		newDTO.setShortDescription(newsEntity.getShortDescription());
		newDTO.setCategoryId(newsEntity.getCategoryEntity().getId());
		newDTO.setCategoryCode(newsEntity.getCategoryEntity().getCode());
		return newDTO;
	}
	
	public NewsEntity toEntity(NewDTO newDto) {
		NewsEntity newsEntity = new NewsEntity();
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDto.getCategoryCode());
		newsEntity.setCategoryEntity(categoryEntity);
		newsEntity.setTitle(newDto.getTitle());
		newsEntity.setContent(newDto.getContent());
		newsEntity.setThumbnail(newDto.getThumbnail());
		newsEntity.setShortDescription(newDto.getShortDescription());
		
		return newsEntity;
		
	}
	
	public NewsEntity toUpdateEntity(NewDTO newDto, NewsEntity oldEntity) {
		oldEntity.setTitle(newDto.getTitle());
		oldEntity.setContent(newDto.getContent());
		oldEntity.setThumbnail(newDto.getThumbnail());
		oldEntity.setShortDescription(newDto.getShortDescription());
		
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDto.getCategoryCode());
		oldEntity.setCategoryEntity(categoryEntity);
		return  oldEntity;
	}
}
