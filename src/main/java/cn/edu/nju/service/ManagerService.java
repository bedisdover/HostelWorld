package cn.edu.nju.service;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.dao.*;
import cn.edu.nju.model.Payment;
import cn.edu.nju.util.SHA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 17-3-9.
 * <p>
 * manager相关
 */
@Service
@EnableTransactionManagement
public class ManagerService {

    @Resource
    private ManagerDao managerDao;

    @Resource
    private ApplicationDao applicationDao;

    @Resource
    private HostelDao hostelDao;
    
    @Resource
    private PaymentDao paymentDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PlanDao planDao;

    @Resource
    private OrderDao orderDao;

    public MsgInfo login(String name, String password) {
        String temp = managerDao.getPassword(name);

        if (temp == null) {
            return new MsgInfo(false, "账户不存在");
        }

        if (SHA.encrypt(password).equals(temp)) {
            return new MsgInfo(true, "登录成功", name);
        } else {
            return new MsgInfo(false, "密码错误");
        }
    }

    public MsgInfo getAllApplication() {
        Map<String, Object> application = new HashMap<>();

        application.put("open", applicationDao.getOpenApplicationList());
        application.put("modify", applicationDao.getModifyApplicationList());

        return new MsgInfo(true, "", application);
    }

    @Transactional(rollbackFor = Exception.class)
    public MsgInfo approveOpen(String hostelID, String pass, String notes) {
        if (applicationDao.approveOpen(hostelID, pass, notes) && hostelDao.activateHostel(hostelID)) {
            return new MsgInfo(true, "审批成功");
        } else {
            return new MsgInfo(false, "审批失败");
        }
    }

    public MsgInfo approveModify(int id, String pass, String notes) {
        if (applicationDao.approveModify(id, pass, notes)) {
            return new MsgInfo(true, "审批成功");
        } else {
            return new MsgInfo(false, "审批失败");
        }
    }

    @Transactional
    public MsgInfo approveOpenBatch(int[] id, String pass) {
        if (applicationDao.approveOpenBatch(id, pass)) {
            return new MsgInfo(true, "审批成功");
        } else {
            return new MsgInfo(false, "审批失败");
        }
    }

    @Transactional
    public MsgInfo approveModifyBatch(int[] id, String pass) {
        if (applicationDao.approveModifyBatch(id, pass)) {
            return new MsgInfo(true, "审批成功");
        } else {
            return new MsgInfo(false, "审批失败");
        }
    }
    
    public MsgInfo getAllPayment() {
        Map<String, List<Payment>> result = new HashMap<>();
        
        List<Payment> paymentList = paymentDao.getAllPayment();
        for (Payment payment : paymentList) {
            String hostelID = payment.getHostelID();
            if (result.containsKey(hostelID)) {
                result.get(hostelID).add(payment);
            } else {
                List<Payment> temp = new ArrayList<>();
                temp.add(payment);
                
                result.put(hostelID, temp);
            }
        }
        
        return new MsgInfo(true, "", result);
    }
    
    public MsgInfo pay(String hostelID) {
        if (paymentDao.pay(hostelID)) {
            return new MsgInfo(true, "结算成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }
    
    public MsgInfo payAll() {
        if (paymentDao.payAll()) {
            return new MsgInfo(true, "结算成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }

    public MsgInfo getStatistics() {
        Map<String, Object> result = new HashMap<>();

        result.put("userNum", userDao.getUserNum());
        result.put("userConsume", userDao.getUserConsume());

        result.put("hostelNum", hostelDao.getHostelNum());
        result.put("planNum", planDao.getPlanNum());

        result.put("orderNum", orderDao.getOrderNum());
        result.put("orderSum", orderDao.getOrderSum());

        return new MsgInfo(true, "", result);
    }
}
