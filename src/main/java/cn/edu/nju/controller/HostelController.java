package cn.edu.nju.controller;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.service.HostelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by song on 17-3-8.
 * <p>
 * hostel相关
 */
@Controller
@RequestMapping(value = "/hostel", method = RequestMethod.POST)
public class HostelController {

    @Resource
    private HostelService hostelService;

    /**
     * 注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public MsgInfo register(@RequestParam String name, @RequestParam String address, @RequestParam String password) {
        return hostelService.register(name, address, password);
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public MsgInfo login(HttpSession session, @RequestParam String hostelID, @RequestParam String password) {
        MsgInfo msgInfo = hostelService.login(hostelID, password);

        if (msgInfo.getStatus()) {
            session.setAttribute("hostelID", hostelID);
        }

        return msgInfo;
    }

    /**
     * 判断是否登录
     */
    @RequestMapping("/isLogin")
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        Object hostelID = session.getAttribute("hostelID");

        if (hostelID == null) {
            return new MsgInfo(false, "未登录");
        } else {
            return new MsgInfo(true, "已登录", hostelID);
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        Object hostelID = session.getAttribute("hostelID");

        if (hostelID == null) {
            return new MsgInfo(false, "未登录");
        } else {
            session.removeAttribute("hostelID");
            session.invalidate();

            return new MsgInfo(true, "登出成功");
        }
    }

    /**
     * 获取信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public MsgInfo getInfo(HttpSession session) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.getInfo(hostelID);
    }

    /**
     * 修改信息
     */
    @RequestMapping("/info/edit")
    @ResponseBody
    public MsgInfo editInfo(HttpSession session, String name, String address) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.applyModifyInfo(hostelID, name, address);
    }

    /**
     * 获取所有计划
     */
    @RequestMapping("/plan")
    @ResponseBody
    public MsgInfo getAllPlan(HttpSession session) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.getAllPlan(hostelID);
    }

    /**
     * 发布计划
     */
    @RequestMapping("/plan/publish")
    @ResponseBody
    public MsgInfo publishPlan(HttpSession session, long startDate, long endDate, int single, int normal,
                               double singlePrice, double normalPrice) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.publishPlan(hostelID, startDate, endDate, single, normal, singlePrice, normalPrice);
    }

    /**
     * 非会员入住
     */
    @RequestMapping("/checkIn")
    @ResponseBody
    public MsgInfo checkInNonUser(HttpSession session, String name, @RequestParam String roomID,
                                  long endDate, @RequestParam String type) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.checkInNonUser(hostelID, name, roomID, endDate, type);
    }

    /**
     * 会员入住（无订单）
     */
    @RequestMapping("/checkIn/user")
    @ResponseBody
    public MsgInfo checkIn(HttpSession session, @RequestParam String cardNum, @RequestParam String roomID,
                           long endDate, @RequestParam String type) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.checkIn(hostelID, cardNum, roomID, endDate, type);
    }

    /**
     * 会员入住（有订单）
     */
    @RequestMapping("/checkIn/user/order")
    @ResponseBody
    public MsgInfo checkIn(HttpSession session, @RequestParam String cardNum, @RequestParam String roomID,
                           long endDate, @RequestParam String type, int orderID) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.checkIn(hostelID, cardNum, roomID, endDate, type, orderID);
    }

    /**
     * 退房
     */
    @RequestMapping("/checkOut")
    @ResponseBody
    public MsgInfo checkOut(HttpSession session, int checkInID, String payType) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.checkOut(hostelID, checkInID, payType);
    }

    /**
     * 获取所有入住
     */
    @RequestMapping("/checkIn/all")
    @ResponseBody
    public MsgInfo getAllCheckIn(HttpSession session) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.getAllCheckIn(hostelID);
    }

    /**
     * 统计
     */
    @RequestMapping("/statistics")
    @ResponseBody
    public MsgInfo statistics(HttpSession session) {
        String hostelID = (String) session.getAttribute("hostelID");

        return hostelService.statistics(hostelID);
    }
}
