package com.nmt.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nmt.dto.NewDTO;
import com.nmt.dto.UploadFileDTO;

public interface INewService {
	
	List<NewDTO> findAll(Pageable pageable);
	int totalItem();
	NewDTO findOneById(Long id);
	NewDTO save(NewDTO newDTO);
	void delete(Long ids[]);
	String upLoad(UploadFileDTO upLoadDto);
	InputStream getPhotoThumbnail(Long id);
}
