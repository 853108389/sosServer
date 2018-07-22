package com.kk.apollo.biz.dao.other;

import com.kk.apollo.biz.model.other.Scolview;

import java.util.List;

public interface ScolviewMapper {
    int deleteByPrimaryKey(Integer scolviewId);

    int insert(Scolview record);

    int insertSelective(Scolview record);

    Scolview selectByPrimaryKey(Integer scolviewId);

    int updateByPrimaryKeySelective(Scolview record);

    int updateByPrimaryKey(Scolview record);

    List<Scolview> selectAll();
}