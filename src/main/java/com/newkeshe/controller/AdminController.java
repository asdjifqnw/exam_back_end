package com.newkeshe.controller;

import com.newkeshe.entity.Ivg;
import com.newkeshe.entity.Task;
import com.newkeshe.entity.User;
import com.newkeshe.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/user")
    public Object addUser(@RequestBody User user) {
        log.info(user.toString());
        return adminService.addUser(user);
    }

    @DeleteMapping("/user")
    public Object rmUser(@RequestBody Map map) {
        log.info("准备删除用户的Id:{} ", map.get("id"));
        return adminService.rmUser(Integer.valueOf(map.get("id").toString()));
    }

    @PatchMapping("/user")
    public Object modiUser(@RequestBody User user) {
        return adminService.modiUserInfo(user);
    }

    @PostMapping("/ivg")
    public Object addIvg(@RequestBody Ivg ivg) {
        return adminService.addIvg(ivg);
    }

    @DeleteMapping("/ivg")
    public Object rmIvg(@RequestBody Map map) {
        log.info("准备删除监考信息的Id:{} ", map.get("uid"));
        return adminService.rmIvg(Integer.valueOf(map.get("ivgId").toString()));
    }

    @PatchMapping("/ivg")
    public Object modiIvg(@RequestBody Ivg ivg) {
        return adminService.modiIvgInfo(ivg);
    }

    @PostMapping("/user_ivg")
    public Object setUserIvg(@RequestBody Map map) {
        return adminService.setUserIvg(
                Integer.valueOf(map.get("uId").toString()),
                Integer.valueOf(map.get("ivgId").toString()));
    }
    @DeleteMapping("/user_ivg")
    public Object rmUserIvg(@RequestBody Map map){
        return adminService.rmUserIvg(
                Integer.valueOf(map.get("uId").toString()),
                Integer.valueOf(map.get("ivgId").toString()));
    }
    @PostMapping("/task")
    public Object addTask(@RequestBody Task task){
        log.info(task.toString());
        return adminService.addTask(task);
    }
    @DeleteMapping("/task")
    public Object rmTask(@RequestBody Map map){
        return adminService.rmTask(Integer.valueOf(map.get("tId").toString()));
    }
    @PatchMapping("/task")
    public Object modiTask(@RequestBody Task task){
        return adminService.modiTaskInfo(task);
    }
    @DeleteMapping("/user_task")
    public Object rmUserTask(@RequestBody Map map){
        return adminService.rmUserTask(Integer.valueOf(map.get("uId").toString()),
                Integer.valueOf(map.get("tId").toString()));
    }

}
