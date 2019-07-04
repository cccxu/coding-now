package cn.cccxu;

import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.service.LoginService;
import cn.cccxu.service.TeacherInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

public class LoginTest extends BaseTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TeacherInfoService teacherInfoService;

    @Test
    public void testLogin(){
        Assert.assertTrue(loginService.checkTeacherLogin("100000","319F4D26E3C536B5DD871BB2C52E3178"));
    }
}
