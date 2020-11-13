package com.nmt.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nmt.converter.NewsConverter;
import com.nmt.dto.NewDTO;
import com.nmt.dto.UploadFileDTO;
import com.nmt.entity.NewsEntity;
import com.nmt.repository.NewsRepository;
import com.nmt.service.INewService;
import com.nmt.util.UploadFileUtil;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewsRepository newRepository;
	@Autowired
	private NewsConverter newsConverter;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> result = new ArrayList<>();
		List<NewsEntity> newsList = newRepository.findAll(pageable).getContent();
		for (NewsEntity newsEntity : newsList) {
			if(newsEntity != null) {
				NewDTO newDto = newsConverter.toDTO(newsEntity);
				result.add(newDto);
			}
		}
		return result;
	}

	@Override
	public NewDTO findOneById(Long id) {
		NewsEntity 	newsEntity = newRepository.findOne(id);
		return newsConverter.toDTO(newsEntity);
	}

	@Override
	public NewDTO save(NewDTO newDTO) {
		if(newDTO.getId() == null) {
			NewsEntity newsEntity = newsConverter.toEntity(newDTO);
			NewsEntity newEntity = newRepository.save(newsEntity);
			return newsConverter.toDTO(newEntity);
		}else {
			NewsEntity oldEntity = newRepository.findOne(newDTO.getId());
			oldEntity = newsConverter.toUpdateEntity(newDTO, oldEntity);
			oldEntity = newRepository.save(oldEntity);
			return newsConverter.toDTO(oldEntity);
		}
		
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			newRepository.delete(id);
		}
		
	}

	@Override
	public int totalItem() {
		return (int)newRepository.count();
	}

	@Override
	public String upLoad(UploadFileDTO uploadFileDTO) {
		
		/*
		 * byte[] deCodeBase64 =
		 * Base64.getDecoder().decode(uploadFileDTO.getBase64().getBytes()); String
		 * urlThumNail =
		 * uploadFileUtil.writeOrUpdate(deCodeBase64,"/thumbnail/"+uploadFileDTO.
		 * getNameFile()); return urlThumNail;
		 */
		return null;
	}

	@Override
	public InputStream getPhotoThumbnail(Long id) {
		String thumbnail = findOneById(id).getThumbnail();
		byte[] decodeBase64 = Base64.getDecoder().decode(thumbnail.getBytes());
		InputStream inputStream = new ByteArrayInputStream(decodeBase64);
		return inputStream;
		
	}



}
