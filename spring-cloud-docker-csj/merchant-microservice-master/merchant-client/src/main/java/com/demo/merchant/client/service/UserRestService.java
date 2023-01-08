package com.demo.merchant.client.service;

import com.demo.merchant.object.UserQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRestService {
	 @Autowired
	 private UserClient userClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return userClient.findById(id);
	}

	private String findByIdFallback(Long id){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return userClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		return null;
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return userClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(UserQo userQo){
		Long merchantId = null;
		if(userQo.getMerchant() != null) merchantId = userQo.getMerchant().getId();
		return userClient.findPage(userQo.getPage(), userQo.getSize(),
				userQo.getName(), merchantId);
	}

	private String findPageFallback(UserQo userQo){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(UserQo userQo){
		return userClient.create(userQo);
	}

	private String createFallback(UserQo userQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(UserQo userQo){
		return userClient.update(userQo);
	}

	private String updateFallback(UserQo userQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return userClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
