package cn.cccxu.controller;

import cn.cccxu.entity.Comment;
import cn.cccxu.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/6
 */

@RestController
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/comment/add")
    public boolean addComment(@RequestBody Comment comment,
                              HttpServletRequest request) {
        String userId = request.getSession().getAttribute("userId").toString();
        if(userId == null){
            return false;
        } else {
            comment.setFromUserId(userId);
            return commentService.newComment(comment);
        }
    }

    @PostMapping(path = "/comment/reply")
    public boolean replyComment(@RequestBody Comment comment,
                              HttpServletRequest request) {
        String userId = request.getSession().getAttribute("userId").toString();
        if(userId == null){
            return false;
        } else {
            comment.setFromUserId(userId);
            return commentService.replyComment(comment);
        }
    }

    @GetMapping(path = "/comment/getAll")
    public List<Comment> getAllComment(@RequestParam String lessonId,
                                       HttpServletRequest request) {
        if(request.getSession().getAttribute("userId") == null) {
            return null;
        } else {
            return commentService.getAllComment(lessonId);
        }
    }

    @PostMapping(path = "/comment/like")
    @ResponseBody
    public boolean like(@RequestBody JSONObject jsonObject,
                        HttpServletRequest request) {
        if(request.getSession().getAttribute("userType").equals("user")){
            return commentService.like(jsonObject.getLong("commentId"));
        } else {
            return false;
        }
    }
}
