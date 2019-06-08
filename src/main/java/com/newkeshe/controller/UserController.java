package com.newkeshe.controller;

import com.newkeshe.dao.UserDao;
import com.newkeshe.entity.User;
import com.newkeshe.entity.User_Task;
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
    public Object Register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Object Login(@RequestBody Map map) {
        return userService.login(map.get("phone").toString(), map.get("password").toString());
    }

    @PatchMapping("/user/{userid}/modidfyUserInfo")
    public Object ModiUserInfo(@PathVariable Integer userid,
                               @RequestBody User user,
                               HttpServletRequest request) {
        user.setId(Integer.valueOf(request.getAttribute("uId").toString()));
        user.setAid(Integer.valueOf(request.getAttribute("uPerm").toString()));
        return userService.ModiPersInfo(user);
    }

    @GetMapping("/ivgs")
    public Object getIvgs() {
        return userService.listAllIvg();
    }

    @GetMapping("/tasks")
    public Object getTasks() {
        return userService.listAllTask();
    }

    @GetMapping("/me")
    public Object getMyInfo(HttpServletRequest request){
        return userService.findSelf(Integer.valueOf(request.getAttribute("uId").toString()));
    }

    @GetMapping("/findUserByIvgId")
    public Object getIvgsByUserId(@RequestParam Integer id) {
        return userService.viewIvgsUser(id);
    }

    @GetMapping("/findIvgByUser")
    public Object getUserByIvg(@RequestParam Integer id) {
        return userService.viewUsersIvg(id);
    }

    @PostMapping("/submitTask")
    public Object submitTask(@RequestBody User_Task user_task) {
        return userService.setUserTask(user_task);
    }

    @GetMapping("/findTaskInfoByUserIdAndTaskId")
    public Object findTaskInfoByUserIdAndTaskId(@RequestParam Integer uId, @RequestParam Integer tId) {
        return userService.findSomeoneTaskInfo(uId, tId);
    }
}
