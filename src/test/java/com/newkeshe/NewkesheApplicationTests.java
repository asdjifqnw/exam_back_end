package com.newkeshe;

import com.newkeshe.dao.UserIvgDao;
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
    @Autowired
    UserIvgDao userIvgDao;
    @Test
    public void contextLoads() {
//        System.out.println(userService.listAllIvg());
//        System.out.println(userIvgDao.findCountUserByUserId(1));
//        System.out.println(userIvgDao.findCountUserByUserId(22));
        System.out.println(adminService.findAllUser());
//        System.out.println(userService.listAllIvg());
    }

}
