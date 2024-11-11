package com.example.dao;

import com.example.entity.GentuanInfo;
import com.example.entity.GtOrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GtOrderInfoDao extends Mapper<GtOrderInfo> {

    @Select("select * from gtorder_info g where g.userId = #{userId} and g.level = #{level}")
    List<GtOrderInfo> findOwnGentuan(@Param("userId") Long userId, @Param("level") Integer level);
}