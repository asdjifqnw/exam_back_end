package com.newkeshe.controller;

import com.newkeshe.dao.TaskDao;
import com.newkeshe.entity.Ivg;
import com.newkeshe.entity.Task;
import com.newkeshe.entity.User;
import com.newkeshe.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.findAllUser();
    }

    @PostMapping("/user")
    public Object addUser(@RequestBody User user) {
        log.info(user.toString());
        return adminService.addUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Object rmUser(@PathVariable Integer id) {
        return adminService.rmUser(id);
    }

    @PatchMapping("/user/{id}")
    public Object modiUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        return adminService.modiUserInfo(user);
    }

    @PostMapping("/ivg")
    public Object addIvg(@RequestBody Ivg ivg) {
        return adminService.addIvg(ivg);
    }

    @DeleteMapping("/ivg/{id}")
    public Object rmIvg(@PathVariable Integer id) {
        return adminService.rmIvg(id);
    }

    @PatchMapping("/ivg/{id}")
    public Object modiIvg(@PathVariable Integer id, @RequestBody Ivg ivg) {
        ivg.setId(id);
        return adminService.modiIvgInfo(ivg);
    }

    @GetMapping("/user_ivg/{id}/countIvgs")
    public Integer countIsSetIvg(@PathVariable Integer id){
        return adminService.countIsSetIvg(id);
    }
    @PostMapping("/user_ivg")
    public Object setUserIvg(@RequestBody Map map) {
        return adminService.setUserIvg(
                Integer.valueOf(map.get("uId").toString()),
                Integer.valueOf(map.get("ivgId").toString()));
    }

    @DeleteMapping("/user_ivg/{id}")
    public Object rmUserIvg(@PathVariable Integer id) {
        return adminService.rmUserIvg(id);

    }

    @PostMapping("/task")
    public Object addTask(@RequestBody Task task) {
        return adminService.addTask(task);
    }

    @DeleteMapping("/task/{id}")
    public Object rmTask(@PathVariable Integer id) {
        return adminService.rmTask(id);
    }

    @PatchMapping("/task/{id}")
    public Object modiTask(@PathVariable Integer id, @RequestBody Task task) {
        task.setId(id);
        return adminService.modiTaskInfo(task);
    }

    @GetMapping("/task/{id}/close")
    public Object closeTask(@PathVariable Integer id) {
        return adminService.closeTask(id);
    }

    @DeleteMapping("/user_task/{id}")
    public Object rmUserTask(@PathVariable Integer id) {
        return adminService.rmUserTask(id);
    }

}
