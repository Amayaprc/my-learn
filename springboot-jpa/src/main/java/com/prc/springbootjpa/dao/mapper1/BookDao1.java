package com.prc.springbootjpa.dao.mapper1;

import com.prc.springbootjpa.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookDao1 extends JpaRepository<Book,Integer> {

    Book findBookById(Integer id);

    @Query(value = "SELECT * FROM book WHERE id = (select max(id) from book)",nativeQuery = true)
    Book getMaxBook();

    @Query(value = "INSERT INTO book(name,author) values (?1,?2)",nativeQuery = true)
    @Modifying
    @Transactional
    Integer addBook1(String name,String author);

    @Query(value = "INSERT INTO book(name,author) values (:name ,:author)",nativeQuery = true)
    @Modifying
    @Transactional
    Integer addBook2(@Param("name") String name,@Param("author") String author);
}
