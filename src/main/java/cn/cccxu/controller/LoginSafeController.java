package cn.cccxu.controller;

import cn.cccxu.service.LoginSafeService;
import cn.cccxu.entity.LoginInfo;
import cn.cccxu.entity.LoginSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public boolean checkLoginSafe(@RequestBody LoginSafe loginSafe) {
        System.out.println(loginSafe.getUserId() + loginSafe.getQuestionId() + loginSafe.getAnswer());
        return loginSafeService.checkLoginSafe(loginSafe);
    }

    @PostMapping(path="/account/changePassword")
    @ResponseBody
    public boolean changePassword(@RequestBody LoginInfo loginInfo) {
        return loginSafeService.changePassword(loginInfo);
    }
}
