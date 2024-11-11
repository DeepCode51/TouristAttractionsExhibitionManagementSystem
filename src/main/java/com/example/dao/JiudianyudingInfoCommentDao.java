package com.example.dao;

import com.example.entity.JiudianyudingInfoComment;
import com.example.vo.JiudianyudingInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface JiudianyudingInfoCommentDao extends Mapper<JiudianyudingInfoComment> {
    List<JiudianyudingInfoCommentVo> findAllVo(@Param("name") String name);
    List<JiudianyudingInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
