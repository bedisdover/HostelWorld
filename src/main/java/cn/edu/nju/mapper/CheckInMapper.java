package cn.edu.nju.mapper;

import cn.edu.nju.model.CheckIn;

import java.util.List;

/**
 * Created by song on 17-3-12.
 * <p>
 * checkIn相关
 */
public interface CheckInMapper {

    /**
     * 入住
     */
    int checkIn(CheckIn checkIn);

    /**
     * 退房
     */
    int checkOut(int checkInID, String payType);

    /**
     * 获取入住详情
     */
    CheckIn getCheckInByID(int checkInID);

    /**
     * 获取指定hostel所有入住信息，包含已退房
     */
    List<CheckIn> getAllCheckInByHostel(String hostelID);

    /**
     * 获取指定hostel当前入住信息
     */
    List<CheckIn> getCurrentCheckInByHostel(String hostelID);

    /**
     * 获取指定用户所有入住
     */
    List<CheckIn> getAllCheckInByUser(String cardNum);

    /**
     * 获取用户入住信息
     */
    List<CheckIn> getUserCheckIn(String cardNum);
}
