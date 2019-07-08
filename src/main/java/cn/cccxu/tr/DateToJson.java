package cn.cccxu.tr;

import cn.cccxu.entity.Comment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

@RestController
public class DateToJson {

    @PostMapping(path = "/test/date")
    public boolean dateToJson(@RequestBody Comment comment){
        System.out.println(comment.getCommentDate());
        return true;
    }

}
