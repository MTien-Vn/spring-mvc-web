package com.nmt.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nmt.dto.NewDTO;
import com.nmt.dto.UploadFileDTO;
import com.nmt.service.impl.NewService;

@RestController(value = "newsApiAdmin")
public class NewsAPI {
	@Autowired
	private NewService newsService;
	
	
	@PostMapping(value= "/admin/api/news")
	public NewDTO createNews(@RequestBody NewDTO newDTO) {
		return newsService.save(newDTO);
	}
	
	@PutMapping(value= "/admin/api/news")
	public NewDTO updateNews(@RequestBody NewDTO newDTO) {
		return newsService.save(newDTO);
	}
	
	@DeleteMapping(value = "/admin/api/news")
	public void deleteNews(@RequestBody Long[] ids) {
		newsService.delete(ids);
	}
	
	@PostMapping(value = "/admin/api/news/files")
	public String upLoad(@RequestBody UploadFileDTO uploadFileDTO) {
		return newsService.upLoad(uploadFileDTO);
	}
	

}
