package com.coderdream;

import lombok.Data;

import java.util.List;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/3 16:32
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@Data
public class JavaMailSenderInfo {

    // 收件人地址
    private String toAddress;

    // 邮件主题
    private String subject;

    // 邮件内容
    private String content;

    // 是否是html 注意字段boolean类型 不要用is开头 idea会生成get,set方法会进行优化 isHtml -> isHtml(),getHtml() -- html -> isHtml(),getHtml()
    private boolean html = false;

    // 图片地址集合
    private List<String> photoList;

    // 附件地址集合
    private List<String> attachList;

}