package cn.edu.nju.service;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.dao.*;
import cn.edu.nju.model.*;
import cn.edu.nju.util.DateUtil;
import cn.edu.nju.util.IdUtil;
import cn.edu.nju.util.SHA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by song on 17-3-8.
 * <p>
 * Hostel相关
 */
@Service
@EnableTransactionManagement
public class HostelService {

    @Resource
    private HostelDao hostelDao;

    @Resource
    private ApplicationDao applicationDao;

    @Resource
    private PlanDao planDao;

    @Resource
    private CheckInDao checkInDao;

    @Resource
    private PaymentDao paymentDao;

    @Resource
    private UserDao userDao;

    @Resource
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class)
    public MsgInfo register(String name, String address, String password) {
        if (hostelDao.isHostelExisted(name, address)) {
            return new MsgInfo(false, "旅店已存在");
        }

        String hostelID = 'h' + IdUtil.getNext(hostelDao.getLastID().substring(1));
        Hostel hostel = new Hostel(hostelID, name, address, SHA.encrypt(password));
        if (hostelDao.createHostel(hostel) && applicationDao.applyForOpen(hostelID, name, address)) {
            return new MsgInfo(true, "注册成功", hostel.getHostelID());
        } else {
            return new MsgInfo(false, "注册失败");
        }
    }

    public MsgInfo login(String hostelID, String password) {
        Hostel hostel = hostelDao.getHostelByID(hostelID);

        if (hostel == null) {
            return new MsgInfo(false, "旅店不存在");
        }

        OpenApplication application = applicationDao.getOpenApplication(hostelID);

        switch (application.getPass()) {
            case "waiting":
                return new MsgInfo(false, "申请尚未通过，请等待");
            case "reject":
                return new MsgInfo(false, "申请未通过");
        }

        if (SHA.encrypt(password).equals(hostel.getPassword())) {
            return new MsgInfo(true, "登录成功");
        } else {
            return new MsgInfo(false, "密码错误");
        }
    }

    /**
     * 获取hostel信息
     */
    public MsgInfo getInfo(String hostelID) {
        Hostel hostel = hostelDao.getHostelByID(hostelID);

        if (hostel != null) {
            // 返回前重置password
            hostel.setPassword(null);

            return new MsgInfo(true, "", hostel);
        } else {
            return new MsgInfo(false, "不存在");
        }
    }

    /**
     * 申请修改信息
     */
    public MsgInfo applyModifyInfo(String hostelID, String name, String address) {
        Hostel hostel = hostelDao.getHostelByID(hostelID);

        if (applicationDao.applyForModify(hostel, name, address)) {
            return new MsgInfo(true, "申请已提交，请等待结果");
        } else {
            return new MsgInfo(false, "申请失败");
        }
    }

    public MsgInfo getAllPlan(String hostelID) {
        return new MsgInfo(true, "", planDao.getAllPlan(hostelID));
    }

    /**
     * 发布计划
     */
    public MsgInfo publishPlan(String hostelID, long startDate, long endDate, int single, int normal,
                               double singlePrice, double normalPrice) {
        Date start = new Date(startDate);
        Date end = new Date(endDate);

        if (planDao.hasPlan(hostelID, start, end)) {
            return new MsgInfo(false, "同时间段已存在计划");
        }

        RoomPlan roomPlan = new RoomPlan(hostelID, start, end, single, normal, singlePrice, normalPrice);
        if (planDao.publishPlan(roomPlan)) {
            return new MsgInfo(true, "发布成功");
        } else {
            return new MsgInfo(false, "发布失败");
        }
    }

    /**
     * 非会员入住
     */
    public MsgInfo checkInNonUser(String hostelID, String name, String roomID, long endDate, String type) {
        Date start = new Date();
        Date end = new Date(endDate);

        Room room = planDao.getAvailableRoom(hostelID, start, end);

        if (room == null) {
            return new MsgInfo(false, "无空房");
        }

        // 单价
        double price = room.getPrice(type);
        // 总额
        double amount = DateUtil.dateDiff(end, start) * price;

        CheckIn checkIn = new CheckIn(hostelID, name, roomID, start, end, type, price, amount);

        if (checkInDao.CheckIn(checkIn)) {
            return new MsgInfo(true, "入住成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }

    public MsgInfo checkIn(String hostelID, String cardNum, String roomID, long endDate, String type) {
        return checkIn(hostelID, cardNum, roomID, endDate, type, 0);
    }

    /**
     * 会员入住
     */
    public MsgInfo checkIn(String hostelID, String cardNum, String roomID, long endDate,
                           String type, int orderID) {
        Date start = new Date();
        Date end = new Date(endDate);

        Room room = planDao.getAvailableRoom(hostelID, start, end);

        if (room == null) {
            return new MsgInfo(false, "无空房");
        }

        // 折扣
        double discount = userDao.getDiscount(cardNum);
        // 单价
        double price = room.getPrice(type);
        // 总额
        double amount = DateUtil.dateDiff(end, start) * price * discount;

        CheckIn checkIn = new CheckIn(cardNum, hostelID, roomID, start, end, type, orderID, price, discount, amount);

        if (checkInDao.CheckIn(checkIn)) {
            return new MsgInfo(true, "入住成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }

    public MsgInfo checkOut(String hostelID, int checkInID, String payType) {
        // 使用会员卡支付
        if ("card".equals(payType)) {
            CheckIn checkIn = checkInDao.getCheckInByID(checkInID);

            // 未预定，使用会员卡支付
            if (checkIn.getOrderID() == 0) {
                userDao.consume(checkIn.getCardNum(), checkIn.getMoney());
            }

            // 旅店记账
            paymentDao.add(hostelID, new Date(), checkIn.getMoney());
        }

        if (checkInDao.checkOut(checkInID, payType)) {
            return new MsgInfo(true, "离店成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }

    public MsgInfo getAllCheckIn(String hostelID) {
        return new MsgInfo(true, "", checkInDao.getCurrentCheckIn(hostelID));
    }

    /**
     * 统计
     */
    public MsgInfo statistics(String hostelID) {
        Map<String, Object> result = new HashMap<>();

        result.put("order", orderDao.getOrderByHostel(hostelID));

        result.put("payment", paymentDao.getPaymentByHostel(hostelID));

        return new MsgInfo(true, "", result);
    }
}
