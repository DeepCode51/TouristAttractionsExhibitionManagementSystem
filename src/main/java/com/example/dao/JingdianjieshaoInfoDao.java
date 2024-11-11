package com.example.dao;

import com.example.entity.JingdianjieshaoInfo;
import com.example.vo.JingdianjieshaoInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface JingdianjieshaoInfoDao extends Mapper<JingdianjieshaoInfo> {
    List<JingdianjieshaoInfoVo> findByName(@Param("name") String name);
    
    
    
}
