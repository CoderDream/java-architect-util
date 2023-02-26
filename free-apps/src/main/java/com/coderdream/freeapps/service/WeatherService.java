package com.coderdream.freeapps.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.coderdream.freeapps.dto.WeatherInfo;
import com.coderdream.freeapps.dto.constant.WeatherConstant;
import com.coderdream.freeapps.util.DateUtils;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 根据城市获取当天气温信息.
 *
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 3:25 下午
 */
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherInfo getWeather(WeatherConstant weatherConstant) {
        String json = restTemplate
                .getForObject("http://t.weather.sojson.com/api/weather/city/" + weatherConstant.getCode()
                        , String.class);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer status = jsonObject.getInteger("status");

        String currentDay = DateUtils.getDay(LocalDateTime.now());

        if (status == 200) {
            JSONObject data = jsonObject.getJSONObject("data");
            String quality = data.getString("quality");
            String notice = data.getString("ganmao");
            String currentTemperature = data.getString("wendu");

            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject dayInfo = forecast.getJSONObject(0);
            String high = dayInfo.getString("high");
            String low = dayInfo.getString("low");
            String weather = dayInfo.getString("type");
            String windDirection = dayInfo.getString("fx");

            return WeatherInfo.builder().airQuality(quality + "污染").date(currentDay)
                    .cityName(weatherConstant.getCityName()).temperature(low + "-" + high).weather(weather)
                    .windDirection(windDirection).notice(notice).currentTemperature(currentTemperature)
                    .build();
        }
        return null;
    }

}
