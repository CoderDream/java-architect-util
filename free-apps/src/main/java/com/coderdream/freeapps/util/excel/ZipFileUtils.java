package com.coderdream.freeapps.util.excel;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ZipUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 (zip压缩包/文件) 操作工具类
 *
 * @author liukai
 * @date 2022/7/14
 */
@Slf4j
@SuppressWarnings("all")
public class ZipFileUtils {


    /**
     * 生成Zip压缩包 (注意是对hutool的二次封装,所以必须要有hutool依赖)
     *
     * @param targetZipFile 要生成的目标zip压缩包
     * @param sourceFiles   压缩包中包含的文件集合
     * @param dirWithFlag   是否将文件目录一同打包进去 (true:压缩包中包含文件目录,false:压缩包中不包含目录)
     * @author liukai
     */
    public static void generateZip(File targetZipFile, List<File> sourceFiles, boolean dirWithFlag) {
        if (CollUtil.isNotEmpty(sourceFiles)) {
            File[] fileArr = sourceFiles.toArray(new File[]{});
            ZipUtil.zip(targetZipFile, dirWithFlag, fileArr);
        }
    }

    /**
     * 下载ZIP压缩包(会对下载后的压缩包进行删除)
     *
     * @param file     zip压缩包文件
     * @param response 响应
     * @author liukai
     */
    public static void downloadZip(File file, HttpServletResponse response) {
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            toClient.write(buffer);
            toClient.flush();
        } catch (Exception e) {
            log.error("下载zip压缩包过程发生异常:", e);
        } finally {
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    log.error("zip包下载关流失败:", e);
                }
            }
            //删除改临时zip包(此zip包任何时候都不需要保留,因为源文件随时可以再次进行压缩生成zip包)
            file.delete();
        }
    }

    /**
     * 任何单文件下载
     *
     * @param file     要下载的文件
     * @param response 响应
     * @author liukai
     */
    public static void downloadAnyFile(File file, HttpServletResponse response) {
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            // 清空response
            response.reset();
            //防止文件名中文乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(),"UTF-8"));
            //根据文件动态setContentType
            response.setContentType(new MimetypesFileTypeMap().getContentType(file) + ";charset=UTF-8");
            outputStream = response.getOutputStream();
            byte[] bytes = new byte[2048];
            int len;
            while ((len = fileInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

