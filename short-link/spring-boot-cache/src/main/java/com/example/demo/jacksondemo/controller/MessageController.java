package com.example.demo.jacksondemo.controller;

import java.util.List;

import com.example.demo.jacksondemo.data.Message;
import com.example.demo.jacksondemo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import com.example.demo.jacksondemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    //定义了API的视图类型。也就是说，这个API在序列化的时候，仅仅对视图为View.Summary.class的数据进行序列化。
    // 其中View.Summary.class的定义在view文件夹下面；数据的视图定义在DO文件夹下。
    @JsonView(View.Summary.class)
    @RequestMapping("/")
    public List<Message> getAllMessages() {
        return messageService.getAll();
    }

    //同上，此函数使用View.SummaryWithRecipients.class视图。调用了和上面的API相同的接口messageService.getAll()。但是却输出了不同的序列化结果。
    @JsonView(View.SummaryWithRecipients.class)
    @RequestMapping("/with-recipients")
    public List<Message> getAllMessagesWithRecipients() {
        return messageService.getAll();
    }

    @RequestMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        return this.messageService.get(id);
    }


    @RequestMapping(value = "/save")
    public String save(@RequestParam String file) {
        try {
            this.messageService.saveIntoFile(file);
        } catch (Exception e) {
            return "fail to save";
        }
        return "save successfully";
    }

    @RequestMapping(value = "/load")
    public String load(@RequestParam String file) {
        try {
            this.messageService.loadFile(file);
        } catch (Exception e) {
            return "fail to load";
        }
        return "load successfully";
    }
}