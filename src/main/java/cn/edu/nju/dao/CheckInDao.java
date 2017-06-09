package cn.edu.nju.dao;

import cn.edu.nju.mapper.CheckInMapper;
import cn.edu.nju.model.CheckIn;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-3-13.
 * <p>
 * checkIn相关
 */
@Repository
public class CheckInDao {

    @Resource
    private CheckInMapper checkInMapper;

    /**
     * 入住
     */
    public boolean CheckIn(CheckIn checkIn) {
        try {
            checkInMapper.checkIn(checkIn);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 退房
     */
    public boolean checkOut(int checkInID, String payType) {
        try {
            checkInMapper.checkOut(checkInID, payType);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CheckIn getCheckInByID(int checkInID) {
        return checkInMapper.getCheckInByID(checkInID);
    }

    /**
     * 获取所有入住信息，包含已退房
     */
    public List<CheckIn> getAllCheckIn(String hostelID) {
        return checkInMapper.getAllCheckInByHostel(hostelID);
    }

    /**
     * 获取当前入住信息
     */
    public List<CheckIn> getCurrentCheckIn(String hostelID) {
        return checkInMapper.getCurrentCheckInByHostel(hostelID);
    }

    public List<CheckIn> getUserCheckIn(String cardNum) {
        return checkInMapper.getUserCheckIn(cardNum);
    }
}
