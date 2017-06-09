package cn.edu.nju.controller;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by song on 17-3-12.
 *
 * room相关
 */
@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    /**
     * 所有hostel
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllHostel() {
        return roomService.getAllHostel();
    }

    /**
     * 当前有可预订房间的hostel
     */
    @RequestMapping("/all/available")
    @ResponseBody
    public MsgInfo getAvailableHostel() {
        return roomService.getAvailableHostel();
    }

    /**
     * 当前指定hostel所有可预订房间
     */
    @RequestMapping("/available")
    @ResponseBody
    public MsgInfo getAvailable(@RequestParam String hostelID) {
        return roomService.getAvailableRoom(hostelID);
    }

    /**
     * 指定时间内指定hostel所有可预订房间
     */
    @RequestMapping("/available/date")
    @ResponseBody
    public MsgInfo getAvailableByDate(@RequestParam String hostelID, long startDate, long endDate) {
        return roomService.getAvailableRoomByDate(hostelID, startDate, endDate);
    }

}
