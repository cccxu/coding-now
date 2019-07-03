package cn.cccxu;

import cn.cccxu.service.LoginSafeService;
import cn.cccxu.entity.LoginSafe;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

public class LoginSafeTest extends BaseTest {

    @Autowired
    private LoginSafeService loginSafeService;

    @Test
    public void changePasswordTest() {
        LoginSafe loginSafe = new LoginSafe();
        loginSafe.setUserId("100000");
        loginSafe.setQuestionId(1);
        loginSafe.setAnswer("我是钢铁侠");
        Assert.assertTrue(loginSafeService.checkLoginSafe(loginSafe));
    }
}
