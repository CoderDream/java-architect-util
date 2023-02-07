package com.coderdream.easyexcelpractise.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    /**
     *
     * @param path
     * @return
     */
    public static List<String> fileTraversalNotRecursion(String path) {//非递归
        List<String> names = new ArrayList<>();
        File file = new File(path);
        if (null != file) {
            if (file.exists()) {
                LinkedList<String> list = new LinkedList<>();//利用 LinkedList 的属性,链表结构
                File[] files = file.listFiles();
                if (null == files || 0 == files.length) {
                    System.out.println("该文件夹下面没有文件");
                } else {
                    for (int i = 0, size = files.length; i < size; i++) {
                        File temp = files[i];
                        if (temp.isFile()) {
                            names.add(temp.toString());
                        } else {
                            list.add(temp.getAbsolutePath());
                        }
                    }

                    //遍历文件夹下面的所有文件
                    while (!list.isEmpty()) {
                        String tempPath = list.removeFirst();
                        File temp = new File(tempPath);
                        File[] tf = temp.listFiles();
                        for (File ff : tf) {
                            if (ff.isFile()) {
                                names.add(ff.toString());
                            } else {
                                list.add(ff.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }
        return names;
    }
}
