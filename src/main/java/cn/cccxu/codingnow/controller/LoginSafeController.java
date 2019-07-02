package cn.cccxu.codingnow.controller;

import cn.cccxu.codingnow.service.LoginSafeService;
import cn.cccxu.codingnow.entity.LoginInfo;
import cn.cccxu.codingnow.entity.LoginSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping(path="/account/checkLoginSafe")
    @ResponseBody
    public boolean checkLoginSafe(LoginSafe loginSafe) {
        return loginSafeService.checkLoginSafe(loginSafe);
    }

    @PostMapping(path="/account/changePassword")
    @ResponseBody
    public boolean changePassword(LoginInfo loginInfo) {
        return loginSafeService.changePassword(loginInfo);
    }
}
