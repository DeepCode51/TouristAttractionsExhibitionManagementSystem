package com.example.dao;

import com.example.entity.LvyouluxianInfo;
import com.example.vo.LvyouluxianInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LvyouluxianInfoDao extends Mapper<LvyouluxianInfo> {
    List<LvyouluxianInfoVo> findByPublishId(@Param("id") Long id);
    List<LvyouluxianInfoVo> findByReserveId(@Param("id") Long id, @Param("parentId") Long parentId);
    List<LvyouluxianInfoVo> findAll();
    List<LvyouluxianInfoVo> findAllReserve();
}