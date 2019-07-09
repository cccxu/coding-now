package cn.cccxu.service;

import cn.cccxu.dao.CommentDao;
import cn.cccxu.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/6
 */

@Service
public class CommentService {

    private CommentDao commentDao;

    CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public boolean newComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    public List<Comment> getAllComment(String lessonId) {
        return commentDao.selectAllComment(lessonId);
    }

    public List<Comment> getUserAllComment(String userId) {
        return commentDao.selecUsertAllComment(userId);
    }

    public boolean like(long commentId) {
        return commentDao.updateLike(commentId);
    }

    public boolean replyComment(Comment comment) {
        return commentDao.insertreplyComment(comment);
    }

    public boolean deleteComment(long commentId){
        return commentDao.deleteComment(commentId);
    }
}
