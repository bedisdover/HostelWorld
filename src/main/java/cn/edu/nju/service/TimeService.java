package cn.edu.nju.service;

import cn.edu.nju.dao.UserDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by song on 17-3-24.
 *
 * 定时查看会员状态
 */
@Service
public class TimeService {

    @Resource
    private UserDao userDao;

    //每天凌晨两点执行
    @Scheduled(cron = "0 0 2 * * ?")
    public void activate() {
        userDao.scanActivate();
    }
}
