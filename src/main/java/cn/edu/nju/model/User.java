package cn.edu.nju.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by song on 17-2-17.
 *
 * User持久化对象
 */
public class User {

    /**
     * id, 对应id字段
     */
    private int id;

    /**
     * 会员卡号
     */
    private String cardNum;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 账户余额
     */
    private double balance;

    /**
     * 消费总额
     */
    private double sum;

    /**
     * 会员级别
     */
    private String rate;

    /**
     * 折扣
     */
    private double discount;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 是否激活
     */
    private boolean active;

    /**
     * 有效期
     */
    private Date validity;

    /**
     * 停止日期
     */
    private Date stopDate;

    public User() {
        /*do nothing*/
    }

    public User(String cardNum, String bankCard, String name, String password, String phoneNum) {
        this.cardNum = cardNum;
        this.bankCard = bankCard;
        this.name = name;
        this.password = password;
        this.phoneNum = phoneNum;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);

        this.validity = calendar.getTime();
    }

    public User(int id, String cardNum, String bankCard, double balance, double sum, String rate,
                String name, String password, String phoneNum, boolean active, Date validity, Date stopDate) {
        this.id = id;
        this.cardNum = cardNum;
        this.bankCard = bankCard;
        this.balance = balance;
        this.sum = sum;
        this.rate = rate;
        this.name = name;
        this.password = password;
        this.phoneNum = phoneNum;
        this.active = active;
        this.validity = new Date(validity.getTime());
        this.stopDate = new Date(stopDate.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getValidity() {
        return (Date) validity.clone();
    }

    public void setValidity(Date validity) {
        this.validity = new Date(validity.getTime());
    }

    public Date getStopDate() {
        return (Date) stopDate.clone();
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = new Date(stopDate.getTime());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", bankCard='" + bankCard + '\'' +
                ", balance=" + balance +
                ", sum=" + sum +
                ", rate='" + rate + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", active=" + active +
                ", validity=" + validity +
                ", stopDate=" + stopDate +
                '}';
    }
}
