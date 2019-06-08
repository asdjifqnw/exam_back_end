package com.newkeshe.service.impl;

import com.newkeshe.dao.*;
import com.newkeshe.entity.*;
import com.newkeshe.service.AdminService;
import com.newkeshe.util.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    public List<User> findAllUser() {
        return userDao.findAll();
    }

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
        ivgDao.findById(ivgId).ifPresent(ivg -> {
            if (userIvgDao.findCountIvgByIvgId(ivgId) >= ivg.getNumbersOfTeacher()) {
                throw new RuntimeException("分配人数超过限制");
            }
        });
        userIvgDao.findByUser(new User(uId)).forEach(ui -> {
            //用户已分配的考试的开始时间和结束时间
            LocalDateTime isSetBegin = ui.getIvg().getBeginTime();
            LocalDateTime isSetEnd = isSetBegin.plusHours(ui.getIvg().getDuration().getHour())
                    .plusMinutes(ui.getIvg().getDuration().getMinute());
            //准备分配的考试的开始和结束时间
            Ivg ivg = ivgDao.findById(ivgId).orElseThrow(() -> new RuntimeException("发生错误"));
            LocalDateTime begin = ivg.getBeginTime();
            LocalDateTime end = begin.plusHours(ivg.getDuration().getHour())
                    .plusMinutes(ivg.getDuration().getMinute());
            if ((end.isBefore(isSetEnd) && end.isAfter(isSetBegin))
                    || (begin.isBefore(isSetEnd) && begin.isAfter(isSetBegin))
                    || (begin.isBefore(isSetBegin) && end.isAfter(isSetEnd))) {
                userIvgDao.save(user_ivg);
                throw new RuntimeException("信息已保存,但是与时间为" + ui.getIvg().getBeginTime() +
                        "的" + ui.getIvg().getName() + "考试冲突");
            }
        });
        return userIvgDao.save(user_ivg);
    }

    @Override
    public Integer countIsSetIvg(Integer ivgId) {
        return userIvgDao.findCountIvgByIvgId(ivgId);
    }

    @Override
    public Boolean rmUserIvg(Integer id) {
        userIvgDao.deleteById(id);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "未找到任务信息"));
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
    public boolean rmUserTask(Integer id) {
        userTaskDao.deleteById(id);
        return true;
    }
}
