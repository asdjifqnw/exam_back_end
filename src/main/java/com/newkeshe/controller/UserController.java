package com.newkeshe.controller;

import com.newkeshe.entity.User;
import com.newkeshe.service.UserService;
import com.newkeshe.util.entity.PhoneAndPwd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Object Register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public Object Login(@RequestBody PhoneAndPwd p){
        return userService.login(p.getPhone(), p.getPwd());
    }
    @PatchMapping("/user_id/{userid}/modi_user_info")
    public Object ModiUserInfo(@PathVariable Integer userid,
                               @RequestBody User user,
                               HttpServletRequest request){
        user.setUId(Integer.valueOf(request.getAttribute("uId").toString()));
        user.setUId(Integer.valueOf(request.getAttribute("uPerm").toString()));
        return userService.ModiPersInfo(user);
    }
}
