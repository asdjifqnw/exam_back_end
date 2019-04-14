package com.newkeshe.service.impl;

import com.newkeshe.dao.*;
import com.newkeshe.entity.*;
import com.newkeshe.service.AdminService;
import com.newkeshe.util.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        if (userDao.findByUPhone(user.getUPhone()).isEmpty()) {
            user.setUPwd(p.encode(user.getUPwd()));
            userDao.save(user);
            user.setUPwd("");
            return user;
        } else {
            throw new RuntimeException("电话号已存在!");
        }
    }

    @Override
    public Boolean rmUser(Integer uId) {
        if (userDao.findByUId(uId).isEmpty()) {
            throw new RuntimeException("用户不存在,请检查你您的操作.");
        }
        userDao.deleteByUId(uId);
        return true;
    }

    @Override
    public User modiUserInfo(User user) {
        String phone = userDao.findByUId(user.getUId()).get(0).getUPhone();
        if (user.getUPhone().equals(phone) || userDao.findByUPhone(user.getUPhone()).isEmpty()) {
            user.setUPwd(p.encode(user.getUPwd()));
            userDao.save(user);
            user.setUPwd("");
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
        if (!ivgDao.findByIvgId(ivgId).isEmpty()) {
            ivgDao.deleteByIvgId(ivgId);
            return true;
        } else
            throw new RuntimeException("监考信息不存在，请重试");
    }

    @Override
    public Ivg modiIvgInfo(Ivg ivg) {
        if (!ivgDao.findByIvgId(ivg.getIvgId()).get(0).equals(ivg)) {
            ivgDao.save(ivg);
            return ivg;
        } else
            throw new RuntimeException("任务信息未更改");
    }

    @Override
    public User_Ivg setUserIvg(Integer uId, Integer ivgId) {
        if(userDao.findByUId(uId).isEmpty()||ivgDao.findByIvgId(ivgId).isEmpty())
            throw new RuntimeException("参数错误");
        User_Ivg user_ivg = new User_Ivg();
        user_ivg.setUser(new User(uId));
        user_ivg.setIvg(new Ivg(ivgId));
        userIvgDao.save(user_ivg);
        return user_ivg;
    }

    @Override
    public Boolean rmUserIvg(Integer uId, Integer ivgId) {
        if(userIvgDao.findByUserAndIvg(new User(uId), new Ivg(ivgId)).isEmpty())
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
        if (taskDao.findByTId(tId).isEmpty())
            throw new RuntimeException("任务不存在!");
        else {
            taskDao.deleteByTId(tId);
            return true;
        }
    }

    @Override
    public Task modiTaskInfo(Task task) {
        taskDao.save(task);
        return task;
    }

    @Override
    public boolean rmUserTask(Integer uId, Integer tId) {
        if(userDao.findByUId(uId).isEmpty()||taskDao.findByTId(tId).isEmpty())
            throw new RuntimeException("参数错误");
        else{
            userTaskDao.deleteByUserAndTask(new User(uId), new Task(tId));
            return true;
        }
    }
}
