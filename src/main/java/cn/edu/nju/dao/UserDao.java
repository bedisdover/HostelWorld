package cn.edu.nju.dao;

import cn.edu.nju.mapper.UserMapper;
import cn.edu.nju.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by song on 17-2-17.
 * <p>
 * user相关
 */
@Repository
public class UserDao {

    @Resource
    private UserMapper userMapper;

    /**
     * 判断电话号是否被注册
     *
     * @param phoneNum 电话号
     * @return 已注册返回true, 否则返回false
     */
    public boolean isPhoneExisted(String phoneNum) {
        int num = userMapper.getUserNumByPhone(phoneNum);

        return num == 1;
    }

    /**
     * 获取最后一张会员卡卡号
     *
     * @return 最后一张会员卡卡号
     */
    public String getLastCardNum() {
        return userMapper.getLastCardNum();
    }

    public boolean createUser(User user) {
        try {
            userMapper.insertUser(user);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 激活
     */
    public boolean activate(String cardNum, double amount) {
        try {
            userMapper.activate(cardNum, amount);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editInfo(String cardNum, String name, String password, String phoneNum, String bankCard) {
        try {
            userMapper.editInfo(cardNum, name, password, phoneNum, bankCard);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据会员卡号获取用户持久化对象
     *
     * @param cardNum 会员卡号
     * @return 用户持久化对象
     */
    public User getUserByCard(String cardNum) {
        return userMapper.getUserByCard(cardNum);
    }

    public double getDiscount(String cardNum) {
        return userMapper.getDiscount(cardNum);
    }

    /**
     * 设置卡级别和折扣
     */
    private boolean setDiscount(String cardNum, String rate, double discount) {
        try {
            userMapper.setDiscount(cardNum, rate, discount);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 充值
     */
    public boolean recharge(String cardNum, double amount) {
        try {
            userMapper.recharge(cardNum, amount);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 消费
     * 若消费总金额大于1000， 升级会员 银卡
     * 若消费总金额大于5000， 升级会员 金卡
     * 若消费总金额大于10000， 升级会员 白金卡
     */
    public boolean consume(String cardNum, double amount) {
        try {
            userMapper.consume(cardNum, amount);

            User user = getUserByCard(cardNum);

            if (user.getSum() > 10000) {
                setDiscount(cardNum, "白金卡", 0.7);
            } else if (user.getSum() > 5000) {
                setDiscount(cardNum, "金卡", 0.8);
            } else if (user.getSum() > 1000) {
                setDiscount(cardNum, "银卡", 0.9);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 用户数量
     */
    public int getUserNum() {
        return userMapper.getUserNum();
    }

    public double getUserConsume() {
        return userMapper.getUserConsume();
    }

    public void scanActivate() {
        userMapper.scanActivate();
    }
}
