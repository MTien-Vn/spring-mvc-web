package com.nmt.api.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nmt.dto.NewDTO;
import com.nmt.service.impl.NewService;

@RestController(value = "newsApiWeb")
public class NewsAPI {
	@Autowired
	private NewService newService;
	
	@GetMapping(value = "/web/api/news")
	public NewDTO getNewById(@RequestBody Long id) {
		return newService.findOneById(id);
	}
	@GetMapping(value = "/web/api/news/thumbnail")
	public  void getThumbNail(HttpServletResponse response, @PathVariable("id") Long id) {
		response.setContentType("image/jpeg");
		InputStream inputStream = newService.getPhotoThumbnail(id);
		try {
			IOUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
