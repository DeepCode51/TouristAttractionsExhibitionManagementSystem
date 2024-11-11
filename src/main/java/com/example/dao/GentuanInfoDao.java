package com.example.dao;

import com.example.entity.GentuanInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GentuanInfoDao extends Mapper<GentuanInfo> {

    @Select("select * from gentuan_info g where g.name like concat('%', #{text}, '%')")
    List<GentuanInfo> findByText(String text);
}