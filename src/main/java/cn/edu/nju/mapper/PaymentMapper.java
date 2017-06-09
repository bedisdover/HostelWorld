package cn.edu.nju.mapper;

import cn.edu.nju.model.Payment;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-21.
 *
 * payment相关
 */
public interface PaymentMapper {

    int add(String hostelID, Date date, double amount);

    int pay(String hostelID);

    int payAll();

    List<Payment> getAllPayment();

    List<Payment> getPaymentByHostel(String hostelID);
}
