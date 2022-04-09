package com.example.demo.jacksondemo.data;

import java.util.Arrays;
import java.util.List;

import com.example.demo.jacksondemo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

public class Message {

    @JsonView(View.Summary.class)//这句话指定了id的视图类型是View.Summary.class。Controller的可以根据视图类来指定输出何种视图。View.Summary.class的定义在view文件夹里。
    private Long id;

    @JsonView(View.Summary.class)
    private String title;

    @JsonView(View.Summary.class)
    private User author;

    @JsonView(View.SummaryWithRecipients.class)//这句话指定了recipients的视图类型是View.SummaryWithRecipients.class
    private List<User> recipients;

    private String body;

    //default constructor用于让Jackson反序列化
    Message() {
    }

    public Message(Long id, String title, String body, User author, User... recipients) {
        this();
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.recipients = Arrays.asList(recipients);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }
}
