package com.example.dao;

import com.example.entity.JiudianyudingInfo;
import com.example.vo.JiudianyudingInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface JiudianyudingInfoDao extends Mapper<JiudianyudingInfo> {
    List<JiudianyudingInfoVo> findByPublishId(@Param("id") Long id);
    List<JiudianyudingInfoVo> findByReserveId(@Param("id") Long id, @Param("parentId") Long parentId);
    List<JiudianyudingInfoVo> findAll();
    List<JiudianyudingInfoVo> findAllReserve();
}