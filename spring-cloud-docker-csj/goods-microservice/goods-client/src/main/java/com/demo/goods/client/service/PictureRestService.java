package com.demo.goods.client.service;


import com.demo.goods.object.PictureQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureRestService {
	 @Autowired
	 private PictureClient pictureClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return pictureClient.findById(id);
	}

	private String findByIdFallback(Long id){
		PictureQo pictureQo = new PictureQo();
		return new Gson().toJson(pictureQo);
	}


	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(PictureQo pictureQo){
		String date = null;
		if(pictureQo.getCreated() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.format(pictureQo.getCreated());
		}
		return pictureClient.findPage(pictureQo.getPage(), pictureQo.getSize(), pictureQo.getMerchantid(), date);
	}

	private String findPageFallback(PictureQo pictureQo){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(PictureQo pictureQo){
		return pictureClient.create(pictureQo);
	}

	private String createFallback(PictureQo pictureQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(PictureQo pictureQo){
		return pictureClient.update(pictureQo);
	}

	private String updateFallback(PictureQo pictureQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return pictureClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteByFileNameFallback")
	public String deleteByFileName(String fileName){
		return pictureClient.deleteByFileName(fileName);
	}

	private String deleteByFileNameFallback(String fileName) {
		return "-1";
	}
}
