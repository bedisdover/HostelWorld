package cn.edu.nju.dao;

import cn.edu.nju.mapper.PlanMapper;
import cn.edu.nju.model.Hostel;
import cn.edu.nju.model.Order;
import cn.edu.nju.model.Room;
import cn.edu.nju.model.RoomPlan;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-3-13.
 * <p>
 * plan相关
 */
@Repository
public class PlanDao {

    @Resource
    private PlanMapper planMapper;

    /**
     * 判断指定时间内是否有计划
     */
    public boolean hasPlan(String hostelID, Date startDate, Date endDate) {
        return planMapper.hasPlan(hostelID, startDate, endDate) == 1;
    }

    public List<RoomPlan> getAllPlan(String hostelID) {
        return planMapper.getAllPlan(hostelID);
    }

    /**
     * 发布计划
     */
    public boolean publishPlan(RoomPlan roomPlan) {
        try {
            planMapper.publishPlan(roomPlan);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Hostel> getAllHostel() {
        return planMapper.getAllHostel();
    }

    public List<Hostel> getAvailableHostel() {
        return planMapper.getAvailableHostel();
    }

    public Room getAvailableRoom(String hostelID) {
        return planMapper.getAvailableRoom(hostelID);
    }

    public Room getAvailableRoom(String hostelID, Date startDate, Date endDate) {
        return planMapper.getAvailableRoomByDate(hostelID, startDate, endDate);
    }

    private boolean minusSingle(Order order) {
        try {
            planMapper.minusSingle(order);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean minusNormal(Order order) {
        try {
            planMapper.minusNormal(order);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean minusRoom(Order order) {
        if ("single".equals(order.getType())) {
            return minusSingle(order);
        } else {
            return minusNormal(order);
        }
    }

    public int getPlanNum() {
        return planMapper.getPlanNum();
    }
}
