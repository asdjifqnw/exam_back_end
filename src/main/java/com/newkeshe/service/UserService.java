package com.newkeshe.service;

import com.newkeshe.entity.User;

public interface UserService {
    Object login(String uPhone, String uPwd);
    Object register(User uesr);
    User ModiPersInfo(User user);
}
