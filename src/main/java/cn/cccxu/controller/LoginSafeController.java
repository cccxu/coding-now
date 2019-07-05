package cn.cccxu.controller;

import cn.cccxu.service.LoginSafeService;
import cn.cccxu.entity.LoginInfo;
import cn.cccxu.entity.LoginSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Controller
public class LoginSafeController {

    private LoginSafeService loginSafeService;

    @Autowired
    LoginSafeController(LoginSafeService loginSafeService) {
        this.loginSafeService = loginSafeService;
    }

    /**
     * @param loginSafe 完整的loginSafe对象
     * @return 返回校验结果
     * session内容：
     *  1. userId : 用户id（防止通过密保验证后使用别人id修改别人密码）
     *  2. allowChange : true 表示允许修改
     */
    @PostMapping(path="/account/checkLoginSafe")
    @ResponseBody
    public boolean checkLoginSafe(@RequestBody LoginSafe loginSafe,
                                  HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userId", loginSafe.getUserId());

        //校验成功向session中放入允许修改字段
        if(loginSafeService.checkLoginSafe(loginSafe)) {
            httpSession.setAttribute("allowChange" , true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param loginInfo
     * @return 修改是否成功
     * 修改：
     *  1. 不再使用loginInfo传入的userId, 改用session中的userId（防止通过密保验证后使用别人id修改别人密码）
     *  2. 检查session中的allowChange字段，当且仅当为true时才可以修改
     */
    @PostMapping(path="/account/changePassword")
    @ResponseBody
    public boolean changePassword(@RequestBody LoginInfo loginInfo,
                                  HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        if((Boolean) httpSession.getAttribute("allowChange")) {
            //设置为session中的id
            loginInfo.setUserId(httpSession.getAttribute("userId").toString());
            return loginSafeService.changePassword(loginInfo);
        } else {
            return false;
        }
    }
}
