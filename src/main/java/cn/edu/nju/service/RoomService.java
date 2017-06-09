package cn.edu.nju.service;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.dao.HostelDao;
import cn.edu.nju.dao.PlanDao;
import cn.edu.nju.model.Hostel;
import cn.edu.nju.model.HostelRoom;
import cn.edu.nju.model.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-12.
 * <p>
 * room相关
 */
@Service
public class RoomService {

    @Resource
    private PlanDao planDao;

    @Resource
    private HostelDao hostelDao;

    public MsgInfo getAllHostel() {
        List<HostelRoom> result = new ArrayList<>();
        List<Hostel> hostelList = planDao.getAllHostel();

        hostelList.forEach(hostel -> {
            Room room = planDao.getAvailableRoom(hostel.getHostelID());

            if (room != null) {
                result.add(new HostelRoom(hostel, room));
            }
        });

        return new MsgInfo(true, "", result);
    }

    public MsgInfo getAvailableHostel() {
        return new MsgInfo(true, "", planDao.getAvailableHostel());
    }

    public MsgInfo getAvailableRoom(String hostelID) {
        Hostel hostel = hostelDao.getHostelByID(hostelID);

        if (hostel != null) {
            return new MsgInfo(true, "", planDao.getAvailableRoom(hostelID));
        } else {
            return new MsgInfo(false, "客栈不存在");
        }
    }

    public MsgInfo getAvailableRoomByDate(String hostelID, long startDate, long endDate) {
        Hostel hostel = hostelDao.getHostelByID(hostelID);

        if (hostel != null) {
            Date start = new Date(startDate);
            Date end = new Date(endDate);

            return new MsgInfo(true, "", planDao.getAvailableRoom(hostelID, start, end));
        } else {
            return new MsgInfo(false, "客栈不存在");
        }
    }
}
