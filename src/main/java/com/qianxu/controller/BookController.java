package com.qianxu.controller;

import com.qianxu.pojo.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 2018/7/11.
 */
@RestController
public class BookController {

    @RequestMapping(value = "/books" ,produces="text/plain;charset=UTF-8")
    public ModelAndView books() throws UnsupportedEncodingException {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        String a="�޹���";
        b1.setAuthor("�޹���");
        b1.setName("��������");
        Book b2 = new Book();
        b2.setAuthor("��ѩ��");
        b2.setName(    URLDecoder.decode("��¥��", "GBK"));

        books.add(b1);
        books.add(b2);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    @RequestMapping("/book")
    public com.qianxu.pojo.Book book() {
        Book book = new Book();
        book.setAuthor("�޹���");
        book.setName("��������");
        book.setPrice(30f);
        book.setPublicationDate(new Date());

        return book;
    }
}
