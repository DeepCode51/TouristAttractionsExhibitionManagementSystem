package com.example.dao;

import com.example.entity.LvyouluxianInfoComment;
import com.example.vo.LvyouluxianInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LvyouluxianInfoCommentDao extends Mapper<LvyouluxianInfoComment> {
    List<LvyouluxianInfoCommentVo> findAllVo(@Param("name") String name);
    List<LvyouluxianInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
