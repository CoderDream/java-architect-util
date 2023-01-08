package com.demo.merchant.client.service;

import com.demo.merchant.object.ResourceQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResourceRestService {
	 @Autowired
	 private ResourceClient resourceClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return resourceClient.findById(id);
	}

	private String findByIdFallback(Long id){
		ResourceQo resourceQo = new ResourceQo();
		return new Gson().toJson(resourceQo);
	}

	@HystrixCommand(fallbackMethod = "findByNameFallback")
	public String findByName(String name) {
		return resourceClient.findByName(name);
	}

	private String findByNameFallback(String name) {
		ResourceQo resourceQo = new ResourceQo();

		return new Gson().toJson(resourceQo);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback")
	public String  findList(){
		return resourceClient.findList();
	}

	private String findAllFallback(){
		return null;
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(Integer index, Integer size, String name){
		return resourceClient.findPage(index, size, name);
	}

	private String findPageFallback(Integer index, Integer size, String name){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
		  public String create(ResourceQo resourceQo){
		return resourceClient.create(resourceQo);
	}

	private String createFallback(ResourceQo resourceQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(ResourceQo resourceQo){
		return resourceClient.update(resourceQo);
	}

	private String updateFallback(ResourceQo resourceQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return resourceClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
