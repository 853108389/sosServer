package com.kk.apollo.biz.service.other.impl;

import com.kk.apollo.biz.dao.other.ScolviewMapper;
import com.kk.apollo.biz.model.other.Scolview;
import com.kk.apollo.biz.service.other.MainScrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */
@Service
public class MainScrollServiceImpl implements MainScrollService {
    @Autowired
    public ScolviewMapper scolviewMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(Integer scolviewId) {
        return scolviewMapper.deleteByPrimaryKey(scolviewId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insertSelective(Scolview record) {
        return scolviewMapper.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Scolview selectByPrimaryKey(Integer scolviewId) {
        return scolviewMapper.selectByPrimaryKey(scolviewId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKeySelective(Scolview record) {
        return scolviewMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Scolview> selectAll() {
        return scolviewMapper.selectAll();
    }
}
