package com.example.demo;

import com.example.demo.Book;
import com.example.demo.User;
import com.example.demo.BookRepository;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // 初始化书籍数据
        bookRepository.save(new Book("我的世界", "张三", "001", 1));
        bookRepository.save(new Book("你的世界", "李四", "002", 1));
        bookRepository.save(new Book("他的世界", "王五", "003", 1));

        // 初始化用户数据
        userRepository.save(new User("A", "A"));
        userRepository.save(new User("B", "B"));
        userRepository.save(new User("C", "C"));
    }
}
