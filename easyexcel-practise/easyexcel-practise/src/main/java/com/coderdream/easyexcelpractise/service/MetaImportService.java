package com.coderdream.easyexcelpractise.service;

import java.util.List;

public interface MetaImportService {

    /**
     * 处理文件
     *
     * @param fileName 文件名
     * @param sheetName Excel文件Sheet名称
     * @param objectTypeCode 对象类型编码
     */
    void processFile(String fileName, String sheetName, String objectTypeCode);

    void processFileForFolder(String path, List<String> sheetNameList);

}
