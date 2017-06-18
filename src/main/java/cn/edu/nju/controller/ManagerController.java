package cn.edu.nju.controller;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.service.ManagerService;
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
 * 经理相关
 * <p>
 * url直接为 ‘manager‘时，与tomcat默认url冲突
 */
@Controller
@RequestMapping(value = "/hostel/manager", method = RequestMethod.POST)
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @RequestMapping("/login")
    @ResponseBody
    public MsgInfo login(HttpSession session, @RequestParam String name, @RequestParam String password) {
        MsgInfo msgInfo = managerService.login(name, password);

        if (msgInfo.getStatus()) {
            session.setAttribute("name", name);
        }

        return msgInfo;
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        if (session.getAttribute("name") != null) {
            return new MsgInfo(true, "已登录");
        } else {
            return new MsgInfo(false, "未登录");
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        if (session.getAttribute("name") != null) {
            session.invalidate();

            return new MsgInfo(true, "登出成功");
        } else {
            return new MsgInfo(false, "未登录");
        }
    }

    /**
     * 获取所有客栈信息，包括申请开店的客栈
     */
    @RequestMapping("/hostel")
    @ResponseBody
    public MsgInfo getAllHostel() {
        return managerService.getAllHostel();
    }

    /**
     * 获取 开店/修改信息 请求
     */
    @RequestMapping("/application")
    @ResponseBody
    public MsgInfo getApplication() {
        return managerService.getAllApplication();
    }

    /**
     * 审批开店申请
     */
    @RequestMapping("/approve/open")
    @ResponseBody
    public MsgInfo approveOpen(@RequestParam String hostelID, @RequestParam String pass, String notes) {
        return managerService.approveOpen(hostelID, pass, notes);
    }

    /**
     * 审批修改信息申请
     */
    @RequestMapping("/approve/modify")
    @ResponseBody
    public MsgInfo approveModify(int id, @RequestParam String pass, String notes) {
        return managerService.approveModify(id, pass, notes);
    }

    /**
     * 批量审批开店申请
     */
    @RequestMapping("/approve/open/batch")
    @ResponseBody
    public MsgInfo approveOpenBatch(@RequestParam(value = "id[]") int[] id, @RequestParam String pass) {
        return managerService.approveOpenBatch(id, pass);
    }

    /**
     * 批量审批修改信息申请
     */
    @RequestMapping("/approve/modify/batch")
    @ResponseBody
    public MsgInfo approveModifyBatch(@RequestParam(value = "id[]") int[] id, @RequestParam String pass) {
        return managerService.approveModifyBatch(id, pass);
    }

    /**
     * 获取所有payment
     */
    @RequestMapping("/payment")
    @ResponseBody
    public MsgInfo getAllPayment() {
        return managerService.getAllPayment();
    }

    /**
     * 结算（单个旅店）
     */
    @RequestMapping("/payment/pay")
    @ResponseBody
    public MsgInfo pay(@RequestParam String hostelID) {
        return managerService.pay(hostelID);
    }

    /**
     * 结算（所有旅店）
     */
    @RequestMapping("/payment/pay/all")
    @ResponseBody
    public MsgInfo payAll() {
        return managerService.payAll();
    }

    /**
     * 统计
     */
    @RequestMapping("/statistics")
    @ResponseBody
    public MsgInfo getStatistics() {
        return managerService.getStatistics();
    }
}
