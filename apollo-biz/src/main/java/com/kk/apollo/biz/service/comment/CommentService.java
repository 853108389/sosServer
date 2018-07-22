package com.kk.apollo.biz.service.comment;

import com.kk.apollo.biz.model.comment.Comment;
import com.kk.apollo.biz.model.comment.CommentVo;
import com.kk.apollo.biz.model.common.FindConditions;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
public interface CommentService {
    /**
     * 根据活动查询活动下所有评论
     * @param id
     * @return
     */
    List<CommentVo> findCommentsByActivityId(Integer id);

    /**
     * 添加一条评论
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

    CommentVo findCommentById(Integer commentId);

    List<CommentVo> findByConditions(FindConditions<Comment> conditions);

    Integer countAll(FindConditions<Comment> conditions);
}
