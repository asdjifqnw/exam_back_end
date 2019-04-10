package com.newkeshe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.newkeshe.dao.UserDao;
import com.newkeshe.entity.User;
import com.newkeshe.service.UserService;
import com.newkeshe.util.entity.Result;
import com.newkeshe.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    TokenService tokenService;

    PasswordEncoder p = new BCryptPasswordEncoder();

    public Object login(String uPhone,String uPwd){
        List<User> list = userDao.findByUPhone(uPhone);
        if(list.isEmpty())
            throw new RuntimeException("用户不存在!");
        if(p.matches(uPwd, list.get(0).getUPwd())){
            JSONObject jsonObject = new JSONObject();
            list.get(0).setUPwd("");
            jsonObject.put("uInfo",list.get(0));
            Map<String,String> map = new HashMap<>();
            map.put("uId",list.get(0).getUId().toString());
            map.put("uPerm",list.get(0).getUPerm().toString());
            jsonObject.put("token",tokenService.encrypt(map));
            return jsonObject;
        }else{
            throw new RuntimeException("密码错误!");
        }
    }
    public Object register(User user){
        if(userDao.findByUPhone(user.getUPhone()).isEmpty()){
            user.setUPwd(p.encode(user.getUPwd()));
            userDao.save(user);
            return new Result();
        } else {
            throw new RuntimeException("电话号已存在!");
        }
    }

    public User ModiPersInfo(User user){
        String phone = userDao.findByUId(user.getUId()).get(0).getUPhone();
        if(userDao.findByUPhone(user.getUPhone()).isEmpty() || user.getUPhone().equals(phone)){
            user.setUPwd(p.encode(user.getUPwd()));
            userDao.save(user);
            user.setUPwd("");
            return user;
        }else
            throw new RuntimeException("电话号已存在!");
    }
}
