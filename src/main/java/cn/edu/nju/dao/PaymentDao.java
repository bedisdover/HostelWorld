package cn.edu.nju.dao;

import cn.edu.nju.mapper.PaymentMapper;
import cn.edu.nju.model.Payment;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-21.
 *
 * 应付款（经理结算给hostel）相关
 */
@Repository
public class PaymentDao {

    @Resource
    private PaymentMapper paymentMapper;

    /**
     * 记账
     */
    public boolean add(String hostelID, Date date, double amount) {
        try {
            paymentMapper.add(hostelID, date, amount);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 结算
     */
    public boolean pay(String hostelID) {
        try {
            paymentMapper.pay(hostelID);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean payAll() {
        try {
            paymentMapper.payAll();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Payment> getPaymentByHostel(String hostelID) {
        return paymentMapper.getPaymentByHostel(hostelID);
    }

    /**
     * 所有未结算的payment
     */
    public List<Payment> getAllPayment() {
        return paymentMapper.getAllPayment();
    }
}
