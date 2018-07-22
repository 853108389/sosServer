package com.kk.apollo.biz.service.comment.impl;

import com.kk.apollo.biz.dao.comment.CommentMapper;
import com.kk.apollo.biz.model.comment.Comment;
import com.kk.apollo.biz.model.comment.CommentVo;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<CommentVo> findCommentsByActivityId(Integer id) {
        return commentMapper.findCommentsByActivityId(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer insertComment(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CommentVo findCommentById(Integer commentId) {
        return commentMapper.findCommentVoById(commentId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<CommentVo> findByConditions(FindConditions<Comment> conditions) {
        return commentMapper.findByConditions(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer countAll(FindConditions<Comment> conditions) {
        return commentMapper.countAll(conditions);
    }
}
