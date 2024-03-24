package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081") // 允许来自http://localhost:8081的跨域请求
@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 根据书名、作者或编号进行搜索
    @GetMapping("/search")
    public List<Book> searchByTitleAuthorOrIsbn(@RequestParam("query") String query) {
        logger.info("执行搜索，查询参数：{}", query);
        List<Book> books = bookRepository.findByTitleAuthorOrIsbnContaining("%" + query + "%");
        if (books.isEmpty()) {
            logger.info("未找到匹配的书籍");
        } else {
            logger.info("找到{}本匹配的书籍", books.size());
        }
        return books;
    }
}

