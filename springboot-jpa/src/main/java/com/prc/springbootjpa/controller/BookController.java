package com.prc.springbootjpa.controller;

import com.prc.springbootjpa.pojo.Book;
import com.prc.springbootjpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("findAllBook")
    public List<Book> findAllBook(){
        return bookService.findAllBook();
    }

    @RequestMapping("addBook")
    public void addBook(Book book){
        bookService.addBook(book);
    }

    @RequestMapping("updateBook")
    public void updateBook(Book book){
        bookService.updateBook(book);
    }

    @RequestMapping("deleteBook")
    public void deleteBook(Integer id){
        bookService.deleteBook(id);
    }

    @RequestMapping("findBookById")
    public Book findBookById(Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("findBookPage")
    public Page<Book> findBookPage(Pageable pageable){
        return bookService.findBookPage(pageable);
    }


    @RequestMapping("getMaxBook")
    public Book getMaxBook(){
        return bookService.getMaxBook();
    }

    //配置多数据源后的测试
    //查询
    @RequestMapping("selectAll1")
    public List<Book> findAllBook1(){
        return bookService.findAllBook1();
    }

    @RequestMapping("selectAll2")
    public List<Book> findAllBook2(){
        return bookService.findAllBook2();
    }

    //新增
    @RequestMapping("addBook1")
    public void addBook1(Book book){
        bookService.addBook1(book);
    }

    @RequestMapping("addBook2")
    public void addBook2(Book book){
        bookService.addBook2(book);
    }
}
