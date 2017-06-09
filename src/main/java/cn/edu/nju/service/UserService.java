package cn.edu.nju.service;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.dao.*;
import cn.edu.nju.model.CheckIn;
import cn.edu.nju.model.Order;
import cn.edu.nju.model.Room;
import cn.edu.nju.model.User;
import cn.edu.nju.util.DateUtil;
import cn.edu.nju.util.IdUtil;
import cn.edu.nju.util.SHA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by song on 17-2-17.
 * <p>
 * user相关
 */
@Service
@EnableTransactionManagement
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private PlanDao planDao;

    @Resource
    private CheckInDao checkInDao;

    @Resource
    private PaymentDao paymentDao;

    public MsgInfo register(String name, String password, String phoneNum, String bankCard) {
        if (userDao.isPhoneExisted(phoneNum)) {
            return new MsgInfo(false, "电话号已被注册");
        }

        User user = new User(IdUtil.getNext(userDao.getLastCardNum()), bankCard, name, SHA.encrypt(password), phoneNum);

        if (userDao.createUser(user)) {
            return new MsgInfo(true, "注册成功", user.getCardNum());
        } else {
            return new MsgInfo(false, "注册失败");
        }
    }

    public MsgInfo activateAccount(String cardNum, double amount) {
        if (amount < 1000) {
            return new MsgInfo(false, "单次充值需大于1000元");
        }

        if (userDao.activate(cardNum, amount)) {
            return new MsgInfo(true, "激活成功");
        } else {
            return new MsgInfo(false, "激活失败");
        }
    }

    public MsgInfo login(String cardNum, String password) {
        User user = userDao.getUserByCard(cardNum);

        if (user == null) {
            return new MsgInfo(false, "会员卡号不存在");
        }

//        if (!user.isActive()) {
//            return new MsgInfo(false, "账号未激活");
//        }

        if (SHA.encrypt(password).equals(user.getPassword())) {
            return new MsgInfo(true, "登录成功", user.getName());
        } else {
            return new MsgInfo(false, "密码错误");
        }
    }

    public MsgInfo getInfo(String cardNum) {
        User user = userDao.getUserByCard(cardNum);

        if (user == null) {
            return new MsgInfo(false, "会员卡号不存在");
        }

//        if (!user.isActive()) {
//            return new MsgInfo(false, "账号未激活");
//        }

        // 默认stopDate为null，spring无法处理，会报错
        user.setStopDate(new Date());
        return new MsgInfo(true, "", user);
    }

    public MsgInfo editInfo(String cardNum, String name, String password, String phoneNum, String bankCard) {
        if (userDao.editInfo(cardNum, name, password, phoneNum, bankCard)) {
            return new MsgInfo(true, "修改成功");
        } else {
            return new MsgInfo(false, "修改失败");
        }
    }

    /**
     * 操作步骤：
     * 1. 检查空房是否足够
     * 2. 减少空房数量
     * 3. 设置用户消费记录
     * 4. 添加旅店账单
     * 5. 添加订单
     */
    @Transactional(rollbackFor = Exception.class)
    public MsgInfo order(String cardNum, String hostelID, String type, long startDate, long endDate) {
        if (!"single".equals(type) && !"normal".equals(type)) {
            return new MsgInfo(false, "参数错误");
        }

        Date start = new Date(startDate);
        Date end = new Date(endDate);
        User user = userDao.getUserByCard(cardNum);
        Room room = planDao.getAvailableRoom(hostelID, start, end);
        // 总额 = 折扣 × 天数 × 单价
        double amount = user.getDiscount() * DateUtil.dateDiff(end, start) * room.getPrice(type);

        Order order = new Order(cardNum, hostelID, start, end, type, amount);

        // 1. 检查空房是否足够
        if (room.getNum(type) < 1) {
            return new MsgInfo(false, "无空闲房间");
        }

        // 2. 减少空房数量
        if (!planDao.minusRoom(order)) {
            return new MsgInfo(false, "无空闲房间");
        }

        // 3. 设置用户消费记录
        userDao.consume(cardNum, amount);

        // 4. 添加酒店账单
        paymentDao.add(hostelID, new Date(), amount);

        // 5. 添加订单
        int orderID = orderDao.order(order);

        return new MsgInfo(true, orderID + "", amount);
    }

    public MsgInfo dropOrder(int orderID) {
        if (orderDao.dropOrder(orderID)) {
            return new MsgInfo(true, "退订成功");
        } else {
            return new MsgInfo(false, "退订失败");
        }
    }

    public MsgInfo getAllHistory(String cardNum) {
        Map<String, Object> history = new HashMap<>();
        List<Order> orderList = orderDao.getAllOrder(cardNum);
        List<CheckIn> checkInList = checkInDao.getUserCheckIn(cardNum);

        history.put("order", orderList);
        history.put("checkIn", checkInList);

        return new MsgInfo(true, "", history);
    }

    public MsgInfo recharge(String cardNum, double amount) {
        if (userDao.recharge(cardNum, amount)) {
            return new MsgInfo(true, "充值成功");
        } else {
            return new MsgInfo(false, "出错了");
        }
    }
}
