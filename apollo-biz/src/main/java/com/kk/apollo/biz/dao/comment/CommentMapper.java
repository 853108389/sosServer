package com.kk.apollo.biz.dao.comment;

import com.kk.apollo.biz.model.comment.Comment;
import com.kk.apollo.biz.model.comment.CommentVo;
import com.kk.apollo.biz.model.common.FindConditions;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentVo> findCommentsByActivityId(Integer id);

    CommentVo findCommentVoById(Integer commentId);

    List<CommentVo> findByConditions(FindConditions<Comment> conditions);

    Integer countAll(FindConditions<Comment> conditions);
}