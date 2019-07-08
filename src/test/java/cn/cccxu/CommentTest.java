package cn.cccxu;

import cn.cccxu.entity.Comment;
import cn.cccxu.service.CommentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

public class CommentTest  extends BaseTest{

    @Autowired
    private CommentService commentService;

    @Test
    public void getAll(){
        List test = commentService.getAllComment("000001");
        System.out.println(test);
    }
}
