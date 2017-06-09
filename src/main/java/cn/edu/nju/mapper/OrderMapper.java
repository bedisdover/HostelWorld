package cn.edu.nju.mapper;

import cn.edu.nju.model.Order;

import java.util.List;

/**
 * Created by song on 17-3-12.
 * <p>
 * room相关
 */
public interface OrderMapper {

    /**
     * 添加订单
     */
    int createOrder(Order order);

    /**
     * 取消订单
     */
    int dropOrder(int orderID);

    /**
     * 获取所有预定
     */
    List<Order> getAllOrder(String cardNum);

    /**
     * 所有预定数量
     */
    int getOrderNum();

    /**
     * 所有预定金额
     */
    double getOrderSum();

    List<Order> getOrderByHostel(String hostelID);
}
