package com.feng.uploadUtils;

import com.feng.bean.PhotoSeaMainModel;
import com.feng.bean.XfxUserbaseModel;
import com.feng.ossUtils.OssUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UploadImageUtil
 * @Description TODO
 * @Author admin
 * @Date 2021/6/15 15:38
 * @Version 1.0
 */
public class UploadImageUtil {

    /**
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 上传生成的海报 上传到云服务器 并返回给前端 url
     * @Date 16:54 2021/4/1
     * @Param [inputStream, shareCategory, request]
     **/
    public static Map<String, Object> uploadShareSeaImg(InputStream inputStream, String shareCategory) {
        // 获取用户的目的是为了 1、文件新名称，2、将海报添加到数据库做记录
        // XfxUserbaseModel user = upmsSessionDao.getUser(request); // 通过 request 获取当前用户信息。
        XfxUserbaseModel user = new XfxUserbaseModel(); // 就是一个用户实体类
        user.setId(1);
        // 新名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + ".png";
        String key = "wx/" + user.getId() + "/" + shareCategory + "/" + newFileName;
        String ok = OssUtil.uploadImg(key, inputStream);
        if (!"OK".equals(ok)) {
            System.out.println("UploadImageUtil oss uploadShareSeaImg fail:" + user.getId());
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("ossKey", key);
        map.put("fileUrl", OssUtil.getImgUrl(key));
        // 将做好的海报URL oss key 放到 数据库
        PhotoSeaMainModel model = new PhotoSeaMainModel();
        model.setUserId(user.getId());
        model.setTitle(shareCategory);
        model.setFileOss(key);
        // photoSeaService.addPhotoSeaModel(model);
        return map;
    }
}
