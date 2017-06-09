package cn.edu.nju.mapper;

import cn.edu.nju.model.Hostel;
import cn.edu.nju.model.Order;
import cn.edu.nju.model.Room;
import cn.edu.nju.model.RoomPlan;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-13.
 *
 * plan相关
 */
public interface PlanMapper {

    int hasPlan(String hostelID, Date startDate, Date endDate);

    /**
     * 获取所有计划
     */
    List<RoomPlan> getAllPlan(String hostelID);

    /**
     * 发布计划
     */
    int publishPlan(RoomPlan roomPlan);

    /**
     * 获取所有发布计划的hostel
     */
    List<Hostel> getAllHostel();

    /**
     * 获取所有有空房的hostel
     */
    List<Hostel> getAvailableHostel();

    /**
     * 获取指定hostel可预定的房间
     */
    Room getAvailableRoom(String hostelID);

    /**
     * 获取指定时间内指定hostel可预定的房间
     */
    Room getAvailableRoomByDate(String hostelID, Date startDate, Date endDate);

    /**
     * 减少单间
     */
    int minusSingle(Order order);

    /**
     * 减少标准间
     */
    int minusNormal(Order order);

    int getPlanNum();
}
