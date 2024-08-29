package com.coderdream.freeapps.util.callapi;

//import ch.qos.logback.core.util.CloseUtil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.coderdream.freeapps.util.other.BeanMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;

@Slf4j
public class HttpUtil {


    /**
     * get请求
     *
     * @param param
     * @param head
     * @param url
     * @param retryTimes
     * @return
     */
    public static List<String> getText(Map<String, Object> param, Map<String, String> head, String url,
        Integer retryTimes) {
        List<String> result = new ArrayList<>();
        HttpRequest request = HttpRequest.get(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = form(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(5000).execute();
            if (response != null && response.isOk()) {
                String body = response.body();
                body = body.replaceAll("\r", " ");
//                System.out.println("XXXXXXXXXXX" + body);
                result.addAll(Arrays.asList(body.split("\n")));
                return result;
//                return JSON.parseObject(body, t);
            } else {
                throw new RuntimeException("返回状态:" + response.getStatus());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            if (retryTimes < 0) {
                log.error("调用远程服务失败:" + url);
                throw new RuntimeException("调用远程服务失败");
            } else {
                return null;
//                return get(param, head, url, t, --retryTimes);
            }
        } finally {
            if (response != null) {
                //关闭对应的流
                response.close();
            }
        }

//        return null;
    }


    /**
     * get请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T get(Map<String, Object> param, Map<String, String> head, String url, Class<T> t,
        Integer retryTimes) {
        HttpRequest request = HttpRequest.get(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = form(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(5000).execute();
            if (response != null && response.isOk()) {
                String body = response.body();
                System.out.println("#####  " + body);
                return JSON.parseObject(body, t);
            } else {
                throw new RuntimeException("返回状态:" + response.getStatus());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            if (retryTimes < 0) {
                log.error("调用远程服务失败:" + url);
                throw new RuntimeException("调用远程服务失败");
            } else {
                return get(param, head, url, t, --retryTimes);
            }
        } finally {
            if (response != null) {
                //关闭对应的流
                response.close();
            }
        }
    }

    /**
     * get请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T get(Map<String, Object> param, Map<String, String> head, String url, Class<T> t) {
        HttpRequest request = HttpRequest.get(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = form(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(15000).execute();
            if (response != null && response.isOk()) {
                String body = response.body();
                return JSON.parseObject(body, t);
            } else {
                throw new RuntimeException("返回状态:" + response.getStatus());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("调用远程服务失败");
        } finally {
            if (response != null) {
                //关闭对应的流
                response.close();
            }
        }
    }

    /**
     * get请求
     *
     * @param s
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T get(S s, Map<String, String> head, String url, Class<T> t) {
        Map<String, Object> param = BeanMapper.map(s, Map.class);
        return get(param, head, url, t);
    }

    /**
     * post请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T post(Map<String, Object> param, Map<String, String> head, String url,
        Class<T> t) {
        HttpRequest request = HttpRequest.post(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = body(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(9000).execute();
            if (response != null && response.getStatus() == 200) {
                String body = response.body();
                return JSON.parseObject(body, t);
            } else {
                throw new RuntimeException("返回状态:" + response.getStatus());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            //关闭对应的流
//            CloseUtil.closeQuietly(response);
        }
    }

    /**
     * post请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T postWithJson(Map<String, Object> param, Map<String, String> head,
        String url, Class<T> t) {
        head.put("Content-Type", ContentType.JSON.getValue());
        HttpRequest request = HttpRequest.post(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = body(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(6000).execute();
//            response = request.charset("utf-8").timeout(6000).execute();
            if (response != null && response.getStatus() == 200) {
                String body = response.body();
                return JSON.parseObject(body, t);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用远程服务失败");
        } finally {
            //关闭对应的流
//            CloseUtil.closeQuietly(response);
        }
        return null;
    }

    /**
     * post请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T postWithForm(Map<String, Object> param, Map<String, String> head,
        String url, Class<T> t) {
        head.put("Content-Type", ContentType.FORM_URLENCODED.getValue());
        HttpRequest request = HttpRequest.post(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = form(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(6000).execute();
            if (response != null && response.getStatus() == 200) {
                String body = response.body();
                return JSON.parseObject(body, t);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用远程服务失败");
        } finally {
            //关闭对应的流
//            CloseUtil.closeQuietly(response);
        }
        return null;
    }

    /**
     * post请求
     *
     * @param param
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T postWithForm(Map<String, Object> param, Map<String, String> head, String url, Class<T> t,
        Integer retryTimes) {
//        head.put("Content-Type", ContentType.FORM_URLENCODED.getValue());
        HttpRequest request = HttpRequest.post(url);
        //设置头参数
        request = header(request, head);
        //设置请求参数
        request = form(request, param);
        HttpResponse response = null;
        try {
            response = request.charset("utf-8").timeout(6000).execute();
            if (response != null && response.getStatus() == 200) {
                String body = response.body();
                return JSON.parseObject(body, t);
            }
            if (retryTimes < 0) {
                throw new RuntimeException("调用远程服务失败");
            } else {
                return postWithForm(param, head, url, t, --retryTimes);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            if (retryTimes < 0) {
                throw new RuntimeException("调用远程服务失败");
            } else {
                return postWithForm(param, head, url, t, --retryTimes);
            }
        } finally {
            //关闭对应的流
//            CloseUtil.closeQuietly(response);
        }
    }

//    public static void testHutoolPost(String cameraId) {
//        JSONObject jsonObject = JSONUtil.createObj();
//        jsonObject.put("cameraId", cameraId);
//        jsonObject.put("startTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        jsonObject.put("callback", "http://www.baidu.com");
//        String postResult = HttpRequest
//            .post("http://localhost:8080/v1/platedetect/tasks")
//            .header("Content-Type","application/json")
//            .body(jsonObject)
//            .execute()
//            .body();
//        log.info("postResult:"+postResult);
//    }


    /**
     * post请求
     *
     * @param s
     * @param head
     * @param url
     * @param t
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T post(S s, Map<String, String> head, String url, Class<T> t) {
        Map<String, Object> param = BeanMapper.map(s, Map.class);
        return post(param, head, url, t);
    }

    /**
     * 构建头参数
     *
     * @param request
     * @param head
     * @return
     */
    private static HttpRequest header(HttpRequest request, Map<String, String> head) {
        if (CollectionUtil.isNotEmpty(head)) {
            for (Map.Entry<String, String> entry : head.entrySet()) {
                if (StrUtil.isNotBlank(entry.getValue())) {
                    request = request.header(entry.getKey(), entry.getValue());
                }
            }
        }
        return request;
    }

    /**
     * post方法的请求参数
     *
     * @param request
     * @param param
     * @return
     */
    private static HttpRequest body(HttpRequest request, Map<String, Object> param) {
        if (CollectionUtil.isNotEmpty(param)) {
            request = request.body(JSON.toJSONString(param));
        }
        return request;
    }

    /**
     * get方法请求参数
     *
     * @param request
     * @param param
     * @return
     */
    private static HttpRequest form(HttpRequest request, Map<String, Object> param) {
        if (CollectionUtil.isNotEmpty(param)) {
            request = request.form(param);
        }
        return request;
    }
}
