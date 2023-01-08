package com.demo.merchant.client.service;

import com.demo.merchant.object.RoleQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleRestService {
	 @Autowired
	 private RoleClient roleClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return roleClient.findById(id);
	}

	private String findByIdFallback(Long id){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return roleClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		return null;
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return roleClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return roleClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
		  public String create(RoleQo roleQo){
		return roleClient.create(roleQo);
	}

	private String createFallback(RoleQo roleQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(RoleQo roleQo){
		return roleClient.update(roleQo);
	}

	private String updateFallback(RoleQo roleQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return roleClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
