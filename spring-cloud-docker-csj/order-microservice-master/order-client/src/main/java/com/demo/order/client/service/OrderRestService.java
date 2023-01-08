package com.demo.order.client.service;

import com.demo.order.object.OrderQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderRestService {
	 @Autowired
	 private OrderClient orderClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return orderClient.findById(id);
	}

	private String findByIdFallback(Long id){
		OrderQo orderQo = new OrderQo();
		return new Gson().toJson(orderQo);
	}

	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(OrderQo orderQo){
		String date = null;
		if(orderQo.getCreated() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.format(orderQo.getCreated());
		}
		return orderClient.findPage(orderQo.getPage(), orderQo.getSize(), orderQo.getUserid(),
				orderQo.getMerchantid(), orderQo.getStatus(), date);
	}

	private String findPageFallback(OrderQo orderQo){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(OrderQo orderQo){
		return orderClient.create(orderQo);
	}

	private String createFallback(OrderQo orderQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(OrderQo orderQo){
		return orderClient.update(orderQo);
	}

	private String updateFallback(OrderQo orderQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return orderClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
