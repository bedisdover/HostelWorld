package cn.edu.nju.model;

/**
 * Created by song on 17-3-16.
 *
 * hostel及空房信息
 */
public class HostelRoom {

    private String hostelID;

    private String name;

    private String address;

    private int single;

    private double singlePrice;

    private int normal;

    private double normalPrice;

    public HostelRoom(Hostel hostel, Room room) {
        this.hostelID = hostel.getHostelID();
        this.name = hostel.getName();
        this.address = hostel.getAddress();
        this.single = room.getSingle();
        this.singlePrice = room.getSinglePrice();
        this.normal = room.getNormal();
        this.normalPrice = room.getNormalPrice();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    @Override
    public String toString() {
        return "HostelRoom{" +
                "hostelID='" + hostelID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", single=" + single +
                ", singlePrice=" + singlePrice +
                ", normal=" + normal +
                ", normalPrice=" + normalPrice +
                '}';
    }
}
