package com.demo.catalog.client.service;

import com.demo.catalog.object.SubsortsQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubsortsRestService {
	@Autowired
	private SubsortsClient subsortsClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id) {
		return subsortsClient.findById(id);
	}

	private String findByIdFallback(Long id){
		SubsortsQo subsortsQo = new SubsortsQo();
		return new Gson().toJson(subsortsQo);
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return subsortsClient.findByName(name);
	}

	private String findByNameFallback(String name){
		SubsortsQo subsortsQo = new SubsortsQo();
		return new Gson().toJson(subsortsQo);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findAll() {
		return subsortsClient.findAll();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findAll(Integer index, Integer size, String name) {
		return subsortsClient.findAll(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(SubsortsQo subsortsQo){
		return subsortsClient.create(subsortsQo);
	}

	private String createFallback(SubsortsQo subsortsQo){
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(SubsortsQo subsortsQo){
		return subsortsClient.update(subsortsQo);
	}

	private String updateFallback(SubsortsQo subsortsQo){
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return subsortsClient.delete(id);
	}

	private String deleteFallback(Long id){
		return "-1";
	}
}
