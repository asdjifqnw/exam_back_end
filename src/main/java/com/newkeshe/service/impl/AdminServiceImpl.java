package com.newkeshe.service.impl;

import com.newkeshe.dao.*;
import com.newkeshe.entity.*;
import com.newkeshe.service.AdminService;
import com.newkeshe.util.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserDao userDao;
    @Autowired
    IvgDao ivgDao;
    @Autowired
    UserIvgDao userIvgDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    UserTaskDao userTaskDao;

    PasswordEncoder p = new BCryptPasswordEncoder();

    @Override
    public User addUser(User user) {
        if (userDao.findByPhone(user.getPhone()) == null) {
            user.setPassword(p.encode(user.getPassword()));
            userDao.save(user);
            user.setPassword("");
            return user;
        } else {
            throw new RuntimeException("电话号已存在!");
        }
    }

    @Override
    public Boolean rmUser(Integer uId) {
        Optional.ofNullable(userDao.findById(uId))
                .orElseThrow(() -> new RuntimeException("用户不存在,请检查你您的操作."));
        userDao.deleteById(uId);
        return true;
    }

    @Override
    public User modiUserInfo(User user) {
        String phone = userDao.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "发生错误"))
                .getPhone();
        if (user.getPhone().equals(phone) || userDao.findByPhone(user.getPhone()) == null) {
            user.setPassword(p.encode(user.getPassword()));
            userDao.save(user);
            user.setPassword("");
            return user;
        } else
            throw new RuntimeException("电话号已存在!");
    }

    @Override
    public Ivg addIvg(Ivg ivg) {
        ivgDao.save(ivg);
        return ivg;
    }

    @Override
    public Boolean rmIvg(Integer ivgId) {
        ivgDao.findById(ivgId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "未找到"));
        ivgDao.deleteById(ivgId);
        return true;
    }

    @Override
    public Ivg modiIvgInfo(Ivg ivg) {
        ivgDao.save(ivg);
        return ivg;
    }

    @Override
    public User_Ivg setUserIvg(Integer uId, Integer ivgId) {
        if (!userDao.findById(uId).isPresent() || !ivgDao.findById(ivgId).isPresent())
            throw new RuntimeException("参数错误");
        User_Ivg user_ivg = new User_Ivg();
        user_ivg.setUser(new User(uId));
        user_ivg.setIvg(new Ivg(ivgId));
        userIvgDao.save(user_ivg);
        return user_ivg;
    }

    @Override
    public Boolean rmUserIvg(Integer uId, Integer ivgId) {
        if (userIvgDao.findByUserAndIvg(new User(uId), new Ivg(ivgId)).isEmpty())
            throw new RuntimeException("参数错误");
        userIvgDao.deleteByUserAndIvg(new User(uId), new Ivg(ivgId));
        return true;
    }

    @Override
    public Task addTask(Task task) {
        taskDao.save(task);
        return task;
    }

    @Override
    public Boolean rmTask(Integer tId) {
        taskDao.findById(tId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"未找到任务信息"));
            taskDao.deleteById(tId);
            return true;
    }

    @Override
    public Task modiTaskInfo(Task task) {
        taskDao.save(task);
        return task;
    }

    @Override
    public Task closeTask(Integer tId) {
        taskDao.closeTask(tId);
        return taskDao.findById(tId).orElse(null);
    }

    @Override
    public boolean rmUserTask(Integer uId, Integer tId) {
        if (!userDao.findById(uId).isPresent() || !taskDao.findById(tId).isPresent()) {
            throw new RuntimeException("参数错误");
        } else {
            userTaskDao.deleteByUserAndTask(new User(uId), new Task(tId));
            return true;
        }
    }
}
