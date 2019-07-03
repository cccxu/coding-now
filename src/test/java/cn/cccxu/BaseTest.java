package cn.cccxu;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {

    @Before
    public void init() {
        System.out.println("--------开始测试--------");
    }

    @After
    public void finish() {
        System.out.println("--------测试结束--------");
    }
}
