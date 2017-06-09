package cn.edu.nju.model;

/**
 * Created by song on 17-3-12.
 * <p>
 * room，包括：
 * 单间/标准间 数量及价格
 */
public class Room {

    private int id;

    private String hostelID;

    /**
     * 剩余单间数量
     */
    private int single;

    /**
     * 剩余标准间数量
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

    public Room() {
        /*do nothing*/
    }

    public Room(String hostelID, int single, int normal, double singlePrice, double normalPrice) {
        this.hostelID = hostelID;
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

    public int getNum(String type) {
        if ("single".equals(type)) {
            return single;
        } else {
            return normal;
        }
    }

    public double getPrice(String type) {
        if ("single".equals(type)) {
            return singlePrice;
        } else {
            return normalPrice;
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "hostelID='" + hostelID + '\'' +
                ", single=" + single +
                ", normal=" + normal +
                ", singlePrice='" + singlePrice + '\'' +
                ", normalPrice='" + normalPrice + '\'' +
                '}';
    }
}
