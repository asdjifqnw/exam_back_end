package com.newkeshe.service;

import com.newkeshe.entity.*;

import java.util.List;

public interface UserService {
    Object login(String uPhone, String uPwd);
    Object register(User uesr);
    User ModiPersInfo(User user);
    List<Ivg> listAllIvg();
    List<User_Ivg> viewIvgsUser(Integer ivgId);
    List<User_Ivg> viewUsersIvg(Integer uId);
    User_Task setUserTask(User_Task user_task);
    User_Task findSomeoneTaskInfo(Integer uId,Integer tId);
    List<Task> listAllTask();
}
