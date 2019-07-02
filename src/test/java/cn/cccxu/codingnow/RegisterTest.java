package cn.cccxu.codingnow;

import cn.cccxu.codingnow.service.RegisterService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

public class RegisterTest extends BaseTest {

    @Autowired
    RegisterService registerService;

    @Test
    public void checkIdUseableTest() {
        Assert.assertFalse(registerService.checkUserIdUseable("100001"));
        Assert.assertTrue(registerService.checkUserIdUseable("100005"));
    }
}
