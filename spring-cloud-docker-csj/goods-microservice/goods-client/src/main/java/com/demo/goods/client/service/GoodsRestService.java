package com.demo.goods.client.service;


import com.demo.goods.object.GoodsQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodsRestService {
	 @Autowired
	 private GoodsClient goodsClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(Long id){
		return goodsClient.findById(id);
	}

	private String findByIdFallback(Long id){
		GoodsQo goodsQo = new GoodsQo();
		return new Gson().toJson(goodsQo);
	}


	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(GoodsQo goodsQo){
		String date = null;
		if(goodsQo.getCreated() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.format(goodsQo.getCreated());
		}
		return goodsClient.findPage(goodsQo.getPage(), goodsQo.getSize(), goodsQo.getMerchantid(),
				goodsQo.getName(), goodsQo.getSortsid(), goodsQo.getSubsid(), date);
	}

	private String findPageFallback(GoodsQo goodsQo){
		Map<String, Object> page = new HashMap<>();
		page.put("content", null);
		page.put("totalPages", 0);
		page.put("totalelements", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(GoodsQo goodsQo){
		return goodsClient.create(goodsQo);
	}

	private String createFallback(GoodsQo goodsQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(GoodsQo goodsQo){
		return goodsClient.update(goodsQo);
	}

	private String updateFallback(GoodsQo goodsQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(Long id){
		return goodsClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
