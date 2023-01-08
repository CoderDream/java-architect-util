package com.demo.merchant.client.service;

import com.demo.merchant.object.KindQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KindRestService {
	 @Autowired
	 private KindClient kindClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return kindClient.findById(id);
	}

	private String findByIdFallback(Long id){
		KindQo kindQo = new KindQo();
		return new Gson().toJson(kindQo);
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return kindClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		KindQo kindQo = new KindQo();

		return new Gson().toJson(kindQo);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return kindClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return kindClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
		  public String create(KindQo kindQo){
		return kindClient.create(kindQo);
	}

	private String createFallback(KindQo kindQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(KindQo kindQo){
		return kindClient.update(kindQo);
	}

	private String updateFallback(KindQo kindQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return kindClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
