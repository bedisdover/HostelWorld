package cn.edu.nju.controller;

import cn.edu.nju.config.MsgInfo;
import cn.edu.nju.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by song on 17-2-17.
 * <p>
 * user相关
 */
@Controller
@RequestMapping(method = RequestMethod.POST)
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     *
     * @param name     姓名
     * @param password 密码
     * @param phoneNum 电话号
     * @param bankCard 银行卡号
     */
    @RequestMapping("/register")
    @ResponseBody
    public MsgInfo register(@RequestParam String name, @RequestParam String password,
                            @RequestParam String phoneNum, @RequestParam String bankCard) {
        return userService.register(name, password, phoneNum, bankCard);
    }

    /**
     * 激活账户
     */
    @RequestMapping("/activate")
    @ResponseBody
    public MsgInfo activateAccount(HttpSession session, @RequestParam double amount) {
        String cardNum = (String) session.getAttribute("cardNum");

        return userService.activateAccount(cardNum, amount);
    }

    @RequestMapping("/login")
    @ResponseBody
    public MsgInfo login(HttpSession session, @RequestParam String cardNum, @RequestParam String password) {
        MsgInfo msgInfo = userService.login(cardNum, password);

        if (msgInfo.getStatus()) {
            session.setAttribute("cardNum", cardNum);
        }

        return msgInfo;
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        if (session.getAttribute("cardNum") != null) {
            return userService.getInfo((String) session.getAttribute("cardNum"));
        } else {
            return new MsgInfo(false, "未登录");
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        if (session.getAttribute("cardNum") != null) {
            session.invalidate();

            return new MsgInfo(true, "登出成功");
        } else {
            return new MsgInfo(false, "未登录");
        }
    }

    @RequestMapping("/info/edit")
    @ResponseBody
    public MsgInfo editInfo(HttpSession session, @RequestParam String name, @RequestParam String password,
                            @RequestParam String phoneNum, @RequestParam String bankCard) {
        String cardNum = (String) session.getAttribute("cardNum");

        return userService.editInfo(cardNum, name, password, phoneNum, bankCard);
    }

    /**
     * 预定
     *
     * @param type 单间/标准间
     */
    @RequestMapping("/order")
    @ResponseBody
    public MsgInfo order(HttpSession session, @RequestParam String hostelID, @RequestParam String type, long startDate, long endDate) {
        String cardNum = (String) session.getAttribute("cardNum");

        return userService.order(cardNum, hostelID, type, startDate, endDate);
    }

    /**
     * 退订
     */
    @RequestMapping("/order/drop")
    @ResponseBody
    public MsgInfo dropOrder(int id) {
        return userService.dropOrder(id);
    }

    /**
     * 获取所有预定、入住、消费
     */
    @RequestMapping("/history/all")
    @ResponseBody
    public MsgInfo getAllHistory(HttpSession session) {
        String cardNum = (String) session.getAttribute("cardNum");

        return userService.getAllHistory(cardNum);
    }

    /**
     * 充值
     */
    @RequestMapping("/recharge")
    @ResponseBody
    public MsgInfo recharge(HttpSession session, double amount) {
        String cardNum = (String) session.getAttribute("cardNum");

        return userService.recharge(cardNum, amount);
    }
}
