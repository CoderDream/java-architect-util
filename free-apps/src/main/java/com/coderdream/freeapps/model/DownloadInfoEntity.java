package com.coderdream.freeapps.model;


import java.io.Serializable;
import lombok.Data;

/**
 * @author CoderDream
 */
@Data
public class DownloadInfoEntity implements Serializable {

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件存储文件夹
     */
    private String path;

    /**
     * 文件名称
     */
    private String fileName;

}
