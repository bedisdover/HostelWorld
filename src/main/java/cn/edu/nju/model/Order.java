package cn.edu.nju.model;

import java.util.Date;

/**
 * Created by song on 17-3-12.
 *
 * order订单
 */
public class Order {

    private int id;

    private String cardNum;

    private String hostelID;

    private Date startDate;

    private Date endDate;

    private String type;

    private double amount;

    private boolean finish;

    public Order() {
        /*do nothing*/
    }

    public Order(String cardNum, String hostelID, Date startDate, Date endDate, String type, double amount) {
        this.cardNum = cardNum;
        this.hostelID = hostelID;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.type = type;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "HostelList{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", hostelID='" + hostelID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", finish=" + finish +
                '}';
    }
}
