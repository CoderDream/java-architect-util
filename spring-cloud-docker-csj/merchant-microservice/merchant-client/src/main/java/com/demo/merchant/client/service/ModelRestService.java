package com.demo.merchant.client.service;

import com.demo.merchant.object.ModelQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModelRestService {
	 @Autowired
	 private ModelClient modelClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return modelClient.findById(id);
	}

	private String findByIdFallback(Long id){
		ModelQo modelQo = new ModelQo();
		return new Gson().toJson(modelQo);
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return modelClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		ModelQo modelQo = new ModelQo();

		return new Gson().toJson(modelQo);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return modelClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return modelClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
		  public String create(ModelQo modelQo){
		return modelClient.create(modelQo);
	}

	private String createFallback(ModelQo modelQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(ModelQo modelQo){
		return modelClient.update(modelQo);
	}

	private String updateFallback(ModelQo modelQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return modelClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
