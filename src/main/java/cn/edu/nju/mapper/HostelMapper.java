package cn.edu.nju.mapper;

import cn.edu.nju.model.Hostel;
import cn.edu.nju.model.ModifyApplication;
import cn.edu.nju.model.OpenApplication;
import cn.edu.nju.model.RoomPlan;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-8.
 * <p>
 * hostel相关
 */
public interface HostelMapper {

    int isHostelExisted(String name, String address);

    boolean isActive(String hostelID);

    String getLastID();

    int createHostel(Hostel hostel);

    int activateHostel(String hostelID);

    Hostel getHostelByID(String hostelID);

    int updateHostel(Hostel hostel);

    int getHostelNum();

    List<Hostel> getAllHostel();
}
