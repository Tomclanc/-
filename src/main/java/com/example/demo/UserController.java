package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;


@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            log.info("尝试注册的用户名已存在：{}", newUser.getUsername());
            return ResponseEntity.badRequest().body("用户名已存在");
        }
        userRepository.save(newUser);
        log.info("新用户注册成功：{}", newUser.getUsername());

        List<String> allUsernames = userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        log.info("当前所有用户的用户名：{}", allUsernames);

        return ResponseEntity.ok("用户注册成功");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {

            Map<String, Object> response = new HashMap<>();
            response.put("message", "登录成功");
            response.put("username", user.getUsername()); // 确保返回username字段
            log.info("用户登录成功：{}", user.getUsername());
            log.info("登录响应体: {}", response);
            return ResponseEntity.ok().body(response);
        } else {
            log.info("登录失败，用户名或密码错误：{}", loginUser.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    // 可能还有其他的方法
}
