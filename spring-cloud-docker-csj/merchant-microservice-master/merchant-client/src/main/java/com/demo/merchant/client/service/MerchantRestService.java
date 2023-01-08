package com.demo.merchant.client.service;

import com.demo.merchant.object.MerchantQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MerchantRestService {
	 @Autowired
	 private MerchantClient merchantClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return merchantClient.findById(id);
	}

	private String findByIdFallback(Long id){
		MerchantQo merchantQo = new MerchantQo();
		return new Gson().toJson(merchantQo);
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return merchantClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		MerchantQo merchantQo = new MerchantQo();

		return new Gson().toJson(merchantQo);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return merchantClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return merchantClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
		  public String create(MerchantQo merchantQo){
		return merchantClient.create(merchantQo);
	}

	private String createFallback(MerchantQo merchantQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(MerchantQo merchantQo){
		return merchantClient.update(merchantQo);
	}

	private String updateFallback(MerchantQo merchantQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return merchantClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
