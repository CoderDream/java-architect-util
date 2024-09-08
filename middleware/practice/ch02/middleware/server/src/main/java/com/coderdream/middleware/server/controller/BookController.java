package com.coderdream.middleware.server.controller;
/**
 * Created by Administrator on 2019/3/3.
 */

import com.coderdream.middleware.server.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/3 17:34
 **/
@RestController
@Api(tags = "图书管理")
@RequestMapping("/book")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    /**
     * 获取书籍对象信息
     */
    @ApiImplicitParams({@ApiImplicitParam(name = "bookNo", value = "书籍编号", required = true),
        @ApiImplicitParam(name = "bookName", value = "书籍名称", required = true)})
    @ApiOperation("获取书籍对象信息")
    @GetMapping(value = "info")
    public Book info(Integer e, Integer f, Integer bookNo, String bookName) {
        Book book = new Book();
        book.setBookNo(bookNo);
        book.setName(bookName);
        return book;
    }

    /**
     * 获取书籍对象信息
     */
    @ApiOperation("获取书籍对象信息2")
    @GetMapping(value = "info2")
    public Book info(Book book) {
        Book newBook = new Book();
        newBook.setBookNo(book.getBookNo());
        newBook.setName(book.getName());
        return newBook;
    }

}






















