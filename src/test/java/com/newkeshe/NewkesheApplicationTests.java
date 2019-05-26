package com.newkeshe;

import com.newkeshe.entity.Task;
import com.newkeshe.entity.User;
import com.newkeshe.entity.User_Task;
import com.newkeshe.service.AdminService;
import com.newkeshe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewkesheApplicationTests {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
        User_Task user_task = new User_Task();
        user_task.setTask(new Task(17));
        user_task.setUser(new User(1));
        userService.setUserTask(user_task);
    }
    @Test
    public void test2(){
        System.out.println(userService.findSomeoneTaskInfo(1, 17).getContent());
    }

}
