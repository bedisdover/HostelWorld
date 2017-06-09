package cn.edu.nju.mapper;

import cn.edu.nju.model.User;

/**
 * Created by song on 17-2-17.
 * <p>
 * user相关
 */
public interface UserMapper {

    /**
     * 获取电话号为phoneNum的用户的数量, 用于判断电话号是否被注册
     *
     * @param phoneNum 电话号
     * @return 用户数量
     */
    int getUserNumByPhone(final String phoneNum);

    /**
     * 获取最后一张会员卡卡号
     */
    String getLastCardNum();

    /**
     * 插入用户
     *
     * @param user user持久化对象
     */
    int insertUser(User user);

    /**
     * 激活账户
     *
     * @param cardNum 会员卡号
     */
    int activate(String cardNum, double amount);

    /**
     * 编辑用户信息
     */
    int editInfo(String cardNum, String name, String password, String phoneNum, String bankCard);

    /**
     * 根据会员卡号获取user
     */
    User getUserByCard(String cardNum);

    /**
     * 获取折扣
     */
    double getDiscount(String cardNum);

    /**
     * 设置折扣
     */
    int setDiscount(String cardNum, String rate, double discount);

    /**
     * 充值
     */
    int recharge(String cardNum, double amount);

    /**
     * 消费
     */
    int consume(String cardNum, double amount);

    /**
     * 获取用户数量
     */
    int getUserNum();

    /**
     * 获取用户消费总额
     */
    double getUserConsume();

    void scanActivate();
}
