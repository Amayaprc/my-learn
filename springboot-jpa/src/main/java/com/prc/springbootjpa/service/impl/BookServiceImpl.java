package com.prc.springbootjpa.service.impl;

import com.prc.springbootjpa.dao.mapper1.BookDao1;
import com.prc.springbootjpa.dao.mapper2.BookDao2;
import com.prc.springbootjpa.pojo.Book;
import com.prc.springbootjpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao1 bookDao1;

    @Autowired
    BookDao2 bookDao2;

    @Override
    public List<Book> findAllBook() {
        return bookDao2.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookDao2.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao2.saveAndFlush(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao2.deleteById(id);
    }

    @Override
    public Book findBookById(Integer id) {
        Book book = bookDao2.findBookById(id);
        return book;
    }

    @Override
    public Page<Book> findBookPage(Pageable pageable) {
        return bookDao2.findAll(pageable);
    }

    @Override
    public Book getMaxBook() {
        return bookDao2.getMaxBook();
    }


    //配置多数据源后的测试
    @Override
    public List<Book> findAllBook1() {
        return bookDao1.findAll();
    }

    @Override
    public List<Book> findAllBook2() {
        return bookDao2.findAll();
    }

    @Override
    public void addBook1(Book book) {
        bookDao1.save(book);
    }

    @Override
    public void addBook2(Book book) {
        bookDao2.save(book);
    }
}
