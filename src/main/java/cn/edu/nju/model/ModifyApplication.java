package cn.edu.nju.model;

/**
 * Created by song on 17-3-9.
 *
 * 修改客栈信息申请
 */
public class ModifyApplication {

    private int id;

    private String hostelID;

    private String name_before;

    private String address_before;

    private String name_after;

    private String address_after;

    private String pass;

    private String notes;

    public ModifyApplication() {
        /*do nothing*/
    }

    public ModifyApplication(String hostelID, String name_before, String address_before, String name_after, String address_after) {
        this.hostelID = hostelID;
        this.name_before = name_before;
        this.address_before = address_before;
        this.name_after = name_after;
        this.address_after = address_after;
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

    public String getName_before() {
        return name_before;
    }

    public void setName_before(String name_before) {
        this.name_before = name_before;
    }

    public String getAddress_before() {
        return address_before;
    }

    public void setAddress_before(String address_before) {
        this.address_before = address_before;
    }

    public String getName_after() {
        return name_after;
    }

    public void setName_after(String name_after) {
        this.name_after = name_after;
    }

    public String getAddress_after() {
        return address_after;
    }

    public void setAddress_after(String address_after) {
        this.address_after = address_after;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ModifyApplication{" +
                "id=" + id +
                ", hostelID='" + hostelID + '\'' +
                ", name_before='" + name_before + '\'' +
                ", address_before='" + address_before + '\'' +
                ", name_after='" + name_after + '\'' +
                ", address_after='" + address_after + '\'' +
                '}';
    }
}
