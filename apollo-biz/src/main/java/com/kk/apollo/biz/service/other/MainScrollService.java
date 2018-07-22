package com.kk.apollo.biz.service.other;

import com.kk.apollo.biz.model.other.Scolview;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */
public interface MainScrollService {
    int deleteByPrimaryKey(Integer scolviewId);
    int insertSelective(Scolview record);
    Scolview selectByPrimaryKey(Integer scolviewId);
    int updateByPrimaryKeySelective(Scolview record);
    List<Scolview> selectAll();
}
