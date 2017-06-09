package cn.edu.nju.model;

/**
 * Created by song on 17-3-8.
 *
 * 客栈实体类
 */
public class Hostel {

    private int id;

    private String hostelID;

    private String name;

    private String address;

    private String password;

    private boolean active;

    public Hostel() {
        /*do nothing*/
    }

    public Hostel(String hostelID, String name, String address, String password) {
        this.hostelID = hostelID;
        this.name = name;
        this.address = address;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", hostelID='" + hostelID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
