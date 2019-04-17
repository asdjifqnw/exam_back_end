package com.newkeshe.controller;

import com.newkeshe.entity.User;
import com.newkeshe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserService userService;
    @GetMapping("/test")
    public Object test(){
        return "2333";
    }
}
