package com.newkeshe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.newkeshe.dao.*;
import com.newkeshe.entity.*;
import com.newkeshe.service.UserService;
import com.newkeshe.util.entity.Result;
import com.newkeshe.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserIvgDao userIvgDao;
    @Autowired
    UserTaskDao userTaskDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    IvgDao ivgDao;

    @Autowired
    TokenService tokenService;

    PasswordEncoder p = new BCryptPasswordEncoder();

    public Object login(String uPhone, String uPwd) {
        User user = Optional.ofNullable(userDao.findByPhone(uPhone))
                .orElseThrow(() -> new RuntimeException("用户不存在!"));
        if (p.matches(uPwd, user.getPassword())) {
            JSONObject jsonObject = new JSONObject();
            user.setPassword("");
            jsonObject.put("uInfo", user);
            Map<String, String> map = new HashMap<>();
            map.put("uId", user.getId().toString());
            map.put("uPerm", user.getAid().toString());
            jsonObject.put("token", tokenService.encrypt(map));
            return jsonObject;
        } else {
            throw new RuntimeException("密码错误!");
        }
    }

    public Object register(User user) {
        if (userDao.findByPhone(user.getPhone()) == null) {
            user.setPassword(p.encode(user.getPassword()));
            userDao.save(user);
            return user;
        } else {
            throw new RuntimeException("电话号已存在!");
        }
    }

    public User ModiPersInfo(User user) {
        String phone = userDao.findById(user.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"未找到用户信息"))
                .getPhone();
        if (userDao.findByPhone(user.getPhone()) == null || user.getPhone().equals(phone)) {
            user.setPassword(p.encode(user.getPassword()));
            userDao.save(user);
            user.setPassword("");
            return user;
        } else
            throw new RuntimeException("电话号已存在!");
    }

    @Override
    public List<Ivg> listAllIvg() {
        return ivgDao.findAll();
    }

    @Override
    public List<User_Ivg> viewIvgsUser(Integer ivgId) {
        return Optional.ofNullable(userIvgDao.findByIvg(new Ivg(ivgId))).orElse(new ArrayList<>());
    }

    @Override
    public List<User_Ivg> viewUsersIvg(Integer uId) {
        return Optional.ofNullable(userIvgDao.findByUser(new User(uId))).orElse(new ArrayList<>());
    }

    @Override
    public User_Task setUserTask(User_Task user_task) {
        LocalDateTime ddl = taskDao.findById(user_task.getTask().getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getDdl();
        LocalDateTime now = LocalDateTime.now();
        user_task.setTimeOut(now.isAfter(ddl));
        return userTaskDao.save(user_task);
    }

    @Override
    public User_Task findSomeoneTaskInfo(Integer uId, Integer tId) {
        return userTaskDao.findByUserAndTask(new User(uId), new Task(tId));
    }

    @Override
    public List<Task> listAllTask() {
        return taskDao.findAll();
    }
}
