package com.prc.springbootrest.dao;

import com.prc.springbootrest.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * exported : 是否当成接口暴露出去
 * path : 访问路径名
 * collectionResourceRel : 前端展示的集合名
 * itemResourceRel : 集合中元素的名字
 */
@RepositoryRestResource(path = "bs",collectionResourceRel = "bs",itemResourceRel = "b",exported = true)
public interface BookDao extends JpaRepository<Book,Integer> {

    /**
     * exported : 是否当成接口暴露出去
     * path : 访问路径名
     * rel : 前端展示的接口名
     */
    @RestResource(path = "findByName",rel = "findByName",exported = true)
    List<Book> findAllByBookNameContaining(@Param("bookName") String bookName);
}
