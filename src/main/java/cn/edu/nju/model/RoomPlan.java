package cn.edu.nju.model;

import java.util.Date;

/**
 * Created by song on 17-3-8.
 *
 * hostel客房信息
 */
public class RoomPlan {

    private int id;

    private String hostelID;

    private Date startDate;

    private Date endDate;

    /**
     * 单间数量
     */
    private int single;

    /**
     * 标准间数量
     */
    private int normal;

    /**
     * 单间价格
     */
    private double singlePrice;

    /**
     * 标准间价格
     */
    private double normalPrice;

    public RoomPlan() {
        /*do nothing*/
    }

    public RoomPlan(String hostelID, Date startDate, Date endDate, int single, int normal, double singlePrice, double normalPrice) {
        this.hostelID = hostelID;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.single = single;
        this.normal = normal;
        this.singlePrice = singlePrice;
        this.normalPrice = normalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    @Override
    public String toString() {
        return "RoomPlan{" +
                "hostelID='" + hostelID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", single=" + single +
                ", normal=" + normal +
                ", singlePrice=" + singlePrice +
                ", normalPrice=" + normalPrice +
                '}';
    }
}
