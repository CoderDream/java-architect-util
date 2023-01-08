package com.demo.catalog.client.service;


import com.demo.catalog.object.SortsQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SortsRestService {
	 @Autowired
	 private SortsClient sortsClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return sortsClient.findById(id);
	}

	private String findByIdFallback(Long id){
		SortsQo sortsQo = new SortsQo();
		return new Gson().toJson(sortsQo);
	}


	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return sortsClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "findListFallback")
	public String  findList() {
		return sortsClient.findList();
	}

	private String findListFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(SortsQo sortsQo){
		return sortsClient.create(sortsQo);
	}

	private String createFallback(SortsQo sortsQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(SortsQo sortsQo){
		return sortsClient.update(sortsQo);
	}

	private String updateFallback(SortsQo sortsQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return sortsClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
