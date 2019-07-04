package cn.cccxu;

import cn.cccxu.dao.LoginSafeDao;
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

    @Autowired
    private LoginSafeDao loginSafeDao;

    @Test
    public void changePasswordTest() {
    }
}
