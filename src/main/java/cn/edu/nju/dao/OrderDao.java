package cn.edu.nju.dao;

import cn.edu.nju.mapper.OrderMapper;
import cn.edu.nju.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-12.
 * <p>
 * room相关
 */
@Repository
public class OrderDao {

    @Resource
    private OrderMapper orderMapper;

    public int order(Order order) {
        orderMapper.createOrder(order);

        return order.getId();
    }

    public boolean dropOrder(int orderID) {
        try {
            orderMapper.dropOrder(orderID);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取所有预定
     */
    public List<Order> getAllOrder(String cardNum) {
        return orderMapper.getAllOrder(cardNum);
    }

    public List<Order> getOrderByHostel(String hostelID) {
        return orderMapper.getOrderByHostel(hostelID);
    }

    public int getOrderNum() {
        return orderMapper.getOrderNum();
    }

    public double getOrderSum() {
        return orderMapper.getOrderSum();
    }
}
