package com.newkeshe.util;

import com.newkeshe.dao.UserIvgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeService {
    @Autowired
    UserIvgDao userIvgDao;
    @Scheduled(cron = "0 0 0 * * *")
    public void PrintIvgInfo(){
        System.out.println("今天的考试有"+userIvgDao.findAll());
    }
}
