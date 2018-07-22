package com.kk.apollo.controller;

import com.kk.apollo.biz.model.comment.Comment;
import com.kk.apollo.biz.model.comment.CommentVo;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.service.comment.CommentService;
import com.kk.apollo.utils.DataParser;
import com.kk.apollo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("add")
    public @ResponseBody
    ModelWithMessageVo fetchComments(@RequestBody Comment comment) {
        if (comment == null || comment.getActivityId() == null || comment.getUserId() == null) {
            return DataParser.setMessageVoProps(false, "数据或者外键为null!", null, 0);
        }
        comment.setCommentTime(TimeUtils.timeFormat("yyyy-MM-dd HH:mm:ss"));
        if (commentService.insertComment(comment) <= 0) {
            return DataParser.setMessageVoProps(false, "数据库插入失败", null, 0);
        }
        Integer commentId = comment.getCommentId();
        CommentVo commentVo = commentService.findCommentById(commentId);
        commentVo = DataParser.parserComment(commentVo);
        if (commentVo == null) {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
        return DataParser.setMessageVoProps(true, "成功", commentVo, 1);
    }

    /**
     * 条件查询评论
     *
     * @return
     */
    @RequestMapping("conditions")
    public @ResponseBody
    ModelWithMessageVo fetchComments(@RequestBody FindConditions<Comment> conditions) {
        Integer index = conditions.getPageIndex();
        List<CommentVo> commentvolist = commentService.findByConditions(conditions);
        if (commentvolist == null) {
            return DataParser.setPageVoProps(false, "失败", null, 0, index);
        }
        commentvolist = DataParser.parserCommentList(commentvolist);
        Integer total = commentService.countAll(conditions);
        return DataParser.setPageVoProps(true, "成功", commentvolist, total, index);
    }


}
