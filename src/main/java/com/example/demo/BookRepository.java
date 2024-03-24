package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 根据书名、作者或编号进行模糊搜索
    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1% OR b.author LIKE %?1% OR b.isbn LIKE %?1%")
    List<Book> findByTitleAuthorOrIsbnContaining(String keyword);
}
