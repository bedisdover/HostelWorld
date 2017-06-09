package cn.edu.nju.model;

/**
 * Created by song on 17-3-9.
 *
 * 开店申请
 */
public class OpenApplication {

    private int id;

    private String hostelID;

    private String name;

    private String address;

    private String pass;

    private String notes;

    public OpenApplication() {
        /*do nothing*/
    }

    public OpenApplication(String hostelID) {
        this.hostelID = hostelID;
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
        return "OpenApplication{" +
                "id=" + id +
                ", hostelID='" + hostelID + '\'' +
                ", pass='" + pass + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
