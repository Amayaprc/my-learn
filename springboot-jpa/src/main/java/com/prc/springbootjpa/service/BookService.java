package com.prc.springbootjpa.service;

import com.prc.springbootjpa.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBook();

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Integer id);

    Book findBookById(Integer id);

    Page<Book> findBookPage(Pageable pageable);

    Book getMaxBook();

    List<Book> findAllBook1();

    List<Book> findAllBook2();

    void addBook1(Book book);

    void addBook2(Book book);
}
