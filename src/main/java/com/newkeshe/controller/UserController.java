package com.newkeshe.controller;

import com.newkeshe.entity.User;
import com.newkeshe.service.UserService;
import com.newkeshe.util.entity.PhoneAndPwd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Object Register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public Object Login(@RequestBody Map map){
        return userService.login(map.get("phone").toString(),map.get("password").toString());
    }
    @PatchMapping("/user_id/{userid}/modi_user_info")
    public Object ModiUserInfo(@PathVariable Integer userid,
                               @RequestBody User user,
                               HttpServletRequest request){
        user.setId(Integer.valueOf(request.getAttribute("uId").toString()));
        user.setAid(Integer.valueOf(request.getAttribute("uPerm").toString()));
        return userService.ModiPersInfo(user);
    }
}
