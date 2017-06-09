package cn.edu.nju.model;

import java.util.Date;

/**
 * Created by song on 17-3-12.
 *
 * 入住登记
 */
public class CheckIn {

    private int id;

    private String cardNum;

    private String hostelID;

    private String name;

    private String roomID;

    private Date startDate;

    private Date endDate;

    /**
     * 单间/标准间
     */
    private String type;

    /**
     * 订单号
     */
    private int orderID;

    /**
     * 支付方式（会员卡/现金）
     */
    private String payType;

    private double price;

    private double discount;

    private double money;

    private boolean finish;

    public CheckIn() {
        /*do nothing*/
    }

    public CheckIn(String hostelID, String name, String roomID, Date startDate, Date endDate, String type,
                   double price, double money) {
        this.hostelID = hostelID;
        this.name = name;
        this.roomID = roomID;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.orderID = 0;
        this.type = type;
        this.price = price;
        this.money = money;
    }

    public CheckIn(String cardNum, String hostelID, String roomID, Date startDate, Date endDate, String type,
                   int orderID, double price, double discount, double money) {
        this.cardNum = cardNum;
        this.hostelID = hostelID;
        this.roomID = roomID;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.type = type;
        this.orderID = orderID;
        this.price = price;
        this.discount = discount;
        this.money = money;
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

    public String getHostelID() {
        return hostelID;
    }

    public void setHostelID(String hostelID) {
        this.hostelID = hostelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public void setStartDate(Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }

    public void setEndDate(Date endDate) {
        this.endDate = new Date(endDate.getTime());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", hostelID='" + hostelID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", money=" + money +
                '}';
    }
}
